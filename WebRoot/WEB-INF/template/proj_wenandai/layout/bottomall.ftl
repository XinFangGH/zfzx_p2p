<#include "/WEB-INF/template/${systemConfig.theme}/layout/newsitemap.ftl">
<#--<#include "/WEB-INF/template/${systemConfig.theme}/layout/newsite_demo.ftl">-->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/footer.ftl">
<#include "/WEB-INF/template/proj_wenandai/layout/navigation.ftl">
<script type="text/javascript">
	$(function () {  
		$("body").css("width", $(document).width() + "px");
		$(window).resize(function () {
			if ($(window).width() > 1200) {
		    	$("body").css("width", $(window).width() + "px");
		    } else {
		    	$("body").css("width", $(document).width() + "px");
		    }
		});
	})
</script> 
