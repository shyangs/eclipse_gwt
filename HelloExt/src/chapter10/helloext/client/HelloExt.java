package chapter10.helloext.client;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloExt implements EntryPoint {
	
	public class TreeModelIconProvider implements ModelIconProvider<BaseTreeModel> {
		@Override
		public AbstractImagePrototype getIcon(BaseTreeModel model) {
			if ("叶子节点".equals(model.get("name"))) {
				return IconHelper.createPath(GWT.getHostPageBaseURL() + "leaf.png");
			}
			return null;
		}
	}

	
	
	public void onModuleLoad() {
		TreeStore<BaseTreeModel> store = new TreeStore<BaseTreeModel>();
		BaseTreeModel root = new BaseTreeModel();
		root.set("name", "根节点");

		BaseTreeModel leaf = new BaseTreeModel(root);
		leaf.set("name", "叶子节点");
		

		store.add(root, true);

		TreePanel<BaseTreeModel> tree = new TreePanel<BaseTreeModel>(store);
		tree.setDisplayProperty("name");
		tree.setIconProvider(new TreeModelIconProvider());
		
		Viewport viewport = new Viewport();
		viewport.setLayout(new FitLayout());
		viewport.add(tree);
		RootPanel.get().add(viewport);

	}
}
