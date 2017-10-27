package gwtflow.client.htmlcontrol;

import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.user.client.Element;

public class ControlFactory {
	public static Component createControl(Element element, Element parent) {
		String childTagName = element.getTagName();
		if ("INPUT".equalsIgnoreCase(childTagName)) {
			HtmlInput field = new HtmlInput(element);
			field.render(parent);
			return field;
		} else if ("TEXTAREA".equalsIgnoreCase(childTagName)) {
			HtmlTextArea field = new HtmlTextArea(element);
			field.render(parent);
			return field;
		}
		return null;
	}
}
