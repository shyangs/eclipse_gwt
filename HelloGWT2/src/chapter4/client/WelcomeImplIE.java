package chapter4.client;

import com.google.gwt.user.client.Window;

public class WelcomeImplIE extends WelcomeImpl {

	@Override
	public void greeting() {
		Window.alert("Hello GWT! @IE");
	}

}
