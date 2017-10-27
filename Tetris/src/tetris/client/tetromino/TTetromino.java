package tetris.client.tetromino;

import tetris.client.TetrisColor;
import tetris.client.Tetromino;

public class TTetromino extends Tetromino {

	public TTetromino() {
		matrix = new boolean[3][3];
		matrix[0][1] = true;
		matrix[1][1] = true;
		matrix[1][2] = true;		
		matrix[2][1] = true;
	}
	
	@Override
	public String getColor() {
		return TetrisColor.PURPLE;
	}
}