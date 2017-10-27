package demo2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class DemoGWT2 implements EntryPoint {
	public void onModuleLoad() {
		RootPanel.get().add(new HelloWorld("aa"));
	}
}


