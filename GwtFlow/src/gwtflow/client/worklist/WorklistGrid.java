package gwtflow.client.worklist;

import gwtflow.client.AppEvents;
import gwtflow.client.GwtFlow;
import gwtflow.client.dialog.ApprovalForm;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class WorklistGrid extends Grid<BaseModel> implements Listener<GridEvent<BaseModel>> {
	
	private Listener<BaseEvent> refreshListListener = new Listener<BaseEvent>(){
		@Override
		public void handleEvent(BaseEvent be) {
			reload();
		}
	};
	
	public static WorklistGrid create() {
		List<ColumnConfig> columnList = new ArrayList<ColumnConfig>();
		columnList.add(new ColumnConfig("理由", "理由", 100));
		columnList.add(new ColumnConfig("金额", "金额", 100));
		columnList.add(new ColumnConfig("申请人", "申请人", 100));
		ColumnModel cm = new ColumnModel(columnList);
		return new WorklistGrid(new WorklistStore(), cm);
	}

	public WorklistGrid(WorklistStore store, ColumnModel cm) {
		super(store, cm);
		GwtFlow.getCurrent().addAppListener(AppEvents.LoginSuccess, refreshListListener);
		GwtFlow.getCurrent().addAppListener(AppEvents.AfterSubmit, refreshListListener);
		this.addListener(Events.RowDoubleClick, this);
		this.setAutoExpandColumn("理由");
	}
	
	public void reload() {
		getStore().getLoader().load();
	}

	@Override
	public void handleEvent(GridEvent<BaseModel> ge) {
		if (ge.getType() == Events.RowDoubleClick) {
			ApprovalForm approvalForm = ApprovalForm.getInstance();
			approvalForm.setTaskId(ge.getModel().get("taskId").toString());
			approvalForm.setData(ge.getModel());
			approvalForm.setTaskStatus(ge.getModel().get("taskName").toString());
			approvalForm.show();
		}
	}
}
