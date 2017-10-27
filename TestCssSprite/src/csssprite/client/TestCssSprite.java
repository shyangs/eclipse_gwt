package csssprite.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class TestCssSprite implements EntryPoint {
	int direction = 0;
	
	public void onModuleLoad() {
		final MyImageBundle myImageBundle = GWT.create(MyImageBundle.class);
		final SimplePanel p = new SimplePanel();
		final Button button = new Button("�ı�ͼƬ");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				switch (direction)
				{
				case 0:
					p.setWidget(myImageBundle.upIcon().createImage());
					break;
				case 1:
					p.setWidget(myImageBundle.downIcon().createImage());
					break;
				case 2:
					p.setWidget(myImageBundle.leftIcon().createImage());
					break;
				case 3:
					p.setWidget(myImageBundle.rightIcon().createImage());
					break;
				}
				direction++;
				if (direction == 4) {
					direction = 0;
				}
			}
		});
		
		RootPanel.get().add(p);
		RootPanel.get().add(button);
	}
}
