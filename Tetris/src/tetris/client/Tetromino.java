package tetris.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Random;

import tetris.client.tetromino.ITetromino;
import tetris.client.tetromino.JTetromino;
import tetris.client.tetromino.LTetromino;
import tetris.client.tetromino.OTetromino;
import tetris.client.tetromino.STetromino;
import tetris.client.tetromino.TTetromino;
import tetris.client.tetromino.ZTetromino;

public abstract class Tetromino {
	protected int left;
	protected int top;
	protected boolean[][] matrix;

	public static Tetromino createRandom() {
		int r = Random.nextInt(7);
		switch (r) {
		case 0:
			return new ITetromino();
		case 1:
			return new JTetromino();
		case 2:
			return new LTetromino();
		case 3:
			return new OTetromino();
		case 4:
			return new STetromino();
		case 5:
			return new TTetromino();
		case 6:
			return new ZTetromino();
		}
		throw new RuntimeException("bad random seed");
	}

	public Tetromino() {
		left = 0;
		top = 0;
		matrix = new boolean[4][4];
	}

	public void rotateLeft() {
		boolean[][] newMatrix = new boolean[matrix.length][matrix.length];
		for (int x = 0; x < matrix.length; ++x) {
			for (int y = 0; y < matrix.length; ++y) {
				newMatrix[y][matrix.length - 1 - x] = matrix[x][y];
			}
		}	
		matrix = newMatrix;
	}
	
	public void rotateRight() {
		boolean[][] newMatrix = new boolean[matrix.length][matrix.length];
		for (int x = 0; x < matrix.length; ++x) {
			for (int y = 0; y < matrix.length; ++y) {
				newMatrix[x][y] = matrix[y][matrix.length - 1 - x];
			}
		}
		matrix = newMatrix;
	}

	public abstract String getColor();

	public void moveLeft() {
		left --;
	}
	
	public void moveRight() {
		left ++;
	}	
	
	public void moveDown() {
		top ++;
	}
	
	public void moveUp() {
		top --;
	}	
	
	public List<Point> getPoints() {
		List<Point> result = new ArrayList<Point>();
		for (int x = 0; x < matrix.length; ++x) {
			for (int y = 0; y < matrix.length; ++y) {
				if (matrix[x][y]) {
					result.add(new Point(x + left, y + top));
				}
			}
		}
		return result;
	}



}
