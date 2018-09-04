package com.hurong.credit.action.materials;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.materials.Attachment;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.FileUploadUtil;
import com.hurong.credit.util.MyUserSession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;




public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = -3096800116651263134L;
	private String fileSizeLimit;
	private File oldFile;
	private Attachment attachment;
	private WebFinanceApplyUploads webFinanceApplyUploads;
	
	public void init(ServletConfig config) throws ServletException {
		this.fileSizeLimit = config.getInitParameter("fileSizeLimit");
	}
	
	public void destroy() {
		this.fileSizeLimit = null;
		super.destroy();
	}
	
	class MyFileRenamePolicy implements FileRenamePolicy {
		public File rename(File file) {
			oldFile = file;
			String fileSaveName = random()+"."+FilenameUtils.getExtension(file.getName());
			File result = new File(file.getParentFile(), fileSaveName);
			return result;
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId  = request.getParameter("userId");//得到当前用户的id
		BpCustMemberService bpCustMemberService = (BpCustMemberService) AppUtil.getBean("bpCustMemberService");
		//得到当前用户
		BpCustMember mem = bpCustMemberService.get(Long.valueOf(userId));
		HttpSession session = request.getSession();
		StringBuilder stringBuilder = new StringBuilder();
		  
		if(mem!=null){
			//文件保存位置，当前项目下的attachFiles\webfile
			String uploadDir = "attachFiles" + File.separatorChar + "webfile" + File.separatorChar;
			//String userId = String.valueOf(mem.getId());
			//获取项目发布目录
			String ctxDir = session.getServletContext().getRealPath(String.valueOf(File.separatorChar));
			//每个用户的材料保存到finaceapply下面，对应用的id的文件夹下
			String autoCreatedDateDir = "finaceapply" + File.separatorChar + userId+ File.separatorChar ;
			//若不存在文件夹，就创建文件夹
			if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
				ctxDir = ctxDir + File.separatorChar;
			}
			File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
			if (!savePath.exists()) {
				savePath.mkdirs();
			}
			String saveDirectory = ctxDir + uploadDir + autoCreatedDateDir;
			
			if (StringUtils.isBlank(this.fileSizeLimit.toString())) {
				this.fileSizeLimit = "80";// 默认100M
			}
			int maxPostSize = Integer.parseInt(this.fileSizeLimit) * 1024 * 1024;
			String encoding = "UTF-8";
			FileRenamePolicy rename = new MyFileRenamePolicy();
			MultipartRequest multi = null;
			try {
				multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, rename);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			// 输出
			Enumeration files = multi.getFileNames();
			String fileSavePath = "";
			File newFile = null;
			String contentType = "";
			JSONObject jsonObj = new JSONObject();
			String materialstype = request.getParameter("type");
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				contentType =  multi.getContentType(name);
				//System.out.println("ContentType:"+contentType);
				File f = multi.getFile(name);
				jsonObj = updateWebFinanceApply(f,userId,materialstype);
				Image imageSrc = ImageIO.read(f);
					
				if(!"zip".equals(contentType.toLowerCase())
						||!"rar".equals(contentType.toLowerCase())
						||!"7z".equals(contentType.toLowerCase())
						||imageSrc==null){
					
				}
				
				if (f != null) {
					newFile = f;
					String fileName = multi.getFilesystemName(name);
					String lastFileName = saveDirectory + "\\" + fileName;
					fileSavePath = uploadDir + autoCreatedDateDir + fileName;
					
				}
			}
			String oldFileName = oldFile.getName();
			
			
			
			//jsonObj = null;
			String returnData = "";
			//传回前台数据
			if(jsonObj!=null){
				//取出前台传来的数据
				String saveDataId = request.getParameter("saveDataId");
				jsonObj.accumulate("saveDataId", saveDataId);
				returnData = jsonObj.toString();
				if(returnData.isEmpty()){
					returnData = "{\"error\":\"system error\"}";
					//删除上传的文件
					newFile.delete();
				}
			}else{
				returnData = "{\"error\":\"system error\"}";
				//删除上传的文件
				newFile.delete();
			}
		}
	}
	
	private JSONObject updateWebFinanceApply(File file,String userId,String materialstype){
		//保存数据库
		//System.out.println("------------开始保存数据库-------------");
		attachment = new Attachment();
		JSONObject jsonObj = new JSONObject();
		
		//try {
			WebFinanceApplyUploadsService webFinanceApplyUploadsService = (WebFinanceApplyUploadsService) AppUtil.getBean("webFinanceApplyUploadsService");
			WebFinanceApplyUploads wf = webFinanceApplyUploadsService.getUserIdBystate(Long.valueOf(userId),materialstype);
			if (wf != null) {
				WebFinanceApplyUploads web = new WebFinanceApplyUploads();
				if (!"".equals(file.getName())) {
					//得到以前的文件名集合
					String oldFileNames = wf.getFiles();
					//新的文件名集合
					String newFileNames = "";
					if ("".equals(oldFileNames) || oldFileNames == null) {
						newFileNames = file.getName();
					} else {
						newFileNames = oldFileNames + "|" + file.getName();
					}
					web.setFiles(newFileNames);
				}
				web.setMaterialstype(materialstype);
				web.setId(wf.getId());
				web.setUserID(wf.getUserID());
				web.setStatus(1);
				web.setLastuploadtime(new Date());
				//修改
				boolean fag = webFinanceApplyUploadsService.update(web);
				if(fag){
					jsonObj = JSONObject.fromObject(attachment);
				}else{
					jsonObj = JSONObject.fromObject(webFinanceApplyUploads);
				}
			}else{
				jsonObj = JSONObject.fromObject(attachment);
			}
			
		/*} catch (Exception e) {
			jsonObj = JSONObject.fromObject(webFinanceApplyUploads);
		}*/
		return jsonObj;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public String getFileSizeLimit() {
		return fileSizeLimit;
	}

	public void setFileSizeLimit(String fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}
	private String random(){
		Calendar nowtime = new GregorianCalendar();  
		String random = String.valueOf((int)(Math.random() * (9999 - 1000)) + 1000);//生成的验证码
        String strDateTime=String.format("%04d", nowtime.get(Calendar.YEAR))+  
                String.format("%02d", nowtime.get(Calendar.MONTH)+1)+ 
                String.format("%02d", nowtime.get(Calendar.DATE))+  
                String.format("%02d", nowtime.get(Calendar.HOUR))+ 
                String.format("%02d", nowtime.get(Calendar.MINUTE))+  
                String.format("%02d", nowtime.get(Calendar.SECOND))+
                String.format("%03d", nowtime.get(Calendar.MILLISECOND))+random;  
        return strDateTime;
	}
}
