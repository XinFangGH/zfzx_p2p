package com.hurong.credit.util.freemarkerToWord;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;

public class WordtoPdfUtil {
	static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
	static final int wdFormatPDF = 17;// PDF 格式

	/**
	 * word转pdf
	 * @param wordSrc    word路径
	 * @param pdfSrc     另存为pdf后的路径
	 */
	public static void  wordToPdf(String wordSrc,String pdfSrc){
		    long start = System.currentTimeMillis();
		    ActiveXComponent app = null;
		    Dispatch docs=null;
		    try {
		    	System.runFinalizersOnExit(true);
		    	app = new ActiveXComponent("Word.Application");
		        app.setProperty("Visible", false);

		         docs = app.getProperty("Documents").toDispatch();
		        System.out.println("打开文档" + wordSrc);
		        Dispatch doc = Dispatch.call(docs,//
		                "Open", //
		                wordSrc,// FileName
		                false,// ConfirmConversions
		                true // ReadOnly
		                ).toDispatch();

		        System.out.println("转换文档到PDF" + pdfSrc);
		        File tofile = new File(pdfSrc);
		   	 	//如果输出目标文件夹不存在，则创建
		        if (!tofile.getParentFile().exists()){
		        	 tofile.getParentFile().mkdirs();
		        }
		        Dispatch.call(doc,//
		                "SaveAs", //
		                pdfSrc, // FileName
		                wdFormatPDF);

		        Dispatch.call(doc, "Close", false);
		        long end = System.currentTimeMillis();
		        
		        System.out.println("转换完成..用时：" + (end - start) + "ms.");
		    } catch (Exception e) {
		    	e.printStackTrace();
		        System.out.println("========Error:文档转换失败：" + e.getMessage());
		    } finally {
		        if (app != null){
		        	app.invoke("Quit", wdDoNotSaveChanges);
		        }
		        if(docs != null){
		        	 ComThread.Release();
		 	        ComThread.RemoveObject(docs);
		        }	
		       
		    }
			
		}
}
