package chapter4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;

public class JSNI_echarts implements EntryPoint {

	public void onModuleLoad() {
		int[] quantity = {5, 20, 36, 10, 10, 20};
		showEcharts( toJsArrayInt(quantity) );
	}
	
	public native void showEcharts(JsArrayInteger quantity) /*-{

		var doc = $doc;
		var ech = $wnd.echarts;
		
		//為ECharts準備一個具備大小（寬高）的Dom		
		doc.body.innerHTML = "<div id='main' style='width: 600px;height:400px; border:red 2px solid;'></div>";
        
        var myChart = ech.init(doc.getElementById('main'));

        // 指定圖表的配置項和數據
        var option = {
            title: {
                text: 'ECharts 入門示例'
            },
            tooltip: {},
            legend: {
                data:['銷量']
            },
            xAxis: {
                data: ["襯衫","羊毛衫","雪紡衫","褲子","高跟鞋","襪子"]
            },
            yAxis: {},
            series: [{
                name: '銷量',
                type: 'bar',
                data: quantity
//                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用剛指定的配置項和數據顯示圖表。
        myChart.setOption(option);
        
	}-*/;
	
	
	public JsArrayInteger toJsArrayInt(int[] input) {
		JsArrayInteger jsArrayInteger = JsArrayInteger.createArray().cast();
	    for (int i : input) {
	        jsArrayInteger.push(i);
	    }
	    return jsArrayInteger; 
	}

}
