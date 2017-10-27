package chapter5.xmltest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class XMLTest implements EntryPoint {

	public void onModuleLoad() {
		Document xmlDocument = XMLParser.parse("<a><b>a<c>b<e/>c<b/>e</c>f</b>g</a>");
		Window.alert(getInnerText(xmlDocument.getDocumentElement()));
	}
	
	public String getInnerText(Element element) {
		StringBuilder result = new StringBuilder();
		NodeList list = element.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			Node node = list.item(i);
			short nodeType = node.getNodeType();
			if (nodeType == Node.TEXT_NODE) {
				Text text = (Text)node;
				result.append(text.getNodeValue());
			}
			else if (nodeType == Node.ELEMENT_NODE) {
				result.append(getInnerText((Element)node));
			}
		}
		return result.toString();
	}

}
