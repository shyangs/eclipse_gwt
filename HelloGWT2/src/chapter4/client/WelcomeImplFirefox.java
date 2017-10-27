package chapter4.client;

import com.google.gwt.user.client.Window;

public class WelcomeImplFirefox extends WelcomeImpl {

	@Override
	public void greeting() {
		Window.alert("Hello GWT! @Firefox");
	}

}
