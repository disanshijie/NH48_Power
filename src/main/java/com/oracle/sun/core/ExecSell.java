package com.oracle.sun.core;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.sftp.two.HandelConsole;
import com.oracle.sun.common.utils.sftp.two.VersouSshUtil;
import com.oracle.sun.common.utils.sftp.two.impl.HandelConsoleImp;

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
			logger.info(IP+"#"+USERNAME+"#"+PASSWD+"#");
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
			String cmd = "";
			
			cmd +="pkill -9 java \n";
			
			cmd += "cd "+UserDefined.LINUXPATH+"\n";
			
			//nohup java -jar jarfilename.jar >/dev/null &
			cmd += " nohup java -jar NH48BuyTicket.jar >/dev/null & \n";
			
			//执行的命令，这里为查找目录下最近更新的文件名带有patterns的文件
			//ve.runCmd(cmd, "utf-8",new HandelConsoleImp(0));
			ve.runCmd(cmd, "utf-8",new HandelConsole() {
				public void exec(BufferedReader reader) {
					String buf = null;
			        try {
						while ((buf = reader.readLine()) != null){  
						   System.out.println(buf);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        System.out.println("----------------end--------------------");
				}
			});
			
		} catch (Exception e) {
			System.out.println("服务器"+ip+"执行失败，原因："+e.getMessage());
			logger.error(e);
		}
       
    }

}
