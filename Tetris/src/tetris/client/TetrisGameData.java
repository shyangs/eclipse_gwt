package tetris.client;

import java.io.Serializable;

public class TetrisGameData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final static int NORMAL_STATUS = 0;
	public final static int GAMEOVER_STATUS = -1;
	public final static int MAKETROUBLE_STATUS = 1;
	
	public TetrisGameData() {
		status = NORMAL_STATUS;
	}
	
	public int score;
	public int level;
	public String panelData;
	public int status;
}
