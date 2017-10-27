package gwtflow.client.htmlcontrol;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class HtmlTextArea extends TextArea {
	
	private Element targetElement;
	
	public HtmlTextArea(Element targetElement) {
		this.targetElement = targetElement;
		setData("targetElement", targetElement);
	}
	
	protected void onRender(Element target, int index) {
		int childIndex = DOM.getChildIndex(target, targetElement);
		super.onRender(target, childIndex);
		String width = El.fly(targetElement).getStyleAttribute("width");
		setWidth(width);
		targetElement.getParentElement().removeChild(targetElement);
	}
}
