package com.thirdPayInterface;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共接口类
 * @author userAdmin
 *
 */
public interface ThirdPayTypeInterface {
	//请求接口
	CommonResponse businessHandle(CommonRequst commonRequst);
	//回调处理
	CommonResponse businessReturn(Map maps,HttpServletRequest request);
	//获取对应的方法使用是页面还是直接连接
	Object[] checkThirdType(String businessType);
}
