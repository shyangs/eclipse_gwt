package gwtflow.client.bizz.action;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public interface BizzAction extends Listener<BaseEvent> {
	AbstractImagePrototype getIcon();
	String getText();
}
