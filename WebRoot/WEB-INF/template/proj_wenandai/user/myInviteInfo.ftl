<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


    <title>${systemConfig.metaTitle} - 我的邀请详细信息</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <script type="text/javascript">var m1="个人中心",m2="我的邀请",m3="个人资料";</script>
    <script type="text/javascript">
        function hid(){
            document.getElementById("recommandDiv").style.display="none";
        }
        window.onload=function() {
            $(".contents .finlist_title li").each(function(index) { //带参数遍历各个选项卡
                var className = $(this).attr("class");
                var show = $("#show").val();

                if(show=="Repayment"){
                    $(".contents .finlist_hide ol:eq(0)").show()
                            .siblings().hide();
                }
                if(show=="loan"){
                    $(".contents .finlist_hide ol:eq(1)").show()
                            .siblings().hide();
                }
                if(show=="invite"){
                    $(".contents .finlist_title li.active").removeClass("active"); //移除已选中的样式
                    $("#invite").addClass("active"); //增加当前选中项的样式
                    $(".contents .finlist_hided ol:eq(2)").show()
                            .siblings().hide();
                }
                $(this).click(function() {
                    //注册每个选卡的单击事件
                    $(".contents .finlist_title li.active").removeClass("active"); //移除已选中的样式
                    $(this).addClass("active"); //增加当前选中项的样式
                    //显示选项卡对应的内容并隐藏未被选中的内容
                    $(".contents .finlist_hide ol:eq(" + index + ")").show()
                            .siblings().hide();
                });
            });

            $('a[rel*=leanModal]').leanModal({ top : 200 });

	 <#list basisMaterialList as list>
         <#list list.webFinanceApplyUploadsList as uploadList>
	 	$("a[rel=group_${uploadList.materialId}]").fancybox({
            'titlePosition' : 'over',
            openEffect: 'elastic',
            'cyclic'        : true,
            'centerOnScroll': true,
            'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) {
                return '<span id="fancybox-title-over">' + (currentIndex + 1) +
                        ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>';
            },
            onStart: function () {
                $('body').css('overflow','hidden');
            },
            onClosed: function () {
                $('body').css('overflow','auto');
            }

        });
         </#list>
     </#list>

        }

        /*
        function copyText(){
             var obj = $('#recommandurl').text();
             // window.clipboardData.setData('Text',obj);
             copyToClipboard(obj);
             alert("复制成功!");
             }
        function copyToClipboard(maintext){
          if (window.clipboardData){
            window.clipboardData.setData("Text", maintext);
            }else if (window.netscape){
              try{
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            }catch(e){
                alert("该浏览器不支持一键复制！n请手工复制文本框链接地址～");
            }
            var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
            if (!clip) return;
            var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
            if (!trans) return;
            trans.addDataFlavor('text/unicode');
            var str = new Object();
            var len = new Object();
            var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var copytext=maintext;
            str.data=copytext;
            trans.setTransferData("text/unicode",str,copytext.length*2);
            var clipid=Components.interfaces.nsIClipboard;
            if (!clip) return false;
            clip.setData(trans,null,clipid.kGlobalClipboard);
          }
          alert("以下内容已经复制到剪贴板nn" + maintext);
        }*/



    </script>
    <style type="text/css">
        textarea {
            border-style: solid;
            border-width: 1px;
            border-color: #a8a8a8 #d8d8d8 #d8d8d8 #a8a8a8;
            border-radius: 3px;
            background-color: #f4f4f4;
            padding: 5px;
            overflow: auto;
            resize: none;
        }
    </style>

