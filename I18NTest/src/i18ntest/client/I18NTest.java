package i18ntest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class I18NTest implements EntryPoint {
	public void onModuleLoad() {
		Window.alert(displayDistance(5));
	}
	
	public String displayDistance(double distance) {
		MyString myString = GWT.create(MyString.class);
		return Double.toString(distance * myString.distanceFactor()) + myString.unit();
	}
}
