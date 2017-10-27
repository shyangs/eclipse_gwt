package com.google.gwt.xml.client.impl;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XPathImplStandard extends XPathImpl {
	protected native JavaScriptObject evaluateSingle(
			String expression, JavaScriptObject element) /*-{
		var evaluator = new XPathEvaluator();

		var xpathResult = evaluator.evaluate(
				expression,
				element,
				null,
                XPathResult.FIRST_ORDERED_NODE_TYPE,
                null);
		return xpathResult.singleNodeValue;
	}-*/;
	
	protected native JsArray<JavaScriptObject> evaluateList(
			String expression, JavaScriptObject element) /*-{
		var evaluator = new XPathEvaluator();
	
		var xpathResult = evaluator.evaluate(
				expression,
                element,
                null,
                XPathResult.ORDERED_NODE_SNAPSHOT_TYPE,
                null);
		var result = [];
		for (var i = 0; i < xpathResult.snapshotLength; ++i) {
			result.push(xpathResult.snapshotItem(i));
		}
		return result;
	}-*/;
}