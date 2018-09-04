
$( function(){

	$("#loadType, #payType").buttonset();
	
	var buzzRateSel = $("#buzzRateSel"), counRateSel = $("#counRateSel"), years = $("#years"),
		buzzRate = $("#buzzRate"), counRate = $("#counRate");
		
	//动态生成利率下拉框
	for( var key in window.houseLoanBuzzRate ){
		var item = window.houseLoanBuzzRate[ key ];
		var opt = $("<option value='" + key + "'>").text( item.title );
		if ( item.isdefault )
			opt.attr("selected", "selected" );
		
		buzzRateSel.append( opt );		
	}
	for( var key in window.houseLoanCounRate ){
		var item = window.houseLoanCounRate[ key ];
		var opt = $("<option value='" + key + "'>").text( item.title );
		if ( item.isdefault )
			opt.attr("selected", "selected" );
			
		counRateSel.append( opt );			
	}	

	//动态更改利率	
	buzzRateSel.change( function(){
		var v = buzzRateSel.val(), y = parseInt( years.val() ) <= 5 ? 0 : 1 ;
		if ( v == "-1" )
			buzzRate.val("0.00");
		else	
			buzzRate.val( (window.houseLoanBuzzRate[ v ].rate[ y ] * 100).toFixed(2) );	
	} );
	counRateSel.change( function(){
		var v = counRateSel.val(), y = parseInt(years.val() ) <= 5 ? 0 : 1 ;
		if ( v == "-1"  )
			counRate.val("0.00");
		else	
			counRate.val( (window.houseLoanCounRate[ v ].rate[ y ] * 100).toFixed(2) );	
	} );
	years.change( function(){
		counRateSel.change();
		buzzRateSel.change();
	} ).change();
	var years = $("#years"), month = $("#month");
	//计算期次
	function getTerm(){
		return parseInt(years.val() || "0") * 12 + parseInt(month.val() || "0");
	}
	years.keyup( function(){
		$("#term").text( getTerm() );
	} );
	month.change( function(){
		$("#term").text( getTerm() );
	} );
	
	//当利率更改后反馈下拉框
	buzzRate.blur( function(){
		counRate.find("option:selected").removeAttr("selected");
		var v = $(this).val(), y = parseInt( years.val() ) <= 5 ? 0 : 1;
		for( var key in houseLoanBuzzRate ){
			if ( (houseLoanBuzzRate[key].rate[ y ] * 100).toFixed(2) == v ){
				buzzRate.find("option[value='"+ key +"']").attr("selected", "selected" );
				return;				
			}
		}
	});
	counRate.blur( function(){
		counRate.find("option:selected").removeAttr("selected");
		var v = counRate.val(), y = parseInt( years.val() ) <= 5 ? 0 : 1;
		for( var key in houseLoanCounRate ){
			if ( (houseLoanCounRate[key].rate[ y ] * 100).toFixed(2) == v ){
				counRate.find("option[value='"+ key +"']").attr("selected", "selected" );
				return;				
			}
		}
	});		
	
	//隐藏不必须的行
	 $("input[name='radio']").change( function(){
	 	var v = $("input[name='radio']:checked").val();
		if ( v == "1" ){
			$(".buzzTr").show();
			$(".counTr").hide();
			if ( $("#loadBuzzAmt").val() == "0" )
				$("#loadBuzzAmt").val( "" );
			if ( $("#loadCounAmt").val() == "" )
				$("#loadCounAmt").val( "0" );
		}else if ( v == "2" ){
			$(".buzzTr").hide();
			$(".counTr").show();
			if ( $("#loadCounAmt").val() == "0" )
				$("#loadCounAmt").val( "" );
			if ( $("#loadBuzzAmt").val() == "" )
				$("#loadBuzzAmt").val( "0" );
		}else{
			$(".buzzTr").show();
			$(".counTr").show();
		}
	 } ).change();
	
	//重置按钮
	$("#resetbtn").click( function(){
		$(".list").height( "auto" );
		$("#container").children("dd").remove();
	} );	
	
	//提交表单
	$("#houseLoan").submit( function( e ){
		e.preventDefault();
		
		var BA = parseFloat( $("#loadBuzzAmt").val() ) * 10000,
			CA = parseFloat( $("#loadCounAmt").val() ) * 10000,
			N = getTerm(),
			payType = $("input[name='radioPay']:checked").val(),
			loadType = $("input[name='radio']:checked").val(),
			BR = parseFloat( buzzRate.val() ) /100 /12,
			CR = parseFloat( counRate.val() ) /100 /12 ;
	
		var  ct = $("#container");
		ct.children("dd").remove();
			;
		if ( isNaN(CA) ) CA = 0;
		if ( isNaN(BA) ) BA = 0;
		
		var totalInt = totalAmt = buzzIntAmt = counIntAmt = 0;
	
		//商业贷款
		if ( loadType == "1" ){
			CA = 0;
			$("#BuzzIntAmtTr, #CounIntAmtTr").hide();
		}else if ( loadType == "2" ){
		//公积金贷款
			BA = 0;	
			$("#BuzzIntAmtTr, #CounIntAmtTr").hide();
		}else{
			$("#BuzzIntAmtTr, #CounIntAmtTr").show();
		}
		
		var BList, CList;
	
		if (payType == "1") {
			//等额本息
			BList = averageInterest( N, BA, BR);
			CList = averageInterest( N, CA, CR);
		}else if (payType == "2"){
			//等额本金
			BList = averageCapital( N, BA, BR);
			CList = averageCapital( N, CA, CR);			
		}
		var DList = [];
		for (var i=0; i<N; i++) {
			var b = BList[i], c = CList[i];
			DList.push([b[0] + c[0],
						b[1] + c[1],
						b[2] + c[2],
						b[3] + c[3]
					  ]);
			buzzIntAmt += 	b[0];
			counIntAmt += 	c[0];	  
			totalInt += b[0] + c[0];
			totalAmt += b[2] + c[2];		  			
		}	
		
		var oFrag=document.createDocumentFragment();
		for (var i=0; i<DList.length; i++) {
			var tmp = $("<dd>").html(  "<div>" + (i+1) + "</div>" +
										"<div>" + DList[i][2].toFixed(2) + "</div>" +
										"<div>" + DList[i][0].toFixed(2) + "</div>" +
									   "<div>" + DList[i][1].toFixed(2) + "</div>" +
										"<div>" + Math.abs(DList[i][3]).toFixed(2) + "</div>" +	
										"<div class='clear'></div>")[0];
			oFrag.appendChild( tmp );				
		}
		$(".list").height( 200 );
		ct.append( oFrag );
		
		$("#BuzzIntAmt").val( buzzIntAmt.toFixed(2) );
		$("#CounIntAmt").val( counIntAmt.toFixed(2) );
		$("#intAmt").val( totalInt.toFixed(2) );
		$("#fullAmt").val( totalAmt.toFixed(2) );
		
		window.scrollTo( 0, 250 );
	} );
	
	//等额本金
	function averageCapital( N, M, R ){
		var list = [];
		var B = M / N, D = M;
		for (var i=0; i< N ; i++) {
			// Ai = M×（N－i+1）÷N×（R％÷12）
			D = i == 0 ? M : list[ i-1 ][3]; 
			var I = D * R ;
			list.push( [ I, B, B+I, D-B] );	
		}
		return list;			
	}
	
	//等额本息
	function averageInterest( N, M, R ){
		if ( R == 0 )
			return averageCapital( N, M, R );
		var list = [];
		//Ci= M×（R％÷12）×（1+ R％÷12）N÷[（1+ R％÷12）N—1]
		var C = M * R * Math.pow( 1 + R, N ) / ( Math.pow( 1+R, N ) -1 ), D = 0;
		if (isNaN(C)) C = 0;
		for (var i=0; i< N ; i++) {
			// I = 剩余本金 × 月利率
			D = i == 0 ? M : list[ i-1 ][3]; //剩余本金
			var I = D * R ;
			list.push( [ I, C-I, C, D-( C-I )] );	
		}	
		return list;			
	}	
} );
