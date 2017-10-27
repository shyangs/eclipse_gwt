package gwtflow.client;

import gwtflow.client.bizz.action.BizzAction;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.ButtonScale;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;

public class OutlookPanel extends ContentPanel {
	
	private Map<String, ContentPanel> panelMap;
	
	public OutlookPanel(String title) {
		panelMap = new HashMap<String, ContentPanel>();
		setHeading(title);
		setLayout(new AccordionLayout());
	}
	
	public ContentPanel addPanel(String title) {
		ContentPanel panel = new ContentPanel();
		panel.setHeading(title);
		VBoxLayout vLayout = new VBoxLayout();
		vLayout.setPadding(new Padding(5)); 
		vLayout.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
		panel.setLayout(vLayout);
		add(panel);
		panelMap.put(title, panel);
		return panel;
	}
	
	public void addButton(String title, BizzAction action) {
		ContentPanel panel = panelMap.get(title);
		Button button = new Button();
		button.setIconAlign(IconAlign.TOP);
		button.setScale(ButtonScale.LARGE);
		button.setAutoWidth(false);
		button.setWidth(70);
		button.setIcon(action.getIcon());
		button.setText(action.getText());
		button.addListener(Events.Select, action);
		panel.add(button, new VBoxLayoutData(new Margins(0, 0, 5, 0)));
	}
	
	public void clearPanel(String title) {
		ContentPanel panel = panelMap.get(title);
		panel.removeAll();
	}

}
