package tetris.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("tetris")
public interface TetrisServerService extends RemoteService {
	public final static int WAIT_ANOTHER = 1;
	public final static int START = 2;
	public final static int FULL = 3;
	
	Integer join();
	Boolean checkStart();
	TetrisGameData exchangeData(TetrisGameData data);
}
