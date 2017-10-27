package tetris.client;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public class ScorePanel extends Grid {
	
	private Label scoreLabel;
	private Label levelLabel;
	
	public ScorePanel() {
		super(2, 2);
		scoreLabel = new Label("0");
		levelLabel = new Label("1");
		
		setWidget(0, 0, new Label("得分"));
		setWidget(0, 1, scoreLabel);
		setWidget(1, 0, new Label("等级"));
		setWidget(1, 1, levelLabel);
	}
	
	public void setScore(int score, int level) {
		scoreLabel.setText(Integer.toString(score));
		levelLabel.setText(Integer.toString(level));
	}
}
