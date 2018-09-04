package com.hurong.core.web.filter;


import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;


public class XssHttpServletRequestWrapper  extends HttpServletRequestWrapper{
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	@Override
	public String getParameter(String name) {

		return clearXss(super.getParameter(name));
	}

	@Override
	public String getHeader(String name) {

		return clearXss(super.getHeader(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		// 处理路径中的转义字符
		String[] values = super.getParameterValues(name);
		if(values!=null){
			String[] newValues = new String[values.length];

			for (int i = 0; i < values.length; i++) {
				newValues[i] = clearXss(values[i]);
			}
			return newValues;
		}
		return values;
	}

	// 清除路径中的转义字符
	public String clearXss(String value) {

		if (StringUtils.isEmpty(value)) {
			return value;
		}

		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "&#39;");
        value = value.replaceAll(" ", "");
        // 避免script 标签
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免src形式的表达式
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 删除单个的 </script> 标签
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 删除单个的<script ...> 标签
        scriptPattern = Pattern.compile("<script(.*?)>",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 eval(...) 形式表达式
        scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 e­xpression(...) 表达式
        scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 javascript: 表达式
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 vbscript:表达式
        scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");
        // 避免 onload= 表达式
        scriptPattern = Pattern.compile("onload(.*?)=",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        //避免sql注入
        scriptPattern = Pattern.compile("drop",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("sleep",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("insert",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("version",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("database",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("alter",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("delete",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("update",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("insert",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("select",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("show",Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		//避免sql注入
		scriptPattern = Pattern.compile("--");
		value = scriptPattern.matcher(value).replaceAll("");

		return value;
	}






}
