package chapter2.client;

import com.google.gwt.user.client.Window;

public class WelcomeImplFirefox extends WelcomeImpl {

	@Override
	public void greeting() {
		Window.alert("Hello GWT! @Firefox");
	}

}
