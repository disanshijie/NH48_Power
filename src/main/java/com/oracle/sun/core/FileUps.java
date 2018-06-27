package com.oracle.sun.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.common.utils.file.two.FileUtil;
import com.oracle.sun.common.utils.sftp.one.SftpUtil;

public class FileUps {
	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
	protected static Logger logger = Logger.getLogger(FileUps.class);
	
	public static void startUp(int plan) {
		List<String> lineStr=readFileByLines(UserDefined.IPFILE);
		if(lineStr!=null && lineStr.size()>0) {
			checkExePermission();
			if(plan==1) {
				upLoadIptxt(UserDefined.IPFILE);
			}else if(plan==2) {
				
			}else if(plan==3) {
				
			}
			
			for (int i = 0; i < lineStr.size(); i++) {
				String temp=lineStr.get(i);
				if(plan==1) {
					new Thread(new UpDownFile(temp,i)).start();
				}else if(plan==2) {
					new Thread(new ExecSell(temp,i)).start();
				}else if(plan==3) {
					
				}
				
				
			}
		}
	}
	
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static List<String> readFileByLines(String fileName) {
		File file = new File(fileName);
		List<String> str=new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String temp = null;
			// 一次读入一行，直到读入null为文件结束
			while ((temp = reader.readLine()) != null) {
				str.add(temp);
				// 显示行号
				//System.out.println("line " + line + ": " + temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					
				}
			}
		}
		return str;
	}
	
	public static void upLoadIptxt(String src){
		 ChannelSftp sftp;
			try {
				sftp = SftpUtil.getSftpConnect("59.110.224.8", 22, "root", "sjc@7ZXJPDZ");
				//上传文件
				SftpUtil.uploadFile(new File(src), "/root/NH48/data/ip/"+sdf.format(new Date())+"/", sftp);
				SftpUtil.exit(sftp);
			} catch (Exception e) {
				logger.error("格式错误"+e);
				System.exit(0);
			}
	}
	
	
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
