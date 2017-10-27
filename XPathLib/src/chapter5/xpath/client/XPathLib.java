package chapter5.xpath.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class XPathLib implements EntryPoint {
	public void onModuleLoad() {
		XPath x = GWT.create(XPath.class);
		Document doc = XMLParser.parse("<?xml version='1.0' ?><root><a b='a'><b/><a>as</a></a></root>");
		List<Node> node = x.evaluate("//*[@b='a']", doc.getDocumentElement(), XPathConstants.NODESET);
		Window.alert(node.get(0).getNodeName());
		
	}
}
