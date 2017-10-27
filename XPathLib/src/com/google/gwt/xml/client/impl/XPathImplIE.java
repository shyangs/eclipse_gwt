package com.google.gwt.xml.client.impl;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XPathImplIE extends XPathImpl {

	@Override
	protected native JavaScriptObject evaluateSingle(String expression,
			JavaScriptObject element) /*-{
		return element.selectSingleNode(expression);
	}-*/;

	@Override
	protected native JsArray<JavaScriptObject> evaluateList(String expression,
			JavaScriptObject element) /*-{
		var nodeList = element.selectNodes(expression);
		var result = [];
		for (var i = 0; i < nodeList.length; ++i) {
			result.push(nodeList.item(i));
		}
		return result;
	}-*/;
}
