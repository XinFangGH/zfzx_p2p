/**
 * 生成年化收益率表单方法
 * @type 
 */
$(function () { 
	 var chart;
      var chart = {
                  chart: {  //整体控制
                        renderTo: 'container',  //图表容器的DIVbar:横向条形图
                        defaultSeriesType: 'spline', //可选，默认为line【line:折线;spline:平滑的线;area:区域图;bar:曲线图;pie:饼图;scatter:点状图等等;
                        marginRight: 130, //外边距控制 (上下左右空隙)
                        marginBottom: 25  //外边距控制
                 }, 
		         title: {  
		             text: '升升投宝年化利率趋势图',  
		             x: -10, 
		             align:'left',
		             style: { "color": "#333333", "fontSize": "16px" }
		             
		        },
		        credits: {
		        	enabled:false,  
		            text: 'HCharts.cn',
		            href: 'http://www.hcharts.cn'
		        },
		         xAxis: {          //y轴数据
		             title: { 
		                text: '日期' 
		            }
		        }, 
		       yAxis: {          //y轴数据
		            title: { 
		                text: '年化利率' 
		            }, 
		            plotLines: [{  //标线
		                value: 0, 
		                width: 1
		            }] 
		        }, 
		        tooltip: {        //数据点的提示框
		            formatter: function () { 
		                return '<b>每日' + this.series.name + '</b><br/>' + this.x + ': ' + this.y+'%'; 
		            }  //formatter需要一个回调函数，可以通过this关键字打点得到当前一些图表信息
		        }, 
		        legend: { 
		            layout: 'vertical', 
		            align: 'right', 
		            verticalAlign: 'top', 
		            x: -10, 
		            y: 100, 
		            borderWidth: 0 
		        }, 
		        
		        series: [{   //数据数组,json格式中name为这组数据的名字，data为这组数据的数组
		            name: '年化利率'
		        }] 
            }
    //异步请求数据
    var url = basepath + "/financeProduct/listPlFinanceProductRate.do";
    $.ajax({
        type:"POST",
        url:url,//提供数据的Servlet
        dataType : "JSON",
        success:function(responseText, statusText){
        	var xatrnames = [];
			var yvalidators = [];
			var list = JSON.parse(responseText.result);
            for (var i =(list.length-1); i >=0; i--) {
                xatrnames.push([
                        list[i].intentDate
                    ]);
                yvalidators.push([
                         list[i].intentDate,
                         list[i].yearRate
                    ]);
            }
            chart.xAxis.categories = xatrnames
            chart.series[0].data = yvalidators;
            $('#container').highcharts(chart);
        },
        error:function(e){
           alert(e);
        }
    });
});