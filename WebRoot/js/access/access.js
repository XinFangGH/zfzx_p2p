
var score=0;//分数

$(document).ready(function () {
	 $(".surveybox .radio").click(
        function () {
            var outsurvey = $(this).parents(".out-survey");
            var scroll_offset = outsurvey.offset();
            var scroll_height = outsurvey.outerHeight();
            var radiocou = outsurvey.find(".radio")
            var outradio = $(this).parents(".out-survey").find("input:radio:checked").length;
            if (outradio == 1) {
                $("html,body").animate({scrollTop: scroll_offset.top + scroll_height}, 800)
            }
        }
     );
     
     $("#survey").submit(function () {
        if ($('input:checked').length <12) {
            alert("请填写完后提交");
            return false;
        }
        $(".stretchbox input:checked").each(function(){
        	score=score+Number($(this).val());
        });
        $("#accessScore").val(score);
        return true;
     });
     
     $("#sb").click(function () {
        $("#survey").submit();
     });
});


/**
 * 用户请求评估方法
 */
function toAccess(){
	var bidId=$("#bidId").val();
	var bidType=$("#bidType").val();
	location.href=basepath+"user/toAccessBpCustMember.do?isAccess=0&bidId="+bidId+"&bidType="+bidType;
}

function toBuy(){
	var bidId=$("#bidId").val();
	var bidType=$("#bidType").val();
	if("pDir"==bidType || "bDir"==bidType || "bOr"==bidType || "pOr"==bidType){//散标
		location.href=basepath+"creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId="+bidId;
	}else if("plmm"==bidType){//D计划
		location.href=basepath+"creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId="+bidId;
	}else if("uplmm"==bidType){//U计划
		location.href=basepath+"creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId="+bidId+"&keystr=UPlan";
	}else if("claim"==bidType){//债权
		location.href=basepath+"creditFlow/financingAgency/alltransferinglistPlBidSale.do";
	}else if("experience"==bidType){//体验标
		location.href=basepath+"creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId="+bidId+"&keystr=experience";
	}
}