package jquery4gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Node;

public class JQuery4GWT implements EntryPoint {

	
	public void onModuleLoad() {
		com.google.gwt.xml.client.Document doc;
		JQuery.$(Document.get()).ready(new JQueryHandler() {
			@Override
			public void handler(Node thisEl) {
				JQuery.$("#runBtn").click(new JQueryHandler() {
					@Override
					public void handler(Node thisEl) {
						JQuery.$("#panel").show("slow");
					}
				});
			}
		});
	}


}
