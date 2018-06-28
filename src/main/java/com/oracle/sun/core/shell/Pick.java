package com.oracle.sun.core.shell;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.sftp.two.HandelConsole;
import com.oracle.sun.common.utils.sftp.two.VersouSshUtil;
import com.oracle.sun.core.ThreadFactory.ExecSell;
import com.oracle.sun.core.ThreadFactory.SellOperate;

public class Pick {
	protected static Logger logger = Logger.getLogger(Pick.class);
	
	public static void start(String temp,int i) {
		new Thread(new ExecSell(temp,i,new SellOperate() {
			@Override
			public void exec(String ip,String name,String passwd) {
				runCmd( ip, name, passwd);
			}})).start();
	}
	/**
     * OK
     * @Description
     * 返回类型 void
     * @throws Exception
     * @注	
     */
    public static void runCmd(String ip,String name,String passwd){
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
