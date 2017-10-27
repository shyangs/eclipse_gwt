package gwtflow.client.bizz.action;

import gwtflow.client.GwtFlow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class LogoutAction implements BizzAction {

	@Override
	public AbstractImagePrototype getIcon() {
		return IconHelper.createPath(GWT.getHostPageBaseURL() + "images/exit.png", 24, 24);
	}

	@Override
	public String getText() {
		return "注销";
	}

	@Override
	public void handleEvent(BaseEvent be) {
		GwtFlow.getCurrent().logout();
	}

}
