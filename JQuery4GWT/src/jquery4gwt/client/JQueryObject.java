package jquery4gwt.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JQueryObject extends JavaScriptObject {
	
	protected JQueryObject() {
	}
	
	public final native void ready(JQueryHandler handler) /*-{
		this.ready(function() {
			handler.@jquery4gwt.client.JQueryHandler::handler(Lcom/google/gwt/dom/client/Node;)(this);
		});
	}-*/;
	
	public final native void click(JQueryHandler handler) /*-{
		this.click(function() {
			handler.@jquery4gwt.client.JQueryHandler::handler(Lcom/google/gwt/dom/client/Node;)(this);
		});
	}-*/;
	


	public final native void show(String speed) /*-{
		this.show(speed);
	}-*/;
	
}