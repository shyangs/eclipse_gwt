package chapter9.hibernate.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void query(String name, AsyncCallback<UserItem> callback);
	void save(UserItem user, AsyncCallback<?> callback);
}