</head>
<body>
	 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 	 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
    <div class="user-cont">
        <!--start: 左侧页面-->
        <div class="user-name-nav fl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
					<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
        </div>
        <!-- 左侧页面 end -->
        <!-- 右侧主体内容 start  右侧内容宽740px -->
        <div class="user-cont-fr fr">
            <div class="user-cont-right">
                <div class="head-num">
                   <div class="num-icon fl">
                        <div class="icon-img icon-img-seven fl"></div>
                        <div class="num-data fl">
                            <#list  InvestprojectList as list>
                                <#if list_index == 0 >
                            <p class="normal">真实姓名</p>
                            <p class="middle"><em>${list.trueName}</em></p>
                        </div>
                    </div>
                    <div class="num-icon fl" style="width: 242px">
                        <div class="icon-img icon-img-eight fl"></div>
                        <div class="num-data fl">
                            <p class="normal">手机号码</p>
                            <p class="middle"><em>${list.telphone?substring(0,3)}****${list.telphone?substring(7)}</em></p>
                                </#if>
                            </#list>
                        </div>
                    </div>
                       <div class="num-icon fl" style="width: 242px">
                           <div class="icon-img icon-img-eight fl"></div>
                           <div class="num-data fl">
                               <p class="normal">总投资金额</p>
                               <p class="middle"><em>${totalMoney}</em></p>

                           </div>
                       </div>
                </div>
                <h2 class="big-tit">投资记录</h2>
                <div class="cont-list contents">
                    <div class="user-invite" style="margin:0 auto;padding:0;">
                        <!--此处放置内容-->
                        <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0" id="s2">
                            <tr>
                                <th align="center" style="width:190px;">标的名称</th>
                                <th align="center" >投标期限</th>
                                <th align="center" >投标金额(元)</th>
                                <th align="center" >投标时间</th>
                                <th align="center" >投标状态</th>
                            </tr>

                            <#list InvestprojectList as list>
                            <tr>
                                <td align="center">${list.bidName}</td>
                                <td align="center">
                                    ${list.periodTime!}${list.payaccrualType!}
                                </td>
                                <td align="center">${list.userMoney}</td>
                                <td align="center">${list.bidtime}</td>
                                <td align="center">
                                <#if list.stateShow == '已放款'>
                                    <p style="color: red ;font-size: 13px" >${list.stateShow}</p>
                                <#else >
                                    ${list.stateShow}
                                </#if>

                                </td>
                            </tr>
                            </#list>
                        </table>
                        <!-- 可选申请资料结束-->
                        <!--此处放置内容 结束-->
                    </div>
                </div>
                <div id="pager2"></div>
            </div>
        </div>
    </div>
    <!-- 弹出框 -->
    <div class="xy-lc-box" id="recommandDiv" style="display: none">
        <div class="xy-ui-box-mask"></div>
        <div class="xy-lc-box-show" style="width:500px;height:230px;top: 90%">
            <a class="xy-xbox-close" title="Close" href="javascript:void(0);" onClick="hid();" style="display: inline;">×</a>
            <div class="xy-lc-box-content" style="height:230px;">
                <h1>推荐码</h1>
                <br/>
                <div style="padding-left:64px">
                    <span style="font-size:14px;padding-left:50px">推荐码:</span>
                    <input style="width:154px;" id="recommand" name="recommandPerson" type="text" maxlength="16" size="20" class="colorful1" onblur="chkRecommand(this)" />
                    <span style="width:10px;float:right;padding-right:140px;padding-top:8px;display:none" id="recommandImg"></span>
                    <span style="width:100px;float:right;padding-right:58px;padding-top:8px;" id="recommandMark"></span>
                </div>
                <br/>
                <div class="xy-form-btn-box_mm">
                    <a style="padding-left:48px" href="#" class="xy-form-btn-link"><input type="button" onclick="saveRecommand()" value="保存" class="xy-form-btn-box-input"></a>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 弹出框 结束-->
<!--end: Container -->
<div id="signup" style="height:300px!important;">
    <div id="signup-ct">
        <div id="signup-header">
            <div style="float:left; width:330px; height:40px; padding:10px 0px 0px 30px"><span class="large blue">优惠券使用规则</span></div>
            <div id="lean_overlay_close" style="float:right;  padding:20px 0px 0px 0px;width:60px; height:20px; text-decoration:underline; cursor:pointer;" class="normal blue">关闭</div>
        </div>
        <div style="float:left; width:650px; height:300px; margin:10px 0px 0px 30px; overflow:auto; line-height:30px;">
            <span id="bidLoad">此规则只针对使用了优惠券投标的用户，即当投标金额大于等于使用返现券面值则优惠券面值乘以返现比例为返现金额，反之则以投标金额*返现比例为返现金额，当标的正式放款时平台一次性以红包形式返到用户的账户余额</span>
        </div>
    </div>
</div>
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</div>
</body>
</html>
<#--<script type="text/javascript" src="${base}/js/Calendar3.js"></script>-->
<script type="text/javascript" src="${base}/js/user/editUserInfo.js?version=1638120"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="${base}/js/upload.js"></script>

<#--引入jsrender 相关文件-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>
<script type="text/javascript" src="${base}/js/common.js"></script>








