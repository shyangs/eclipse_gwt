package tetris.client.tetromino;

import tetris.client.TetrisColor;
import tetris.client.Tetromino;

public class ITetromino extends Tetromino {

	public ITetromino() {
		matrix[2][0] = true;
		matrix[2][1] = true;
		matrix[2][2] = true;
		matrix[2][3] = true;		
	}
	
	@Override
	public String getColor() {
		return TetrisColor.LIGHTBLUE;
	}
}
