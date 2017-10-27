package gwtflow.client.bizz.action;

import gwtflow.client.dialog.ApplyForm;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class ApplyAction implements BizzAction {
	@Override
	public AbstractImagePrototype getIcon() {
		return IconHelper.createPath(GWT.getHostPageBaseURL() + "images/apply.png", 24, 24);
	}

	@Override
	public String getText() {
		return "申请报销";
	}

	@Override
	public void handleEvent(BaseEvent be) {
		ApplyForm applyForm = ApplyForm.getInstance();
		applyForm.setTaskStatus("init");
		applyForm.show();
	}

}
