package gwtflow.client.htmlcontrol;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class HtmlPanel extends HtmlContainer {
	
	public static final EventType HtmlReady = new EventType();
	
	public HtmlPanel() {
	}

	@Override
	public void setHtml(String html) {
		super.setHtml(html);
		fireEvent(HtmlReady);
	}

	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		renderHtmlElement(el().dom);
	}

	protected void renderHtmlElement(Element el) {
		int childCount = DOM.getChildCount(el);
		for (int i = 0; i < childCount; ++i) {
			Element child = DOM.getChild(el, i);
			Component component = ControlFactory.createControl(child, el);
			if (component != null) {
				getItems().add(component);
			}
			else {
				renderHtmlElement(child);
			}
		}
	}
}
