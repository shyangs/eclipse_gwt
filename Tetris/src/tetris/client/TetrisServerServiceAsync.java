package tetris.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TetrisServerServiceAsync {
	public void join(AsyncCallback<Integer> callback);
	public void checkStart(AsyncCallback<Boolean> callback);
	public void exchangeData(TetrisGameData data, AsyncCallback<TetrisGameData> callback);
}
