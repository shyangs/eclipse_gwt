package chapter4.client;

import chapter4.client2.MyAlert;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class HelloGWT implements EntryPoint {

	public void onModuleLoad() {
		WelcomeImpl welcome = GWT.create(WelcomeImpl.class);
		welcome.greeting();

		MyAlert.alert();
		ShowUUID.show();

		JavaScriptObject o;
		Button welcomeButton = new Button("欢迎");
		welcomeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				WelcomeImpl welcome = GWT.create(WelcomeImpl.class);
				welcome.greeting();
			}
		});
		RootPanel.get().add(welcomeButton);
	}
	
	public int add(int a, int b) {
		return a + b;
	}
}
