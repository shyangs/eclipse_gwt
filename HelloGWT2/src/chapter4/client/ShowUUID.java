package chapter4.client;

import java.util.UUID;

import com.google.gwt.user.client.Window;

public class ShowUUID {
	public static void show() {
		UUID uuid = UUID.randomUUID();
		Window.alert(uuid.toString());
	}
}
