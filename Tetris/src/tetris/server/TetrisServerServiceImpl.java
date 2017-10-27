package tetris.server;

import tetris.client.TetrisGameData;
import tetris.client.TetrisServerService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TetrisServerServiceImpl extends RemoteServiceServlet implements TetrisServerService  {

	private static final long serialVersionUID = 1L;
	
	private String gamer1;
	private String gamer2;
	private TetrisGameData gameData1;
	private TetrisGameData gameData2;

	@Override
	public Integer join() {
		if (gamer1 == null) {
			gamer1 = getThreadLocalRequest().getSession().getId();
			gameData1 = new TetrisGameData();
			return TetrisServerService.WAIT_ANOTHER;
		}
		else if (gamer2 == null) {
			gamer2 = getThreadLocalRequest().getSession().getId();
			gameData2 = new TetrisGameData();
			return TetrisServerService.START;
		}
		else {
			return TetrisServerService.FULL;
		}
	}
	
	@Override
	public Boolean checkStart() {
		if (gamer2 == null) {
			return false;
		}
		else {
			return true;
		}
	}	

	@Override
	public TetrisGameData exchangeData(TetrisGameData data) {
		if (getThreadLocalRequest().getSession().getId().equals(gamer1)) {
			gameData1 = data;
			return gameData2;
		}
		else if (getThreadLocalRequest().getSession().getId().equals(gamer2)) {
			gameData2 = data;
			return gameData1;			
		}
		return null;
	}
}
