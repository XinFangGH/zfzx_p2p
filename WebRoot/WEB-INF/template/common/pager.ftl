<#-- 分页（Pager对象、链接URL、参数Map、最大页码显示数） -->
<#macro pager pager baseUrl parameterMap = {} maxShowPageCount = 6>

	<#local pageNumber = pager.pageNumber />
	<#local pageSize = pager.pageSize />
	<#local pageCount = pager.pageCount />
	<#local property = pager.property />
	<#local keyword = pager.keyword />
	<#local orderBy = pager.orderBy />
	<#local orderType = pager.orderType /> 
	
	<#local parameter = "" />
	<#if (pageSize != "")!>
		<#local parameter = parameter + "&pager.pageSize=" + pageSize />
	</#if>
	<#if (property != "")!>
		<#local parameter = parameter + "&pager.property=" + property />
	</#if>
	<#if (keyword != "")!>
		<#local parameter = parameter + "&pager.keyword=" + keyword />
	</#if>
	<#if (orderBy != "")!>
		<#local parameter = parameter + "&pager.orderBy=" + orderBy />
	</#if>
	<#--<#if (orderType != "")!>
		<#local parameter = parameter + "&pager.orderType=" + orderType />
	</#if> -->
	<#list parameterMap?keys as key>
		<#if parameterMap[key] != null && parameterMap[key] != "">
			<#local parameter = parameter + "&" + key + "=" + parameterMap[key] />
		</#if>
	</#list> 
	
	<#if baseUrl?contains("?")>
		<#local baseUrl = baseUrl + "&" />
	<#else>
		<#local baseUrl = baseUrl + "?" />
	</#if>
	<#local firstPageUrl = baseUrl + "pager.pageNumber=1" + parameter />
	<#local lastPageUrl = baseUrl + "pager.pageNumber=" + pageCount + parameter />
	<#local prePageUrl = baseUrl + "pager.pageNumber=" + (pageNumber - 1) + parameter />
	<#local nextPageUrl = baseUrl + "pager.pageNumber=" + (pageNumber + 1) + parameter />

	<#if maxShowPageCount <= 0>
		<#local maxShowPageCount = 6>
	</#if>
	
	<#local segment = ((pageNumber - 1) / maxShowPageCount)?int + 1 />
	<#local startPageNumber = (segment - 1) * maxShowPageCount + 1 />
	<#local endPageNumber = segment * maxShowPageCount />
	<#if (startPageNumber < 1)>
		<#local startPageNumber = 1 />
	</#if>
	<#if (endPageNumber > pageCount)>
		<#local endPageNumber = pageCount />
	</#if>

	<#if (pageCount > 0)>
		<div class="pager">
			<!--<li class="pageInfo">
				共 ${pageCount} 页
			</li> -->
			<#-- 首页 
			<#if (pageNumber > 1)>
				<li class="firstPage">
					<a href="${base}${firstPageUrl}">首页</a>
				</li>
			<#else>
				<li class="firstPage">
					<span>首页</span>
				</li>
			</#if>-->
			
			<#-- 上一页 -->
			<#if (pageNumber > 1)>
				<a href="${base}${prePageUrl}" class="ep-pages-ctrl">
					上一页
				</a>
				<!--<li class="prePage prePage1"></li>-->
			<#else>
				<!--<li class="prePage prePage1"></li>-->
					<span class="pre-bg">上一页</span>
				
			</#if>
			
			<#if (startPageNumber > 1)>
					<a  class="pagNum" href="${base}${baseUrl + "pager.pageNumber=" + (pageNumber - 2) + parameter}">...</a>
			</#if>
			
			<#list startPageNumber .. endPageNumber as index>
				<#if pageNumber != index>
						<a class="pagNum" href="${base}${baseUrl + "pager.pageNumber=" + index + parameter}">${index}</a>
					
				<#else>
					
						<a class="currentPage pagNum lightblue">${index}</a>
				</#if>
			</#list>
			
			<#if (endPageNumber < pageCount)>
					<a href="${base}${baseUrl + "pager.pageNumber=" + (pageNumber + 2) + parameter}">...</a>
			</#if>
		    
			<#-- 下一页 -->
			<#if (pageNumber < pageCount)>
				<!--<li class="nextPage nextPage1"></li>-->
					<a href="${base}${nextPageUrl}" class="ep-pages-ctrl news-list-page">
						下一页
					</a>
				
			<#else>
					<span class="next-bg news-list-page" style="weight='60';text-align: center;margin-left: 10px;" >下一页</span>
					
			</#if>
			
			<#-- 末页
			<#if (pageNumber < pageCount)>
				<li class="lastPage">
					<a href="${base}${lastPageUrl}">末页</a>
				</li>
			<#else>
				<li class="lastPage">
					<span>末页</span>
				</li>
			</#if> -->
		</div>
	</#if>

