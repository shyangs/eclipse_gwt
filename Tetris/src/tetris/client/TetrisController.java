package tetris.client;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class TetrisController extends Timer implements NativePreviewHandler {

	private TetrisPanel panel;
	private HTML otherPanel;
	private ScorePanel otherScorePanel;
	private NextTetrominoPanel nextPanel;
	private ScorePanel scorePanel;
	private int level;
	private int score;
	private int speed;
	private boolean needMakeTrouble;
	private Tetromino tetromino;
	private boolean[][] matrix;
	
	private Timer joinTimer = new Timer() {
		@Override
		public void run() {
			TetrisServerServiceAsync service = GWT.create(TetrisServerService.class);
			service.checkStart(new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(Boolean result) {
					if (result.booleanValue()) {
						start();
						exchangeTimer.scheduleRepeating(1000);
					}
					else {
						joinTimer.schedule(500);
					}
				}
			});
		}
	};
	
	private Timer exchangeTimer = new Timer() {
		@Override
		public void run() {
			TetrisServerServiceAsync service = GWT.create(TetrisServerService.class);
			TetrisGameData data = new TetrisGameData();
			if (needMakeTrouble) {
				data.status = TetrisGameData.MAKETROUBLE_STATUS;
				needMakeTrouble = false;
			}
			data.score = score;
			data.level = level;
			data.panelData = panel.getElement().getInnerHTML();
			service.exchangeData(data, new AsyncCallback<TetrisGameData>(){
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(TetrisGameData result) {
					otherPanel.setHTML("<table cellSpacing=0 cellPadding=0>" + result.panelData + "</table>");
					otherScorePanel.setScore(result.score, result.level);
					if (result.status == TetrisGameData.GAMEOVER_STATUS) {
						Window.alert("you win");
						exchangeTimer.cancel();
						stop();
					}
					else if (result.status == TetrisGameData.MAKETROUBLE_STATUS) {
						makeTrouble();
					}
				}
			});
		}
	};
	

	public TetrisController(
			TetrisPanel panel, NextTetrominoPanel nextPanel, ScorePanel scorePanel,
			HTML otherPanel, ScorePanel otherScorePanel
			) {

		matrix = new boolean[Tetris.COL][Tetris.ROW];
		for (int row = 0; row < Tetris.ROW; ++row) {
			for (int col = 0; col < Tetris.COL; ++col) {
				matrix[col][row] = false;
			}
		}
		
		needMakeTrouble = false;
		speed = 1000;
		tetromino = null;
		this.otherPanel = otherPanel;
		this.otherScorePanel = otherScorePanel;
		this.panel = panel;
		this.nextPanel = nextPanel;
		score = 0;
		level = 1;
		this.scorePanel = scorePanel;
		Event.addNativePreviewHandler(this);
		
		
		TetrisServerServiceAsync service = GWT.create(TetrisServerService.class);
		service.join(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Integer result) {
				if (result.intValue() == TetrisServerService.START) {
					exchangeTimer.scheduleRepeating(1000);
					start();
				}
				else if (result.intValue() == TetrisServerService.WAIT_ANOTHER) {
					joinTimer.schedule(500);
				}
				else {
					Window.alert("游戏者已满");
				}
			}
		});		
	}

	public void start() {
		scheduleRepeating(speed);
	}
	
	public void stop() {
		cancel();
	}

	public void run() {
		gameStep();
	}	
	
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		if (! event.isCanceled()) {
			if (event.getTypeInt() == Event.ONKEYDOWN) {
				if (tetromino != null) {
					switch (event.getNativeEvent().getKeyCode()) {
					case KeyCodes.KEY_LEFT:
						moveLeft();
						break;
					case KeyCodes.KEY_RIGHT:
						moveRight();
						break;
					case KeyCodes.KEY_DOWN:
						moveDown();
						break;
					case KeyCodes.KEY_UP:
						if (event.getNativeEvent().getShiftKey()) {
							rotateRight();
						}
						else {
							rotateLeft();
						}
						break;
					case 32:
						drop();
						break;
					}
				}
			}
		}
	}
	
	private void drop() {
		hideTetromino();
		while (true) {
			tetromino.moveDown();
			if (! testValid()) {
				tetromino.moveUp();
				showTetromino();
				lockTetromino();
				break;
			}
		}
	}
	
	private void rotateRight() {
		hideTetromino();
		tetromino.rotateRight();
		if (!testValid()) {
			tetromino.rotateLeft();
		}
		showTetromino();
	}	
	
	private void rotateLeft() {
		hideTetromino();
		tetromino.rotateLeft();
		if (!testValid()) {
			tetromino.rotateRight();
		}
		showTetromino();
	}
	
	private void moveRight() {
		hideTetromino();
		tetromino.moveRight();
		if (!testValid()) {
			tetromino.moveLeft();
		}
		showTetromino();
	}	
	
	private void moveLeft() {
		hideTetromino();
		tetromino.moveLeft();
		if (!testValid()) {
			tetromino.moveRight();
		}
		showTetromino();
	}
	
	private void moveDown() {
		hideTetromino();
		tetromino.moveDown();
		if (testValid()) {
			showTetromino();
		} else {
			tetromino.moveUp();
			showTetromino();
			lockTetromino();
		}
	}

	private void gameStep() {
		if (tetromino == null) {
			tetromino = nextPanel.getTetromino();
			if (tetromino == null) {
				tetromino = Tetromino.createRandom();
			}
			nextPanel.setTetromino(Tetromino.createRandom());
			tetromino.left = 3;
			if (! testValid()) {
				Window.alert("game over");
				stop();
				tetromino = null;

				exchangeTimer.cancel();

				TetrisGameData data = new TetrisGameData();
				data.status = TetrisGameData.GAMEOVER_STATUS;
				data.score = score;
				data.level = level;
				data.panelData = panel.getElement().getInnerHTML();

				TetrisServerServiceAsync service = GWT.create(TetrisServerService.class);
				service.exchangeData(data, new AsyncCallback<TetrisGameData>(){
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(TetrisGameData result) {
						
					}
				});
			}
			else {
				showTetromino();
			}
		} else {
			moveDown();
		}
	}

	private void lockTetromino() {
		Set<Integer> lockRow = new HashSet<Integer>();
		for (int i = 0; i < tetromino.getPoints().size(); ++i) {
			Point pt = tetromino.getPoints().get(i);
			matrix[pt.x][pt.y] = true;
			if (!lockRow.contains(Integer.valueOf(pt.y))) {
				lockRow.add(Integer.valueOf(pt.y));
			}
		}

		int deleteRowCount = 0;
		Integer row = null;
		while ((row = minRow(lockRow)) != null) {
			if (!testRowGap(row)) {
				deleteRow(row);
				deleteRowCount ++;
			}			
		}
		
		if (deleteRowCount > 0) {
			score += deleteRowCount * deleteRowCount;
			if (score > level * 100) {
				level++;
				levelUp();
			}
			scorePanel.setScore(score, level);	
		}
		if (deleteRowCount > 1) {
			needMakeTrouble = true;
		}
		tetromino = null;
	}
	
	private void levelUp() {
		if (speed >= 200) {
			speed -= 100;
		}
		else if (speed == 100) {
			speed = 50;
		}
		stop();
		start();
	}
	
	private Integer minRow(Set<Integer> rows) {
		if (rows.size() == 0) {
			return null;
		}
		Integer resultRow = Integer.MAX_VALUE;
		for (Iterator<Integer> iter = rows.iterator(); iter.hasNext();) {
			Integer row = iter.next();
			if (row < resultRow) {
				resultRow = row;
			}
		}
		rows.remove(resultRow);
		return resultRow;
	}

	private boolean testRowGap(int row) {
		for (int col = 0; col < Tetris.COL; ++col) {
			if (matrix[col][row] == false) {
				return true;
			}
		}
		return false;
	}
	
	private void deleteRow(int deleteRow) {
		for (int row = deleteRow - 1; row >= 0; --row) {
			boolean allGap = true;
			for (int col = 0; col < Tetris.COL; ++col) {
				if (matrix[col][row]) {
					allGap = false;
				}
				matrix[col][row + 1] = matrix[col][row];
				panel.setColor(col, row + 1, panel.getColor(col, row));
			}
			if (allGap) {
				return;
			}
		}
	}
	
	private void makeTrouble() {
		hideTetromino();
		tetromino.moveDown();
		if (testValid()) {
			tetromino.moveUp();
		} else {
			tetromino.moveUp();
			showTetromino();
			lockTetromino();
			hideTetromino();
		}
		
		for (int row = 0; row < Tetris.ROW - 1; ++row) {
			for (int col = 0; col < Tetris.COL; ++col) {
				matrix[col][row] = matrix[col][row + 1];
				panel.setColor(col, row, panel.getColor(col, row + 1));
			}
		}
		for (int col = 0; col < Tetris.COL; ++col) {
			boolean hasEmpty = false;
			int r = Random.nextInt(2);
			if ((col == Tetris.COL - 1) && (! hasEmpty)) {
				hasEmpty = true;
				matrix[col][Tetris.ROW - 1] = false;
				panel.setColor(col, Tetris.ROW - 1, TetrisColor.BACKCOLOR);
			}
			else if (r == 1) {
				matrix[col][Tetris.ROW - 1] = true;
				int randomColor = Random.nextInt(7);
				switch(randomColor) {
				case 0:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.BLUE);
					break;
				case 1:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.GREEN);
					break;
				case 2:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.LIGHTBLUE);
					break;
				case 3:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.ORANGE);
					break;
				case 4:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.PURPLE);
					break;
				case 5:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.RED);
					break;
				case 6:
					panel.setColor(col, Tetris.ROW - 1, TetrisColor.YELLOW);
					break;
				}
			}
			else {
				hasEmpty = true;
				matrix[col][0] = false;
				panel.setColor(col, 0, TetrisColor.BACKCOLOR);
			}
		}
		showTetromino();
	}

	private void showTetromino() {
		for (int i = 0; i < tetromino.getPoints().size(); ++i) {
			Point pt = tetromino.getPoints().get(i);
			panel.setColor(pt.x, pt.y, tetromino.getColor());
		}
	}

	private void hideTetromino() {
		for (int i = 0; i < tetromino.getPoints().size(); ++i) {
			Point pt = tetromino.getPoints().get(i);
			panel.setColor(pt.x, pt.y, TetrisColor.BACKCOLOR);
		}
	}
	
	private boolean testValid() {
		for (int i = 0; i < tetromino.getPoints().size(); ++i) {
			Point pt = tetromino.getPoints().get(i);
			if (pt.y == Tetris.ROW) {
				return false;
			}
			if (pt.x == -1) {
				return false;
			}
			if (pt.x == Tetris.COL) {
				return false;
			}
			if (matrix[pt.x][pt.y]) {
				return false;
			}
		}
		return true;
	}
}
