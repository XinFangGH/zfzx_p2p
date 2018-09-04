$(function () {

    var CPIchart = new Highcharts.Chart({
        chart: {
            renderTo: 'divCPIReport',
            type: 'line'
        },
        title: {
            text: '单位(%)'
        },
        loading: {
            labelStyle: {
                fontWeight: 'bold',
                position: 'relative',
                top: '10em'
            }
        },
        xAxis: {
            type: 'string',
            gridLineWidth: 0, //网格(竖线)宽度
            labels: {
                style: { font: 'normal 12px Arial' }
            },
            categories: ['第一季度', '第二季度', '第三季度', '第四季度']
        },
        yAxis: {
            min: 0,
            gridLineWidth: 1,
            labels: false,
            title: false
        },
        legend: {
            enabled: false
        },
        tooltip: false,
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.1f}'
                }
            }
        },
        series: [{
            color: '#ffc502 ',
            data: [2.4, 2.4, 2.8, 2.9],
            states: {
                hover: false
            }
        }],
        exporting: false,
        credits: false
    });

    //计算
    $("#btnCalculate").click(function () {
        if (!valid()) {
            return;
        }

        var amount = $.trim($("#money").val());
        var TimeLong = $.trim($("#TimeLong").val());

        Calculate(amount, TimeLong);
    });

    //重置
    $("#btnReset").click(function () {
        $('#cpi_initial').show();
        $('#ResultShow').hide();
        $("#money").val('');
        $("#TimeLong_input").val('1');
        $("#tipMoney").hide();
        $("#TimeLong").find("option").eq(0).attr("selected", "selected");
    });
});




function valid() {
    var amount = $.trim($("#money").val());
    if (isNaN(amount) || !checkFloat(amount)) {
        $("#tipMoney").html('请输入正确的金额，例：1000');
        $("#tipMoney").show();
        return false;
    }
    else if (parseFloat(amount) < 100) {
        $("#tipMoney").html('输入的金额不能小于100元');
        $("#tipMoney").show();
        return false;
    }
    $("#tipMoney").hide();
    return true;
}

function Calculate(amount, TimeLong) {
	if (!valid()) {
        return;
    }
    var amountVal = parseFloat(amount);
    var yearVal = parseInt(TimeLong);

    //CPI计算
    var d = Math.pow(1 + 0.026, yearVal);
    var per = Round2(((amountVal - amountVal / d) / amountVal) * 100);
    var money = Round2(parseFloat(amountVal / d));
    $('#CPIResults').html(CreateResultHTML(yearVal, money, per));

    //紫薇金融投资计算
    var td = Math.pow(1 + 0.18, yearVal);
    var tdMoney = Round2(amountVal * td);
    var tdPer = Round2(((tdMoney - amountVal) / amountVal) * 100);
    $('#TDResults').html(CreateTDResultHTML(yearVal, tdMoney, tdPer));

	$('#cpi_initial').hide();
    $('#ResultShow').show();
}

function CreateResultHTML(TimeLong, money, per) {
	var html = '<div class="cpi_jg">' + TimeLong + '年后你的财富<span class="huan">将缩水为<br /><b>' + money + '</b>元，缩水 <b>' + per + '</b>%</span></div>根据当前CPI指数推算，数据仅供参考';
    return html;
}

function CreateTDResultHTML(TimeLong, money, per) {
	var html = '<div class="cpi_jg">' + TimeLong + '年后你的财富<span class="huan">将增值为<br /><b>' + money + '</b>元，增值 <b>' + per + '</b>%</span></div>根据当前紫薇金融指数推算，数据仅供参考';
    return html;
}