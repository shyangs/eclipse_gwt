package jquery4gwt.client;

import com.google.gwt.dom.client.Node;

public class JQuery {
	public static native JQueryObject $(String cssSelector)/*-{
		return $wnd.$(cssSelector);
	}-*/;

	public static native JQueryObject $(Node node) /*-{
		return $wnd.$(node);
	}-*/;	
}