package com.oracle.sun.core;

import java.io.File;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.sftp.one.SftpUtil;
import com.oracle.sun.common.utils.sftp.two.VersouSshUtil;

public class ExecSell implements Runnable{

	protected static Logger logger = Logger.getLogger(UpDownFile.class);
	public String line;
	public int sort;
	
	public ExecSell(String line,int sort) {
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
			uploadFile(IP,USERNAME,PASSWD);
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
    public static void uploadFile(String ip,String name,String passwd){
    	 ChannelSftp sftp;
		try {
			VersouSshUtil ve=new VersouSshUtil(ip, 22, name, passwd);
			logger.info("执行命令...\n"+ip+"");
			System.out.println("执行命令...\n"+ip+"");
			String patterns="git";
			String cmd = "ls -ltr /usr/soft/git-2.16.0/|grep "+patterns+"|tail -1|awk {'print $NF'}\n";
			//执行的命令，这里为查找目录下最近更新的文件名带有patterns的文件
			ve.runCmd(cmd, "utf-8");
			
			logger.info("成功："+ip+"+");
			System.out.println("成功："+ip+"");
			
		} catch (Exception e) {
			System.out.println("服务器"+ip+"执行失败，原因："+e.getMessage());
			logger.error(e);
		}
       
    }

}
