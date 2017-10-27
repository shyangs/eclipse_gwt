package chapter4.client;

import com.google.gwt.core.client.EntryPoint;

public class JSNI implements EntryPoint {

	public void onModuleLoad() {
		setBody();
//		Window.alert(getUrl());
	}
	
	public static native void setBody() /*-{
//		$wnd.console.log($wnd);
//		$wnd.console.log(typeof $doc.body.innerHTML);
//		$doc.body.innerHTML = "***<br>網址: " + $wnd.location.href + "<br><br>***";

		//為ECharts準備一個具備大小（寬高）的Dom
//		$doc.body.innerHTML = "<div id='main' style='width: 600px;height:400px;'></div>";
//		$wnd.console.log($doc.body.innerHTML);

		var doc = $doc;
		var ech = $wnd.echarts;
		
		doc.body.innerHTML = "<div id='main' style='width: 600px;height:400px;'></div>";
        
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
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用剛指定的配置項和數據顯示圖表。
        myChart.setOption(option);
        
        
	}-*/;
	
	public native String getUrl() /*-{
		return $wnd.location.href;
	}-*/;

}
