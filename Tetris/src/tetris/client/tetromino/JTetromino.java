package tetris.client.tetromino;

import tetris.client.TetrisColor;
import tetris.client.Tetromino;

public class JTetromino extends Tetromino {

	public JTetromino() {
		matrix[1][1] = true;
		matrix[1][2] = true;
		matrix[2][2] = true;
		matrix[3][2] = true;		
	}
	
	@Override
	public String getColor() {
		return TetrisColor.BLUE;
	}
}
