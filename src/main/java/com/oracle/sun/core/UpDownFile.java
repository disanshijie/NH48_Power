package com.oracle.sun.core;

import java.io.File;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.sftp.one.SftpUtil;

public class UpDownFile implements Runnable {

	protected static Logger logger = Logger.getLogger(UpDownFile.class);
	public String line;
	public int sort;
	
	public UpDownFile(String line,int sort) {
		this.line=line;
		this.sort=sort;
		
	}
	
	public void run() {
		String IP="";
		String USERNAME="";
		String PASSWD="";
		if(line!=null && line.length()>1) {
			String[] arr=line.split("\t");
			IP=arr[0];
			USERNAME=arr[1];
			PASSWD=arr[2];
			logger.info(IP+"#"+USERNAME+"#"+PASSWD+"#"+ UserDefined.UPFILEBASE+sort+"#"+ UserDefined.LINUXPATH);
			//上传
			uploadFile(IP,USERNAME,PASSWD, UserDefined.UPFILEBASE+sort, UserDefined.LINUXPATH);
		}else {
			logger.error("第"+sort+"行数据不合法");
			System.out.println("第"+sort+"行数据不合法");
		}
	}
	
	/**
     * OK
     * @Description
     * 返回类型 void
     * @throws Exception
     * @注	
     */
    public static void uploadFile(String ip,String name,String passwd,String src,String dest){
    	 ChannelSftp sftp;
		try {
			sftp = SftpUtil.getSftpConnect(ip, 22, name, passwd);
			logger.info("上传文件开始...\n"+ip+"+本地位置："+src+";Linux位置："+dest);
			System.out.println("上传文件开始..."+ip+"+本地位置："+src+";Linux位置："+dest);
			//上传文件夹
			SftpUtil.uploadFile(new File(src), dest, sftp);
			SftpUtil.exit(sftp);
			logger.info("成功："+ip+"+本地位置："+src+";Linux位置："+dest);
			System.out.println("成功："+ip+"+本地位置："+src+";Linux位置："+dest);
			
			Thread.currentThread().interrupt();  
		} catch (Exception e) {
			System.out.println("服务器"+ip+"文件("+src+")上传失败，原因："+e.getMessage());
			logger.error(e);
		}
       
    }

}
