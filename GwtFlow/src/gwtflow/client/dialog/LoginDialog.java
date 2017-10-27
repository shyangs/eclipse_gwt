package gwtflow.client.dialog;

import gwtflow.client.AppEvents;
import gwtflow.client.AsyncCallbackEx;
import gwtflow.client.GwtFlow;
import gwtflow.client.service.GwtFlowService;
import gwtflow.client.service.GwtFlowServiceAsync;

import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class LoginDialog extends Dialog {
	
	private TextField<String> userNameField;
	private TextField<String> passwordField;
	
	public LoginDialog() {
		setHeading("登录");
		setLayout(new FitLayout());
		setModal(true);
		setResizable(false);
		setClosable(false);
		setWidth(300);
		setHeight(140);

		FormPanel formPanel = new FormPanel();
		formPanel.setBodyBorder(false);
		formPanel.setHeaderVisible(false);
		formPanel.setLabelWidth(50);

		userNameField = new TextField<String>();
		userNameField.setFieldLabel("用户名");
		formPanel.add(userNameField, new FormData("100%"));

		passwordField = new TextField<String>();
		passwordField.setFieldLabel("密码");
		passwordField.setPassword(true);
		formPanel.add(passwordField, new FormData("100%"));

		add(formPanel);

		getButtonById(Dialog.OK).setText("登录");
	}
	
	public void reset() {
		userNameField.setValue(null);
		passwordField.setValue(null);
	}

	@Override
	protected void onButtonPressed(Button button) {
		if (button == getButtonBar().getItemByItemId(OK)) {
			GwtFlow.getCurrent().mask();
			hide();
			GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
			service.login(userNameField.getValue(), passwordField.getValue(), new AsyncCallbackEx<Boolean>() {
				@Override
				public void onSuccess(Boolean result) {
					GwtFlow.getCurrent().unmask();
					if (result) {
						reset();
						GwtFlow.getCurrent().fireAppEvent(AppEvents.LoginSuccess, null);
					}
					else {
						show();
						Window.alert("登录失败");
					}
				}
			});
		}
	}

}
