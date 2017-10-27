package chapter10.helloext.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	void readData(AsyncCallback<List<Employee>> callback);
}
