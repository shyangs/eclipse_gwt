package chapter9.requestbuildertest.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;


public class RequestBuilderTest implements EntryPoint {

	public void onModuleLoad() {
		
		RequestBuilder request =
				new RequestBuilder(
						RequestBuilder.GET, GWT.getHostPageBaseURL() + "data.txt");
		
		request.setCallback(new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				if(response.getStatusCode() == 200){
					Window.alert(response.getText());
				}
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
			}
		});
		
		try {
			request.send();
		} catch (RequestException e) {
			e.printStackTrace();
		}
		
	}
}
