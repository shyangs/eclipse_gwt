package gwtflow.client;

import gwtflow.client.bizz.action.ApplyAction;
import gwtflow.client.bizz.action.LogoutAction;
import gwtflow.client.dialog.LoginDialog;
import gwtflow.client.service.GwtFlowService;
import gwtflow.client.service.GwtFlowServiceAsync;
import gwtflow.client.worklist.WorklistGrid;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtFlow implements EntryPoint {
	
	private Viewport viewport;
	
	private LoginDialog loginDialog;
	private OutlookPanel outlookPanel;
	private WorklistGrid worklistGrid;
	private static GwtFlow current;
	
	private Listener<BaseEvent> loginListener = new Listener<BaseEvent>(){
		@Override
		public void handleEvent(BaseEvent be) {
			outlookPanel.clearPanel("业务");
			GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
			service.canApply(new AsyncCallbackEx<Boolean>() {
				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						outlookPanel.addButton("业务", new ApplyAction());
						outlookPanel.layout();
					}
				}
			});
		}
	};	
	
	public GwtFlow() {
		loginDialog = new LoginDialog();
	}
	
	public void onModuleLoad() {
		current = this;
		viewport = new Viewport();
		
		viewport.setLayout(new BorderLayout());
		createOutlookBar();
		createWorklist();
		
		addAppListener(AppEvents.LoginSuccess, loginListener);
		
		RootPanel.get().add(viewport);
		loginDialog.show();
	}
	
	public static GwtFlow getCurrent() {
		return current;
	}
	
	public void logout() {
		GwtFlowServiceAsync service = GWT.create(GwtFlowService.class);
		service.logout(new AsyncCallbackEx<Integer>(){
			@Override
			public void onSuccess(Integer result) {
				loginDialog.show();
			}
		});
	}
	
	public void mask() {
		viewport.el().mask("请稍等...");
	}
	
	public void unmask() {
		viewport.el().unmask();
	}
	
	private void createWorklist() {
		BorderLayoutData borderData = new BorderLayoutData(LayoutRegion.CENTER);
		borderData.setMargins(new Margins(5));
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setLayout(new FitLayout());
		worklistGrid = WorklistGrid.create();
		panel.add(worklistGrid);
		PagingToolBar toolBar = new PagingToolBar(50);
		PagingLoader<?> loader = (PagingLoader<?>) worklistGrid.getStore().getLoader();
		toolBar.bind(loader);
		panel.setBottomComponent(toolBar); 
		viewport.add(panel, borderData);
	}
	
	private void createOutlookBar() {
		BorderLayoutData borderData = new BorderLayoutData(LayoutRegion.WEST, 150);
		borderData.setMargins(new Margins(5, 0, 5, 5));
		borderData.setSplit(true);
		borderData.setCollapsible(true);

		outlookPanel = new OutlookPanel("功能面板");
		outlookPanel.setBodyBorder(false);
		outlookPanel.addPanel("业务");
		outlookPanel.addPanel("系统");
		
		outlookPanel.addButton("系统", new LogoutAction());
		viewport.add(outlookPanel, borderData);
	}
	
	public void fireAppEvent(EventType type, BaseEvent event) {
		if (event == null) {
			viewport.fireEvent(type);
		}
		else {
			viewport.fireEvent(type, event);
		}
	}
	
	public void addAppListener(EventType type, Listener<BaseEvent> listener) {
		viewport.addListener(type, listener);
	}

	public WorklistGrid getWorklistGrid() {
		return worklistGrid;
	}
}
