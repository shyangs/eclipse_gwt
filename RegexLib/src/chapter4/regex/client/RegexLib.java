package chapter4.regex.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;

public class RegexLib implements EntryPoint {
	public void onModuleLoad() {
		Pattern pattern = Pattern.compile("\\d{4}-0+(\\d{1,2})-\\d{1,2}");
		Matcher matcher = pattern.matcher("2008-08-08");
		Window.alert("奥运会的开幕式在" + matcher.group(1) + "月份");
	}
}
