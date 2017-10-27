package tetris.client.tetromino;

import tetris.client.TetrisColor;
import tetris.client.Tetromino;

public class ZTetromino extends Tetromino {

	private int status = 0;
	
	public ZTetromino() {
		matrix[0][1] = true;
		matrix[1][1] = true;
		matrix[1][2] = true;		
		matrix[2][2] = true;
	}
	
	@Override
	public String getColor() {
		return TetrisColor.RED;
	}
	
	@Override
	public void rotateLeft() {
		rotate();
	}

	@Override
	public void rotateRight() {
		rotate();
	}	
	
	private void rotate() {
		if (status == 0) {
			status = 1;
			super.rotateLeft();
		}
		else {
			status = 0;
			super.rotateRight();
		}
	}
}
