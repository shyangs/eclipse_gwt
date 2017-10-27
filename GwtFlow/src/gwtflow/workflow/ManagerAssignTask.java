package gwtflow.workflow;

import java.util.List;

import org.hibernate.Session;
import org.jbpm.api.IdentityService;
import org.jbpm.api.identity.Group;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.jbpm.pvm.internal.env.Environment;
import org.jbpm.pvm.internal.identity.impl.GroupImpl;
import org.jbpm.pvm.internal.identity.impl.MembershipImpl;

@SuppressWarnings("serial")
public class ManagerAssignTask implements AssignmentHandler {

	@Override
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		IdentityService identityService = Environment.getCurrent().get(IdentityService.class);
		
		String userId = (String) execution.getVariable("申请人");
		List<Group> groups = identityService.findGroupsByUser(userId);
		GroupImpl group = (GroupImpl) groups.get(0);
		GroupImpl parentGroup = group.getParent();
		
		Session session = Environment.getCurrent().get(Session.class);
		String sql = "from MembershipImpl m where m.group = :g";
		MembershipImpl membership = 
			(MembershipImpl) session.createQuery(sql)
				.setEntity("g", parentGroup).list().get(0);
		assignable.setAssignee(membership.getUser().getId());
	}

}
