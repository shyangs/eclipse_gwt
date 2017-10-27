package tetris.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Tetris implements EntryPoint {
	
	public final static int ROW = 20;
	public final static int COL = 10;
	
	
	public void onModuleLoad() {
		TetrisPanel tetrisPanel = new TetrisPanel(ROW, COL);
		HTML otherTetrisPanel = new HTML();
		otherTetrisPanel.setWidth("120px");
		ScorePanel otherScorePanel = new ScorePanel();
		NextTetrominoPanel nextPanel = new NextTetrominoPanel();
		ScorePanel scorePanel = new ScorePanel();

		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(nextPanel);
		vPanel.add(scorePanel);
		vPanel.add(new Label("对方"));
		vPanel.add(otherScorePanel);
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(tetrisPanel);
		hPanel.add(vPanel);
		hPanel.add(otherTetrisPanel);
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.setWidget(hPanel);
		RootPanel.get().add(decoratorPanel);
		
		new TetrisController(tetrisPanel, nextPanel, scorePanel, otherTetrisPanel, otherScorePanel);
//		controller.start();
	}
}
