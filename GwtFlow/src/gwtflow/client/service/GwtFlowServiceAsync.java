package gwtflow.client.service;

import java.util.Map;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtFlowServiceAsync {
	void loadFormRight(String rightFilePath, String status, AsyncCallback<Map<String, String>> callback);
	void getWorklist(PagingLoadConfig config, AsyncCallback<PagingLoadResult<BaseModel>> callback);
	void login(String userName, String password, AsyncCallback<Boolean> callback);
	void logout(AsyncCallback<Integer> callback);
	void createApply(String reason, Double cost, AsyncCallback<Integer> callback);
	void submit(String taskId, String transition, String title, String text, AsyncCallback<Integer> callback);
	void canApply(AsyncCallback<Boolean> callback);
}
