package demo2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HelloWorld extends Composite {
	interface MyUiBinder extends UiBinder<Widget, HelloWorld> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField Button mybutton;
	
	@UiHandler("mybutton")
	void onClick(ClickEvent e) {
		Window.alert("Hello, AJAX");
	}
	 
	public HelloWorld(String buttonText) {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
