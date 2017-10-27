package chapter9.httprequesttest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


public class HttpRequestTest implements EntryPoint {

	public void onModuleLoad() {
		final TextBox data1 = new TextBox();
		final TextBox data2 = new TextBox();
		final Label result = new Label();
		Button queryButton = new Button("=");
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(data1);
		panel.add(new Label("+"));
		panel.add(data2);
		panel.add(queryButton);
		panel.add(result);
		RootPanel.get().add(panel);
		
		queryButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				AddServiceAsync add = GWT.create(AddService.class);
				add.add(
						Integer.valueOf(data1.getValue()),
						Integer.valueOf(data2.getValue()),
						new AsyncCallback<Integer>(){
							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Integer addResult) {
								result.setText(addResult.toString());
							}
						});

			}
		});
	}
}