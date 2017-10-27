package gwtflow.client.htmlcontrol;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class HtmlInput extends TextField<String> {
	
	private Element targetElement;
	
	public HtmlInput(Element targetElement) {
		this.targetElement = targetElement;
		setData("targetElement", targetElement);
		
		String regex = targetElement.getAttribute("validation_regex");
		if (! regex.isEmpty()) {
			setRegex(regex);
		}
		String regexMsg = targetElement.getAttribute("validation_regex_msg");
		if (! regexMsg.isEmpty()) {
			getMessages().setRegexText(regexMsg);
		}		
	}
	
	protected void onRender(Element target, int index) {
		int childIndex = DOM.getChildIndex(target, targetElement);
		super.onRender(target, childIndex);
		String width = El.fly(targetElement).getStyleAttribute("width");
		if (! width.isEmpty()) {
			setWidth(width);
		}
		targetElement.getParentElement().removeChild(targetElement);
	}
}