</#macro>

<#-- 文章内容分页 -->
<#macro articleContentPager article pageNumber maxShowPageCount = 6>

	<#local pageCount = article.pageCount />
	<#local htmlFilePathList = article.htmlFilePathList />
	<#local firstPageUrl = article.htmlFilePathList[0] />
	<#local lastPageUrl = article.htmlFilePathList[pageCount - 1] />
	<#local prePageUrl = article.htmlFilePathList[pageNumber - 2] />
	<#local nextPageUrl = article.htmlFilePathList[pageNumber] />

	<#if maxShowPageCount <= 0>
		<#local maxShowPageCount = 6>
	</#if>
	
	<#local segment = ((pageNumber - 1) / maxShowPageCount)?int + 1 />
	<#local startPageNumber = (segment - 1) * maxShowPageCount + 1 />
	<#local endPageNumber = segment * maxShowPageCount />
	<#if (startPageNumber < 1)>
		<#local startPageNumber = 1 />
	</#if>
	<#if (endPageNumber > pageCount)>
		<#local endPageNumber = pageCount />
	</#if>

	<#if (pageCount > 1)>
		<div class="pager">
			
				<a class="pageInfo">共 ${pageCount} 页</a>
			
			<#-- 首页 
			<#if (pageNumber > 1)>
				<li class="firstPage">
					<a href="${base}${firstPageUrl}">首页</a>
				</li>
			<#else>
				<li class="firstPage">
					<span>首页</span>
				</li>
			</#if>-->
			
			<#-- 上一页 -->
			<#if (pageNumber > 1)>
					<a  class="prePage prePage1" href="${base}${prePageUrl}">上一页</a>
			<#else>
				<a class="prePage prePage1"><span>上一页</span></a>
				
			</#if>
			
			<#if (startPageNumber > 1)>
				
					<a href="${base}${htmlFilePathList[startPageNumber - 2]}">...</a>
				
			</#if>
			
			<#list startPageNumber .. endPageNumber as index>
				<#if pageNumber != index>
						<a href="${base}${htmlFilePathList[index - 1]}">${index}</a>
				<#else>
					<a class="currentPage">
						<span>${index}</span>
						</a>
				</#if>
			</#list>
			
			<#if (endPageNumber < pageCount)>
				
					<a href="${base}${htmlFilePathList[endPageNumber]}">...</a>
				
			</#if>
		    
			<#-- 下一页 -->
			<#if (pageNumber < pageCount)>
				<!--<li class="nextPage nextPage1"></li>-->
					<a href="${base}${nextPageUrl}" class="news-list-page">下一页</a>
				
			<#else>
				<a class="nextPage nextPage1  news-list-page"><span>下一页</span></a>
			</#if>
			
			<#--末页 
			<#if (pageNumber < pageCount)>
				<li class="lastPage">
					<a href="${base}${lastPageUrl}">末页</a>
				</li>
			<#else>
				<li class="lastPage">
					<span>末页</span>
				</li>
			</#if> -->
		</div>
	</#if>

</#macro>