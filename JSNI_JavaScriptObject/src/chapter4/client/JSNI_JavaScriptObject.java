package chapter4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;

public class JSNI_JavaScriptObject implements EntryPoint {

	public void onModuleLoad() {
		JavaScriptObject myDog = createJavaScriptObject("Lucy", 6);
		webConsoleLog(myDog);
	}
	
	public native void webConsoleLog(JavaScriptObject dog) /*-{
		$wnd.console.log(dog);
	}-*/;
	
	public native JavaScriptObject createJavaScriptObject(String strName, int age) /*-{
		var mydog = {
			name : strName,
			age : age,
			children : [ "aaa", "bbb" ]
		};
		
		return mydog;
	}-*/;
	

}
