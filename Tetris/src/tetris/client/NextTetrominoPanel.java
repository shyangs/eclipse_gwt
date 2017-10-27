package tetris.client;

import java.util.List;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;

public class NextTetrominoPanel extends Grid {
	
	Tetromino tetromino;
	
	
	public NextTetrominoPanel() {
		super(4, 4);
		tetromino = null;
		setCellPadding(0);
		setCellSpacing(0);

		for (int row = 0; row < getRowCount(); ++row) {
			for (int col = 0; col < getColumnCount(); ++col) {
				Image image = new Image(TetrisColor.BACKCOLOR);
				image.setWidth("12px");
				image.setHeight("12px");
				setWidget(row, col, image);
			}
		}
	}
	
	public Tetromino getTetromino() {
		return tetromino;
	}
	
	public void setTetromino(Tetromino tetromino) {
		List<Point> points;
		if (this.tetromino != null) {
			points = this.tetromino.getPoints();
			for (Point point : points) {
				Image image = (Image) getWidget(point.y, point.x);
				image.setUrl(TetrisColor.BACKCOLOR);
			}
		}
		this.tetromino = tetromino;
		points = tetromino.getPoints();
		for (Point point : points) {
			Image image = (Image) getWidget(point.y, point.x);
			image.setUrl(tetromino.getColor());
		}
	}
}
