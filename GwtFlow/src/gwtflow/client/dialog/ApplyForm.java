package gwtflow.client.dialog;

import gwtflow.client.AsyncCallbackEx;
import gwtflow.client.service.GwtFlowService;
import gwtflow.client.service.GwtFlowServiceAsync;

import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;

public class ApplyForm extends BizzForm {
	
	private static ApplyForm instance;
	private String taskStatus;
	
	public static ApplyForm getInstance() {
		if (instance == null) {
			instance = new ApplyForm();
		}
		return instance;
	}
	
	public ApplyForm() {
		htmlPanel.setUrl(GWT.getHostPageBaseURL() + "bizz/applyform.html");
		setHeading("申请表");
		setWidth(440);
		setHeight(370);
		setModal(true);
		setResizable(false);
		setClosable(false);

		this.setButtons(Dialog.OKCANCEL);
		getButtonById(Dialog.OK).setText("申请");
		getButtonById(Dialog.CANCEL).setText("放弃");
	}

	@Override
	protected void onButtonPressed(Button button) {
		if (Dialog.OK.equals(button.getItemId())) {
			if (! isValid()) {
				MessageBox.alert("错误", "一些信息填写不正确", null);
				return;
			}
			GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
			service.createApply(
					getFieldValue("理由"),
					Double.valueOf(getFieldValue("金额")),
					new AsyncCallbackEx<Integer>() {
				@Override
				public void onSuccess(Integer result) {
					hide();
					reset();
				}
			});
		}
		else {
			super.onButtonPressed(button);
		}
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
		setRight("applyform.right.xml", taskStatus);
	}
	
	public String getTaskStatus(String status) {
		return taskStatus;
	}
}
