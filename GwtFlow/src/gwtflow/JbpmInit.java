package gwtflow;

import org.hibernate.Session;
import org.jbpm.api.IdentityService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.pvm.internal.cfg.JbpmConfiguration;

public class JbpmInit {

	
	
	public static void main(String[] args) {
		JbpmConfiguration configuration = new JbpmConfiguration();
		ProcessEngine processEngine = configuration.buildProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		IdentityService identityService = processEngine.getIdentityService();
		
		init(
				configuration.openEnvironment().get(Session.class),
				repositoryService,
				identityService);
	}
	
	public static void init(Session session, RepositoryService repositoryService, IdentityService identityService) {
		NewDeployment deployment = repositoryService.createDeployment();
		deployment.addResourceFromClasspath("bizzflow/applyflow.jpdl.xml");
		deployment.deploy();					
		
		String salesManagerGroupId = identityService.createGroup("销售部经理", "部门经理");
		String salesGroupId = identityService.createGroup("销售部员工", "员工", salesManagerGroupId);
		
		String generalManagerGroupId = identityService.createGroup("总经理");
		String financialGroupId = identityService.createGroup("财务");
		String cashierGroupId = identityService.createGroup("出纳");
		
		
		identityService.createUser("zhang", "小张", null);
		identityService.createMembership("zhang", salesGroupId);
		
		identityService.createUser("wang", "小王", null);
		identityService.createMembership("wang", salesManagerGroupId);

		identityService.createUser("li", "小李", null);
		identityService.createMembership("li", generalManagerGroupId);

		identityService.createUser("zhao", "小赵", null);
		identityService.createMembership("zhao", financialGroupId);

		identityService.createUser("zhu", "小朱", null);
		identityService.createMembership("zhu", cashierGroupId);
	
		session.createSQLQuery("update jbpm4_id_user set password_ = '1'").executeUpdate();
	}

}
