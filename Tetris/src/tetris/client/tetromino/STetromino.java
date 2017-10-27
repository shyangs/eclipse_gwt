package tetris.client.tetromino;

import tetris.client.TetrisColor;

public class STetromino extends ZTetromino {

	public STetromino() {
		matrix = new boolean[4][4];
		matrix[0][2] = true;
		matrix[1][2] = true;
		matrix[1][1] = true;		
		matrix[2][1] = true;
	}
	
	@Override
	public String getColor() {
		return TetrisColor.GREEN;
	}
}


