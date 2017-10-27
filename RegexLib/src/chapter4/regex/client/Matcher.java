package chapter4.regex.client;

import com.google.gwt.core.client.JsArrayString;

public class Matcher extends JsArrayString {
	protected Matcher() {
		
	}
	
	public final String group() {
		StringBuilder result = new StringBuilder(); 
		for (int i = 0; i < this.length(); ++i) {
			if (i != 0) {
				result.append("\n");
			}
			result.append(this.get(i));
		}
		return result.toString();
	}
	
	public final String group(int group) {
		return this.get(group);
	}
	
	public final int groupCount() {
		return this.length();
	}
}
