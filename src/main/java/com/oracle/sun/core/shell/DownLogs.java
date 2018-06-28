package com.oracle.sun.core.shell;

import java.io.File;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.sftp.one.SftpUtil;
import com.oracle.sun.core.ThreadFactory.ExecSell;
import com.oracle.sun.core.ThreadFactory.SellOperate;

public class DownLogs {
	protected static Logger logger = Logger.getLogger(Buy.class);
	
	public static void start(String temp,int i) {
		new Thread(new ExecSell(temp,i,new SellOperate() {
			@Override
			public void exec(String ip,String name,String passwd) {
				DownFile( ip, name, passwd,UserDefined.NH48LOGPATH,UserDefined.LOG_DOWNLOAD_PATH+ip+".txt");
			}})).start();
	}
	
	/**
     * OK
     * @Description
     * 返回类型 void
     * @throws Exception
     * @注	
     */
    public static void DownFile(String ip,String name,String passwd,String src,String dest){
    	 ChannelSftp sftp;
		try {
			sftp = SftpUtil.getSftpConnect(ip, 22, name, passwd);
			logger.info("下载文件开始...\n"+ip+"+Linux位置："+src+";本地位置："+dest);
			System.out.println("下载文件开始...\n"+ip+"+Linux位置："+src+";本地位置："+dest);
			//下载文件夹
			SftpUtil.downloadDir(src, dest, sftp);
			
			SftpUtil.exit(sftp);
			logger.info("成功："+ip+"+Linux位置："+src+";本地位置："+dest);
			System.out.println("成功："+ip+"+Linux位置："+src+";本地位置："+dest);
			
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			System.out.println("服务器"+ip+"文件("+src+")下载失败，原因："+e.getMessage());
			logger.error(e);
		}
       
    }
}
