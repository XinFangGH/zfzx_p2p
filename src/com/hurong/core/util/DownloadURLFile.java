package com.hurong.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletResponse;

import com.hurong.credit.util.RsaHelper;


public class DownloadURLFile {
	private static final DownloadURLFile downloadURLFile = new DownloadURLFile();
	
	public static DownloadURLFile getInstance()
	{
		return downloadURLFile;
	}
		/** 
	     * 执行下载 
	     * @param rfPosit 远程文件开始下载位置 
	     */  
	    public static void start(long rfPosit,URL remoteFile,File storeFile){  
	        HttpURLConnection httpConn = null;  
	        InputStream httpIn = null;  
	        try{  
	            System.out.println("打开HttpURLConnection.");           
	            httpConn = (HttpURLConnection)remoteFile.openConnection();  
	            httpConn.setRequestProperty("User-Agent","NetFox");   
	            httpConn.setRequestProperty("RANGE","bytes="+rfPosit+"-");  
	              
	            System.out.println("得到HttpInputStream.");  
	            httpIn = httpConn.getInputStream();  
	            writeFile(httpIn,storeFile);  
	              
	            System.out.println("关闭HttpURLConnection.");  
	            httpConn.disconnect();  
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }  
	    }  
	      
	    /** 
	     * 从HttpInputStream中读数据并写到本地文件中 
	     * @param in HttpInputStream 
	     */  
	    private static void writeFile(InputStream in,File storeFile){  
	        RandomAccessFile fileOut = null;  
	        int buffer_len = 512;  
	        byte[] buffer = new byte[buffer_len];  
	        int readLen = 0;  
	          
	        try{  
	            System.out.println("写本地文件.");  
	              
	            fileOut = new RandomAccessFile(storeFile, "rw");  
	            fileOut.seek(fileOut.length());  
	              
	            while(-1 != (readLen = in.read(buffer, 0, buffer_len))){  
	                fileOut.write(buffer, 0, readLen);  
	            }     
	              
	            fileOut.close();  
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }  
	    }  
	    
	    /**
	     * 大文件下载
	     * @param path
	     * @param response
	     */
	    public static void downLoad(String path, HttpServletResponse response){
	    	final int BUFFER_SIZE = 0x3000;// 缓冲区大小为3M
	    	boolean isInline = false; // 是否允许直接在浏览器内打开
			OutputStream toClient = null;
			File f=new File(path);
			
	        /**
	         *
	         * map(FileChannel.MapMode mode,long position, long size)
	         *
	         * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode 类中所定义的
	         * READ_ONLY、READ_WRITE 或 PRIVATE 之一
	         *
	         * position - 文件中的位置，映射区域从此位置开始；必须为非负数
	         *
	         * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
	         *
	         * 所以若想读取文件后半部分内容，如例子所写；若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.READ_ONLY,
	         * f.length()*7/8,f.length()/8)
	         *
	         * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0,f.length())
	         *
	         */
	 
	        MappedByteBuffer inputBuffer=null;
	        long start = 0;
			try {
				
				String filename = new String(f.getName().getBytes(), "ISO8859-1");
				String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
				response.reset();
				 
				String inlineType = isInline ? "inline" : "attachment"; // 是否内联附件
				response.setHeader("content-disposition", "attachment;filename=\""+ filename + "\"");
				//response.setContentType("application/pdf");
				
				inputBuffer = new RandomAccessFile(f, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0,f.length());
			
	 
	        byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容
	 
	         start = System.currentTimeMillis();
	 
	        // if(inputBuffer.capacity()>BUFFER_SIZE){
	        for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
	 
	            if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
	 
	                for (int i = 0; i < BUFFER_SIZE; i++)
	 
	                    dst[i] = inputBuffer.get(offset + i);
	 
	            } else {
	 
	                for (int i = 0; i < inputBuffer.capacity() - offset; i++)
	 
	                    dst[i] = inputBuffer.get(offset + i);
	 
	            }
	 
	           
	 
	            toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream;charset=gbk");
				toClient.write(dst);
	        }
	         /*}else{
	        	 //dst[0]=inputBuffer;
	        	 toClient = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream;charset=gbk");
					toClient.write(inputBuffer.get(0));
	         }*/
	        
	        
	       
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					
					if (null != toClient) {
						toClient.close();
						toClient.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        long end = System.currentTimeMillis();
	 
	        System.out.println("读取文件文件内容花费：" + (end - start) + "毫秒");

	    }
}


