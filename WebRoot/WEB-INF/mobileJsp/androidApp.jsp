
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form id="classFrom" action=${base}"/webPhone/addAndroidInfohomePage.do" style="border: 3px;" method="post">
        版本号：<input type="number" id="versionCode" name="appInfo.versionCode"/><br/>
        版本名称:<input type="text" id="versionName" name="appInfo.versionName"/><br/>
        版本详情：<input type="text" id="versionContent" name="appInfo.versionContent"/><br/>
        是否强制更新：<select name="appInfo.mustInstall">
        <option value="true">是</option>
        <option value="false" selected>否</option>
        </select><br/>
        下载地址：<input type="text" id="downUrl" name="appInfo.downUrl"/>
        <input type="submit" value="确认"/>
    </form>

</body>
</html>
