package gwtflow.client.worklist;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

public class WorklistLoader extends BasePagingLoader<PagingLoadResult<BaseModel>> {

	public WorklistLoader() {
		super(new WorklistRpcProxy());
	}

}
