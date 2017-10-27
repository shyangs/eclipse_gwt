package gwtflow.client;

import java.util.Date;

import com.extjs.gxt.ui.client.core.DomQuery;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;

public class FlowViewContainer {
	private El flowViewContainer = null;
	private static FlowViewContainer instance;
	
	public static FlowViewContainer getInstance() {
		if (instance == null) {
			instance = new FlowViewContainer();
		}
		return instance;
	}
	
	private FlowViewContainer() {
		flowViewContainer = El.fly(DomQuery.selectNode("DIV[id=flowViewContainer]"));
	}

	public void show(int x, int y, int w, int h, String taskId) {
		flowViewContainer.makePositionable();
		flowViewContainer.setLeft(x);
		flowViewContainer.setTop(y);
		flowViewContainer.setWidth(w);
		flowViewContainer.setHeight(h);
		flowViewContainer.setVisible(true);
		flowViewContainer.updateZIndex(XDOM.getTopZIndex());		
		
		String cache = Long.toString(new Date().getTime()) + "." + Double.toString(Math.random());
		setData("/gwtflow/chart?id=" + taskId + "&cache=" + cache);
	}
	
	public void hide() {
		flowViewContainer.setWidth("0px");
		flowViewContainer.setHeight("0px");
		flowViewContainer.makePositionable(false);
	}	
	
	private native void setData(String url) /*-{
		$wnd.flowView.setData(url);
	}-*/;	
}
