package tetris.client.tetromino;

import tetris.client.TetrisColor;
import tetris.client.Tetromino;

public class LTetromino extends Tetromino {

	public LTetromino() {
		matrix[0][2] = true;
		matrix[1][2] = true;
		matrix[2][2] = true;		
		matrix[2][1] = true;
	}
	
	@Override
	public String getColor() {
		return TetrisColor.ORANGE;
	}
}

