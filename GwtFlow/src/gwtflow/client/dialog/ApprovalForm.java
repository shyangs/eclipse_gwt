package gwtflow.client.dialog;

import gwtflow.client.AppEvents;
import gwtflow.client.AsyncCallbackEx;
import gwtflow.client.GwtFlow;
import gwtflow.client.service.GwtFlowService;
import gwtflow.client.service.GwtFlowServiceAsync;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;

public class ApprovalForm extends ApplyForm {

	private static ApprovalForm instance;
	private String taskId;
	private BaseModel baseModel;

	
	private Listener<BaseEvent> readyListenerForData = new Listener<BaseEvent>() {
		@SuppressWarnings("unchecked")
		@Override
		public void handleEvent(BaseEvent be) {
			htmlPanel.removeListener(Events.Ready, this);
			for (String fieldId : fieldMap.keySet()) {
				Object value = baseModel.get(fieldId);
				if (value == null) {
					continue;
				}
				TextField<Object> field = (TextField<Object>) fieldMap.get(fieldId);
				field.setValue(value.toString());
			}
		}
	};	
	
	public static ApprovalForm getInstance() {
		if (instance == null) {
			instance = new ApprovalForm();
		}
		return instance;
	}
	
	public ApprovalForm() {
		setButtons(Dialog.YESNOCANCEL);
		getButtonById(Dialog.YES).setText("同意");
		getButtonById(Dialog.NO).setText("拒绝");
		getButtonById(Dialog.CANCEL).setText("关闭");
		
		this.getButtonBar().add(new Button("流程图", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ChartDialog.getInstance().show(taskId);
			}
		}));
		
	}
	
	public TextField<?> getEditableField() {
		for (TextField<?> field : fieldMap.values()) {
			if (! field.isReadOnly()) {
				return field;
			}
		}
		return null;
	}
	
	@Override
	protected void onButtonPressed(Button button) {
		if (Dialog.CANCEL.equals(button.getItemId())) {
			hide();
			reset();
			return;
		}
		if (! isValid()) {
			MessageBox.alert("错误", "一些信息填写不正确", null);
			return;
		}
		
		String transition = null;
		if (Dialog.YES.equals(button.getItemId())) {
			transition = "accept";
		}
		else {
			transition = "reject";
		}
		
		String editFieldLable = null;
		String editFieldValue = null;
		TextField<?> field = getEditableField();
		if (field != null) {
			editFieldLable = field.getData("dataName");
			if (field.getValue() == null) {
				editFieldValue = null;
			}
			else {
				editFieldValue = field.getValue().toString();
			}
		}
		GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
		service.submit(
				getTaskId(),
				transition,
				editFieldLable,
				editFieldValue,
				new AsyncCallbackEx<Integer>() {
			@Override
			public void onSuccess(Integer result) {
				GwtFlow.getCurrent().fireAppEvent(AppEvents.AfterSubmit, null);
				hide();
				reset();
			}
		});
	}	
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}	
	
	@Override
	public void setTaskStatus(String taskStatus) {
		super.setTaskStatus(taskStatus);
		if ("出纳支付".equals(taskStatus)) {
			this.getButtonById(Dialog.NO).setVisible(false);
		}
		else {
			this.getButtonById(Dialog.NO).setVisible(true);
		}
	}	
	
	
	public void setData(BaseModel bm) {
		baseModel = bm;
		htmlPanel.addListener(Events.Ready, readyListenerForData);
	}	
}
