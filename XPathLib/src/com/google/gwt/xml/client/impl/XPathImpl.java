package com.google.gwt.xml.client.impl;

import java.util.ArrayList;
import java.util.List;


import chapter5.xpath.client.XPath;
import chapter5.xpath.client.XPathConstants;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.impl.NodeImpl;

public abstract class XPathImpl extends XPath {
	
	@SuppressWarnings("unchecked")
	public <T> T evaluate(
			String expression, Element element, XPathConstants returnType) {
		if (returnType == XPathConstants.NODE) {
			JavaScriptObject jso =
				evaluateSingle(expression, ((NodeImpl)element).getJsObject());
			return (T)NodeImpl.build(jso);
		}
		else {
			JsArray<JavaScriptObject> nodeList =
				evaluateList(expression, ((NodeImpl)element).getJsObject());
			List<Node> result = new ArrayList<Node>();
			for (int i = 0; i < nodeList.length(); ++i) {
				result.add(NodeImpl.build(nodeList.get(i)));
			}
			return (T)result;
		}
	}
	
	protected abstract JavaScriptObject evaluateSingle(
			String expression, JavaScriptObject element);

	protected abstract JsArray<JavaScriptObject> evaluateList(
			String expression, JavaScriptObject element);
}
