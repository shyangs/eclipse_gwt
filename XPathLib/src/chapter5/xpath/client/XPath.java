package chapter5.xpath.client;

import com.google.gwt.xml.client.Element;

public abstract class XPath {
	public abstract <T> T evaluate(String expression, Element element, XPathConstants returnType);
}
