package chapter4.client;

import com.google.gwt.core.client.EntryPoint;

public class JSNI_PrimitiveDataType implements EntryPoint {

	public void onModuleLoad() {
		boolean b = true;
		short s =1;
		int i = 1;
		float f = 1.1f;
		double d = 1.1;
		char c = 'c';
		String str = "sss";

		// JAVA 的 long 是64 bits 整數, JS 裡的整數只支援到32bits
//		long lng = 123;
//		showTypeMap(b, s, i, f, d, c, str, lng);
		
		showTypeMap(b, s, i, f, d, c, str);
	}
	
	public static native void showTypeMap(boolean b, short s, int i, float f, double d, char c, String str
//			, long lng
			) /*-{
		$wnd.console.log(
			"[JAVA] boolean => " + typeof b + " [JS]\n"
			+ "[JAVA] short => " + typeof s + " [JS]\n"
			+ "[JAVA] int => " + typeof i + " [JS]\n"
			+ "[JAVA] float => " + typeof f + " [JS]\n"
			+ "[JAVA] double => " + typeof d + " [JS]\n"
			+ "[JAVA] char => " + typeof c + " [JS]\n"
			+ "[JAVA] String => " + typeof str + " [JS]\n"
//			+ "[JAVA] long => " + typeof lng + " [JS]\n"
		);

	}-*/;
	

}
