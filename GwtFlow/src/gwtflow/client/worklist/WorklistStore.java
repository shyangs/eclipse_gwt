package gwtflow.client.worklist;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;

public class WorklistStore extends ListStore<BaseModel> {
	public WorklistStore() {
		super(new WorklistLoader());
	}
}
