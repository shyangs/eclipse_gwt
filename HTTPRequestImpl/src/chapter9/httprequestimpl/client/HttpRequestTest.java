package chapter9.httprequestimpl.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ResponseTextHandler;
import com.google.gwt.user.client.Window;


public class HttpRequestTest implements EntryPoint {

	public void onModuleLoad() {
		
		HTTPRequestImpl request = GWT.create(HTTPRequestImpl.class);
		request.asyncGet(
				GWT.getHostPageBaseURL() + "data.txt",
				new ResponseTextHandler() {
					@Override
					public void onCompletion(String responseText) {
						Window.alert(responseText);
					}
				});
		
	}
}
