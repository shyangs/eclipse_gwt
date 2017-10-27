package chapter4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class JSNI implements EntryPoint {

	public void onModuleLoad() {
		Window.alert(getUrl());
		test();
	}
	
	public static native void test() /*-{
		$wnd.alert(456);
		$wnd.console.log(789);
	}-*/;
	
	public native String getUrl() /*-{
		return $wnd.location.href;
//		return window.location.href;
	}-*/;

}
