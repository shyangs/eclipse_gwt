package chapter4.regex.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Pattern extends JavaScriptObject {
	
	protected Pattern() {
	}
	
	public final native static Pattern compile(String regex) /*-{
		return new RegExp(regex,"i");
	}-*/;
	
	public final native Matcher matcher(String str) /*-{
		return str.match(this);
	}-*/;
}
