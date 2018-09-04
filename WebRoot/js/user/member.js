
	$(function(){
		var dengji = $("#dengji").text();
		if(dengji==""||dengji==0)
		{
			
				//得到缴费周期
				var year =$('input[name="year"]:checked').val();
				//得到系统时间
				
				var str="";
				var d=new Date();
				str +=d.getFullYear()+'-'; //获取当前年份 
				str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
				str +=d.getDate(); 
				var nowDuedate = str.split("-");
				var newTime = Number(nowDuedate[0])+Number(year);
				var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
				$("#oldDuedate").text(oldDuedate);
			
			var gradeMoney=$("#money_2").text();
			var year =$('input[name="year"]:checked').val();
			var money=gradeMoney*year;
			$("#buqi").text(0);
			$("#yearMoney").text(money);
			$("#countMoney").text(money);
		}
		if(dengji==2)
		{
			//得到缴费周期
			var year =$('input[name="year"]:checked').val();
			//修改缴费后会员到期时间oldDuedate
			var nowDuedate = $("#nowDuedate").text().split("-");
			var newTime = Number(nowDuedate[0])+Number(year);
			var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
			$("#oldDuedate").text(oldDuedate);
			
			//隐藏低级选项
			var gradeMoney=$("#money_2").text();
			var year =$('input[name="year"]:checked').val();
			$("#hydj").css("display","none");
			var money=gradeMoney*year;
			$("#buqi").text(0);
			$("#yearMoney").text(money);
			$("#countMoney").text(money);	
		}
		if(dengji==1)
		{
			//默认先选中升级
			//得到价差
			var grade =$('input[name="grade"]:checked').val();
			var gradeMoney="";
			if(grade==1)
			{
				gradeMoney=$("#money_1").text();
			}else
			{
				gradeMoney=$("#money_2").text();
			}
			//得到缴费周期
			var year =$('input[name="year"]:checked').val();
			//修改缴费后会员到期时间oldDuedate
			var nowDuedate = $("#nowDuedate").text().split("-");
			var newTime = Number(nowDuedate[0])+Number(year);
			var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
			$("#oldDuedate").text(oldDuedate);
			var day = $("#day").text();
			var money=gradeMoney*year;
			if(day=="已到期,请续费")
			{
				$("#buqi").text(0);
				$("#yearMoney").text(money);
				$("#countMoney").text(money);
			}else{
				var year =$('input[name="year"]:checked').val();
				var jc_money=$("#money_2").text()-$("#money_1").text();
				var bq_money=(Number(jc_money/365*day)).toFixed(2);
			
				$("#buqi").text(bq_money);
				$("#yearMoney").text(money);
				$("#countMoney").text(Number(money)+Number(bq_money));	
			}
		}
		$('input[name="grade"]').click(function(){
			var grade =$('input[name="grade"]:checked').val();
			var gradeMoney="";
			if(grade==1)
			{
				gradeMoney=$("#money_1").text();
			}else
			{
				gradeMoney=$("#money_2").text();
			}
			var dengji = $("#dengji").text();
			if(dengji==""||dengji==0)
			{
				//得到缴费周期
				var year =$('input[name="year"]:checked').val();
				//得到系统时间
				
				var str="";
				var d=new Date();
				str +=d.getFullYear()+'-'; //获取当前年份 
				str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
				str +=d.getDate(); 
				var nowDuedate = str.split("-");
				var newTime = Number(nowDuedate[0])+Number(year);
				var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
				$("#oldDuedate").text(oldDuedate);
			}else
			{
				//得到缴费周期
				var year =$('input[name="year"]:checked').val();
				//修改缴费后会员到期时间oldDuedate
				var nowDuedate = $("#nowDuedate").text().split("-");
				var newTime = Number(nowDuedate[0])+Number(year);
				var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
				$("#oldDuedate").text(oldDuedate);
			}
			var year =$('input[name="year"]:checked').val();
			var day = $("#day").text();
			var money = "";
			if(day=="已到期,请续费")
			{
				money=gradeMoney*year;	
				$("#buqi").text(0);
				$("#yearMoney").text(money);
				$("#countMoney").text(money);
			}else
			{
				if(dengji==1||dengji=="")
				{
					//等级1
					if(grade==1)
					{
						//续费
						money=gradeMoney*year;
						$("#buqi").text(0);
						$("#yearMoney").text(money);
						$("#countMoney").text(money);	
					}else
					{
						//升级
						//得到价差
						var jc_money=$("#money_2").text()-$("#money_1").text();
						var bq_money=(Number(jc_money/365*day)).toFixed(2);
						money=gradeMoney*year;
						$("#buqi").text(bq_money);
						$("#yearMoney").text(money);
						$("#countMoney").text(Number(money)+Number(bq_money));	
					}
				}
			}
		})
		$('input[name="year"]').click(function(){
			var grade =$('input[name="grade"]:checked').val();
			var gradeMoney="";
			if(grade==1)
			{
				gradeMoney=$("#money_1").text();
			}else
			{
				gradeMoney=$("#money_2").text();
			}
			var dengji = $("#dengji").text();
			if(dengji==""||dengji==0)
			{
				//得到缴费周期
				var year =$('input[name="year"]:checked').val();
				//得到系统时间
				
				var str="";
				var d=new Date();
				str +=d.getFullYear()+'-'; //获取当前年份 
				str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
				str +=d.getDate(); 
				var nowDuedate = str.split("-");
				var newTime = Number(nowDuedate[0])+Number(year);
				var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
				$("#oldDuedate").text(oldDuedate);
			}else
			{
				//得到缴费周期
				var year =$('input[name="year"]:checked').val();
				//修改缴费后会员到期时间oldDuedate
				var nowDuedate = $("#nowDuedate").text().split("-");
				var newTime = Number(nowDuedate[0])+Number(year);
				var oldDuedate=newTime+"-"+nowDuedate[1]+"-"+nowDuedate[2];
				$("#oldDuedate").text(oldDuedate);
			}
			var day = $("#day").text();
			var money = "";
			if(day=="已到期,请续费")
			{
				money=gradeMoney*year;	
				$("#buqi").text(0);
				$("#yearMoney").text(money);
				$("#countMoney").text(money);
			}else
			{
				if(dengji==1)
				{
					//等级1
					if(grade==1)
					{
						//续费
						money=gradeMoney*year;
						$("#buqi").text(0);
						$("#yearMoney").text(money);
						$("#countMoney").text(money);	
					}else
					{
						//升级
						//得到价差
						var jc_money=$("#money_2").text()-$("#money_1").text();
						var bq_money=(Number(jc_money/365*day)).toFixed(2);
						money=gradeMoney*year;
						$("#buqi").text(bq_money);
						$("#yearMoney").text(money);
						$("#countMoney").text(Number(money)+Number(bq_money));	
					}
				}else
				{
					//续费
					money=gradeMoney*year;
					$("#buqi").text(0);
					$("#yearMoney").text(money);
					$("#countMoney").text(money);	
				}
			}
		})
	})
