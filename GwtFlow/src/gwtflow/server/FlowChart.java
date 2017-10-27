package gwtflow.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.identity.User;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.cfg.JbpmConfiguration;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@SuppressWarnings("serial")
public class FlowChart extends HttpServlet {
	
	private static JbpmConfiguration jbpmConfiguration;
	private static ProcessEngine jbpmProcessEngine;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (jbpmConfiguration == null) {
				jbpmConfiguration = new JbpmConfiguration();
				jbpmProcessEngine = jbpmConfiguration.buildProcessEngine();
			}
			
			String taskId = request.getParameter("id");
			Task task = jbpmProcessEngine.getTaskService().getTask(taskId);
			String executionId = task.getExecutionId();
			ExecutionService executionService = jbpmConfiguration.getExecutionService();
			String processDefineId = executionService.findExecutionById(executionId).getProcessDefinitionId();

			DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = dbfactory.newDocumentBuilder();
			InputStream is = getClass().getResourceAsStream("/bizzflow/" + processDefineId + ".chart.xml");
			Document document = builder.parse(is);
			
			HistoryService historyService = jbpmProcessEngine.getHistoryService();
			
	        XPathFactory factoryXpah = XPathFactory.newInstance();
	        XPath xpath = factoryXpah.newXPath();
	        XPathExpression expr = xpath.compile("/chart/tasks/task");
	        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
	        for (int i = 0; i < nodeList.getLength(); ++i) {
	        	Element taskEl = (Element) nodeList.item(i);
	        	String status = taskEl.getAttribute("status");
	        	String name = taskEl.getAttribute("name");
	        	if (!"start".equals(status) && !"end".equals(status)) {
	        		HistoryActivityInstance hisActInst = historyService.createHistoryActivityInstanceQuery()
	        			.activityName(name)
	        			.executionId(executionId)
	        			.uniqueResult();
	        		
	        		if (! (hisActInst instanceof HistoryTaskInstanceImpl)) {
	        			taskEl.setAttribute("status", "");
	        			continue;
	        		}
	        		
	        		HistoryTaskInstanceImpl hisTaskInst = (HistoryTaskInstanceImpl) hisActInst;
	        		historyService.createHistoryTaskQuery().taskId(taskId);
	        		HistoryTask hisTask = hisTaskInst.getHistoryTask();
	        		String taskState = hisTask.getState();
        			StringBuilder memo = new StringBuilder();
        			memo.append("开始时间:").append(hisTaskInst.getStartTime());
	        		if ("completed".equals(taskState)) {
	        			memo.append("\n结束时间:").append(hisTaskInst.getEndTime());
	        			memo.append("\n审核人:");
	        			String assignee = hisTask.getAssignee();
	        			User user = jbpmProcessEngine.getIdentityService().findUserById(assignee);
	        			memo.append(user.getGivenName());
	        			taskEl.setAttribute("status", "finished");
	        		}
	        		else {
	        			taskEl.setAttribute("status", "current");
	        		}
        			taskEl.setAttribute("memo", memo.toString());
	        	}

	        }
	        
	        StringWriter stringOut = new StringWriter();
	        TransformerFactory tfactory = TransformerFactory.newInstance();
	        Transformer transformer = tfactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	        DOMSource source = new DOMSource(document);
	        StreamResult streamResult = new StreamResult(stringOut);
	        transformer.transform(source, streamResult);
			response.setContentType("text/xml");
			response.setCharacterEncoding("utf-8");
	        String result = stringOut.toString();
	        stringOut.close();
	        response.getWriter().write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
