package com.hurong.core.util;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	
	private static Log logger=LogFactory.getLog(FileUtil.class);
	
	public static String generateFilename(String originalFilename){
		
		SimpleDateFormat dirSdf=new SimpleDateFormat("yyyyMM");
		String filePre=dirSdf.format(new Date());
		
        String fileExt="";
        int lastIndex=originalFilename.lastIndexOf('.');
        //取得文件的扩展名
        if(lastIndex!=-1){
        	fileExt=originalFilename.substring(lastIndex);
        }

        String filename=filePre+"/"+UUIDGenerator.getUUID()+fileExt;
        
        return filename;
	}
	
	/**
	 * 把数据写至文件中
	 * @param filePath
	 * @param data
	 */
	public static void writeFile(String filePath,String data){
		FileOutputStream fos = null;
		OutputStreamWriter writer=null;
		try {
			fos = new FileOutputStream(new File(filePath));
			writer=new OutputStreamWriter(fos, "UTF-8");			
			writer.write(data);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			try {
				if(writer!=null){
					writer.close();
				}
				if (fos != null){
					fos.close();
				}
			} catch (Exception e) {
			}
		}		
	}
	
	/**
	 * 读取文件内容
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath){
		 StringBuffer buffer = new StringBuffer();
		// 读出这个文件的内容
		try{
			File file = new File(filePath);
		    FileInputStream fis = null;
		    BufferedReader breader=null;
		    try {
		      fis = new FileInputStream(file);
		      InputStreamReader isReader=new InputStreamReader(fis,"UTF-8");
		      breader=new BufferedReader(isReader);
		      String line;
		      while((line=breader.readLine())!=null) {
		        buffer.append(line);
		        buffer.append("\r\n");
		      }
		      breader.close();
		      isReader.close();
		      fis.close();
		      
		    } catch (FileNotFoundException e) {
		      logger.error(e.getMessage());
		    } catch (IOException e) {
		    	logger.error(e.getMessage());
		    }
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return buffer.toString();
	}
	
	
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
//			file = null;
		}
		return false;
	}
	
	
	
	public static String getExtention(String fileName){
		
		int pos = fileName.lastIndexOf(".");
		
		return fileName.substring(pos);
		
	}
	
	/**
	 * 上传文件并返回上传后的文件名
	 */
	public static String uploadForName(String uploadFileName, String savePath, File uploadFile) throws IOException {
		String newFileName = uploadFileName; //checkFileName(uploadFileName, savePath);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(savePath + newFileName);
//			BufferedImage bi = ImageIO.read(resFile);{
//				if(bi == null){
//				System.out.println(此文件不为图片文件&quot;);
//			}
			fis = new FileInputStream(uploadFile);
			
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return newFileName;
	}

	
	
	/**
	 * 返回上传的结果，成功与否
	 */
	public static boolean upload(String uploadFileName, String savePath, File uploadFile) {
		boolean flag = false;
		try {
			uploadForName(uploadFileName, savePath, uploadFile);
			flag = true;
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	
	
	
	public static boolean isImageFile(File file){
		try {
			
			/*ImageInputStream iis = ImageIO.createImageInputStream(file);//resFile为需被Iterator&lt;
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {//文件不是图片
				System.out.println("此文件不为图片文件");
				return false;
			}else{
				return true;
			}*/
			BufferedImage bufferedImage = ImageIO.read(file);
//			BufferedImage bufferedImage = ImageIO.read(new File("E/个人文件夹/记录.txt"));
			if(bufferedImage == null){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
