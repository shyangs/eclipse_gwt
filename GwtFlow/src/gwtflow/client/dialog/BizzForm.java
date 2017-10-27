package gwtflow.client.dialog;

import gwtflow.client.AsyncCallbackEx;
import gwtflow.client.htmlcontrol.HtmlPanel;
import gwtflow.client.service.GwtFlowService;
import gwtflow.client.service.GwtFlowServiceAsync;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;

public class BizzForm extends Dialog {
	
	protected HtmlPanel htmlPanel;
	protected Map<String, TextField<?>> fieldMap;
	private String rightFilePath;
	private String status;
	private boolean htmlReady;
	
	private Listener<BaseEvent> readyListenerForRight = new Listener<BaseEvent>() {
		@Override
		public void handleEvent(BaseEvent be) {
			htmlPanel.removeListener(Events.Ready, this);
			GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
			service.loadFormRight(rightFilePath, status, new AsyncCallbackEx<Map<String, String>>(){
				@Override
				public void onSuccess(Map<String, String> result) {
					for (String fieldId : fieldMap.keySet()) {
						TextField<?> field = fieldMap.get(fieldId);
						String right = result.get(fieldId);
						field.setData("right", right);
						if (right == null) {
							field.setReadOnly(true);
							field.setAllowBlank(true);
							field.setEmptyText(null);
						}
						else if ("x".equals(right)) {
							field.setReadOnly(false);
							field.setAllowBlank(false);
							field.setEmptyText("必填");
						}
						else if ("w".equals(right)) {
							field.setReadOnly(false);
							field.setAllowBlank(true);
							field.setEmptyText("请填写信息");
						}
					}
				}
			});
		}
	};
	
	private Listener<BaseEvent> readyListenerForFieldMap = new Listener<BaseEvent>() {
		@Override
		public void handleEvent(BaseEvent be) {
			htmlPanel.removeListener(Events.Ready, this);
			fieldMap = new HashMap<String, TextField<?>>();
			for (Component component : htmlPanel.getItems()) {
				if (component instanceof TextField) {
					TextField<?> field = (TextField<?>) component;
					String dataName = ((Element)field.getData("targetElement")).getAttribute("data_name");
					field.setData("dataName", dataName);
					fieldMap.put(dataName, field);
				}
			}
		}
	};	
	
	public BizzForm() {
		setLayout(new FitLayout());
		htmlReady = false;
		htmlPanel = new HtmlPanel();
		htmlPanel.addListener(Events.Ready, readyListenerForFieldMap);
		htmlPanel.addListener(HtmlPanel.HtmlReady, new Listener<BaseEvent>(){
			@Override
			public void handleEvent(BaseEvent be) {
				htmlReady = true;
			}
		});
		add(htmlPanel);
	}

	@Override
	public void show() {
		if (! htmlReady) {
			htmlPanel.addListener(HtmlPanel.HtmlReady, new Listener<BaseEvent>(){
				@Override
				public void handleEvent(BaseEvent be) {
					htmlPanel.removeListener(HtmlPanel.HtmlReady, this);
					show();
				}
			});			
		}
		else {
			super.show();
			htmlPanel.fireEvent(Events.Ready);
		}
	}

	public void reset() {
		for (TextField<?> field : fieldMap.values()) {
			field.setValue(null);
			field.clearInvalid();
		}
	}
	
	public boolean isValid() {
		for (TextField<?> field : fieldMap.values()) {
			if (! field.isValid()) {
				return false;
			}
		}
		return true;
	}
	
	public String getFieldValue(String title) {
		return fieldMap.get(title).getValue().toString();
	}
	
	@Override
	protected void onButtonPressed(Button button) {
		if (Dialog.CANCEL.equals(button.getItemId())) {
			reset();
			hide();
		}
		else {
			super.onButtonPressed(button);
		}
	}
	
	protected void setRight(String rightFilePath, String status) {
		this.rightFilePath = rightFilePath;
		this.status = status;
		htmlPanel.addListener(Events.Ready, readyListenerForRight);
	}
}
