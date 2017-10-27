package gwtflow.client.service;

import java.util.Map;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtflow")
public interface GwtFlowService extends RemoteService {
	Map<String, String> loadFormRight(String rightFilePath, String status);
	PagingLoadResult<BaseModel> getWorklist(PagingLoadConfig config);
	Boolean login(String userName, String password);
	Integer logout();
	Integer submit(String taskId, String transition, String title, String text);
	Integer createApply(String reason, Double cost);
	Boolean canApply();
}
