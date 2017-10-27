package gwtflow.client.worklist;

import gwtflow.client.service.GwtFlowService;
import gwtflow.client.service.GwtFlowServiceAsync;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class WorklistRpcProxy extends RpcProxy<PagingLoadResult<BaseModel>> {

	@Override
	protected void load(Object loadConfig,
			AsyncCallback<PagingLoadResult<BaseModel>> callback) {
		
		GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
		service.getWorklist((PagingLoadConfig)loadConfig, callback);
	}

}
