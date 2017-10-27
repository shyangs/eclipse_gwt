package tetris.client.tetromino;

import tetris.client.TetrisColor;
import tetris.client.Tetromino;

public class OTetromino extends Tetromino {

	public OTetromino() {
		matrix[1][1] = true;
		matrix[1][2] = true;
		matrix[2][1] = true;
		matrix[2][2] = true;
	}
	
	@Override
	public String getColor() {
		return TetrisColor.YELLOW;
	}
}
