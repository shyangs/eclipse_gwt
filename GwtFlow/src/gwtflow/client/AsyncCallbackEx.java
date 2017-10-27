package gwtflow.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AsyncCallbackEx<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

	@Override
	public abstract void onSuccess(T result);
}
