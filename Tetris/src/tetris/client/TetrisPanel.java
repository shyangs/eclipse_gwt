package tetris.client;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;

public class TetrisPanel extends Grid {

	public TetrisPanel(int row, int col) {
		super(row, col);
		setCellPadding(0);
		setCellSpacing(0);
		for (int x = 0; x < getRowCount(); ++x) {
			for (int y = 0; y < getColumnCount(); ++y) {
				Image image = new Image(TetrisColor.BACKCOLOR);
				image.setWidth("12px");
				image.setHeight("12px");
				setWidget(x, y, image);
			}
		}		
	}

	public String getColor(int x, int y) {
		Image image = (Image) getWidget(y, x);
		return image.getUrl();
	}

	public void setColor(int x, int y, String color) {
		Image image = (Image) getWidget(y, x);
		image.setUrl(color);
	}
}
