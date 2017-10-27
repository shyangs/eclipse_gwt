package gwtflow.server;

import gwtflow.client.service.GwtFlowService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.hibernate.Session;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskService;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.cfg.JbpmConfiguration;
import org.jbpm.pvm.internal.identity.impl.GroupImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GwtFlowServiceImpl extends RemoteServiceServlet implements
		GwtFlowService {

	JbpmConfiguration jbpmConfiguration;
	ProcessEngine jbpmProcessEngine;
	
	public GwtFlowServiceImpl() {
		jbpmConfiguration = new JbpmConfiguration();
		jbpmProcessEngine = jbpmConfiguration.buildProcessEngine();
		
//		JbpmInit.init(
//				jbpmConfiguration.openEnvironment().get(Session.class),
//				jbpmProcessEngine.getRepositoryService(),
//				jbpmConfiguration.getIdentityService());
	}
	

	@Override
	public Map<String, String> loadFormRight(String rightFilePath, String status) {
		InputStream is = null;
		StringWriter stringOut = null;
		try {
			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			is = getClass().getResourceAsStream("/bizzinfo/" + rightFilePath);
			File rightFile = new File(getServletContext().getRealPath("/bizz/" + rightFilePath));
			Document document = builder.parse(rightFile);
	        XPathFactory factoryXpah = XPathFactory.newInstance();
	        XPath xpath = factoryXpah.newXPath();
	        XPathExpression expr = xpath.compile("/bizzright/status[@name='" + status + "']/item");
	        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
	        Map<String, String> result = new HashMap<String, String>();
	        for (int i = 0; i < nodeList.getLength(); ++i) {
	        	Element el = (Element) nodeList.item(i);
	        	result.put(el.getAttribute("name"), el.getAttribute("right"));
	        }
	        return result;
		} catch (Exception e) {
			
			throw new RuntimeException(e.getMessage());
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (stringOut != null) {
				try {
					stringOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	@Override
	public BasePagingLoadResult<BaseModel> getWorklist(PagingLoadConfig config) {
		TaskService taskService = jbpmProcessEngine.getTaskService();
		List<Task> taskList = taskService.findGroupTasks(getCurrentUserId());
		taskList.addAll(taskService.findPersonalTasks(getCurrentUserId()));
		List<Task> subTaskList = new ArrayList<Task>();
		List<BaseModel> result = new ArrayList<BaseModel>();
		for (int i = config.getOffset(); i < taskList.size(); ++i) {
			subTaskList.add(taskList.get(i));
			if (subTaskList.size() > config.getOffset()) {
				break;
			}
		}
		
		ExecutionService executionService = jbpmProcessEngine.getExecutionService();
		for (Task task : subTaskList) {
			BaseModel m = new BaseModel();
			for (String varName : executionService.getVariableNames(task.getExecutionId())) {
				m.set(varName, executionService.getVariable(task.getExecutionId(), varName));
			}
			m.set("taskName", task.getName());
			m.set("taskId", task.getId());
			result.add(m);
		}
		return new BasePagingLoadResult<BaseModel>(result, config.getOffset(), taskList.size());
	}
	
	
	private String getCurrentUserId() {
		HttpSession session =  getThreadLocalRequest().getSession();
		User currentUser = (User) session.getAttribute("user");
		return currentUser.getId();
	}
	
	@Override
	public Boolean login(String userName, String password) {
		Session session = jbpmConfiguration.openEnvironment().get(Session.class);
		String sql = "from UserImpl as u where u.id = :id and u.password = :pwd";
		List<?> result = session.createQuery(sql).setString("id", userName).setString("pwd", password).list();
		if (result.size() == 0) {
			return false;
		}
		else {
			getThreadLocalRequest().getSession().setAttribute("user", result.get(0));
			return true;
		}
	}

	@Override
	public Integer logout() {
		getThreadLocalRequest().getSession().invalidate();
		return null;
	}

	@Override
	public Integer createApply(String reason, Double cost) {
		Map<String, Object> variables = new HashMap<String, Object>(); 
		variables.put("申请人", getCurrentUserId());
		variables.put("理由", reason);
		variables.put("金额", cost);

		ExecutionService executionService = jbpmProcessEngine.getExecutionService();
		executionService.startProcessInstanceByKey("applyflow", variables);
		return null;
	}

	@Override
	public Integer submit(String taskId, String transition, String title, String text) {
		TaskService taskService = jbpmProcessEngine.getTaskService();
		Task task = taskService.getTask(taskId);
		if (title != null) {
			ExecutionService executionService = jbpmProcessEngine.getExecutionService();
			executionService.setVariable(task.getExecutionId(), title, text);
		}
		taskService.completeTask(taskId, transition);
		return null;
	}	
	
	@Override
	public Boolean canApply() {
		IdentityService identityService = jbpmProcessEngine.getIdentityService();
		List<Group> groups = identityService.findGroupsByUser(getCurrentUserId());
		GroupImpl group = (GroupImpl) groups.get(0);
		if ("员工".equals(group.getType())) {
			return true;
		}
		else {
			return false;
		}
	}
}
