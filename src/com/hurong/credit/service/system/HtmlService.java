package com.hurong.credit.service.system;

import java.io.File;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hurong.credit.model.system.HtmlDataMapVO;
import com.hurong.credit.util.MapAdapter;

@Path("/htmlService")
@Produces("application/json")
public interface HtmlService {

	
	// 获取公共数据
	public Map<String, Object> getCommonData(Map<String, Object> commonData);
	
	/**
	 * 根据Freemarker模板文件路径、Map数据生成HTML静态文件
	 * 
	 * @param templateFilePath
	 *            Freemarker模板文件路径
	 * 
	 * @param htmlFilePath
	 *            生成HTML静态文件存放路径
	 * 
	 * @param data
	 *            Map数据
	 * 
	 */
	public void buildHtml(String templateFilePath, String htmlFilePath,
			Map<String, Object> data);

	/**
	 * 生成baseJavascript文件
	 * 
	 */
	public void baseJavascriptBuildHtml();

	/**
	 * 生成首页HTML静态文件 cxf rs 发布方式
	 */
	@POST
	@Path("/indexBuildHtml")
	@Consumes("application/xml")
	public void indexBuildHtml(HtmlDataMapVO dataVo);
	
	
	/**
	 * 生成充值HTML静态文件 cxf webservices ws 发布方式
	 */
	@POST
	@Path("/rechargeBuildHtml")
	@Consumes("application/xml")
	public void rechargeBuildHtml(Map<String, Object> data);

	// cxf webservices ws 发布方式
	@WebMethod
	@XmlJavaTypeAdapter(MapAdapter.class)
	public void indexBuildHtml2(
			@WebParam @XmlJavaTypeAdapter(MapAdapter.class) Map<String, Object> data);

	/**
	 * 生成登录HTML静态文件
	 * 
	 */
	@POST
	@Path("/loginBuildHtml")
	@Consumes("application/xml")
	public void loginBuildHtml();

	/**
	 * 生成注册HTML静态文件
	 * 
	 */
	@POST
	@Path("/regBuildHtml")
	@Consumes("application/xml")
	public void regBuildHtml(HtmlDataMapVO dataVo);

	/**
	 * 根据Article对象生成文章内容HTML静态文件
	 * 
	 * @param article
	 *            文章
	 */
	@POST
	@Path("/articleContentBuildHtml")
	@Consumes("application/xml")
	public void articleContentBuildHtml(HtmlDataMapVO dataVo);
	
	/**
	 * 招标方案对象生成内容HTML静态文件
	 * 
	 * @param sign
	 */
	@POST
	@Path("/signSchemeContentBuildHtml")
	@Consumes("application/xml")
	public void signSchemeContentBuildHtml(HtmlDataMapVO dataVo);

	/**
	 * 错误页HTML静态文件
	 */
	public void errorPageBuildHtml();

	/**
	 * 权限错误页HTML静态文件
	 */
	public void errorPageAccessDeniedBuildHtml();

	/**
	 * 错误页500 HTML静态文件
	 */
	public void errorPage500BuildHtml();

	/**
	 * 错误页404 HTML静态文件
	 */
	public void errorPage404BuildHtml();

	/**
	 * 错误页403 HTML静态文件
	 */
	public void errorPage403BuildHtml();


}