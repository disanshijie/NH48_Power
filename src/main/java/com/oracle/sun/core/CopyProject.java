package com.oracle.sun.core;

import java.io.File;
import java.io.IOException;
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

/**
 * @url
 * @Description	创建项目副本
 * @author admin
 * @date 2018年6月28日
 * @version V1.0
 * @说明
 */
public class CopyProject {

	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
	protected static Logger logger = Logger.getLogger(CopyProject.class);
	
//	public static final String USERTXT="/NH48Pick/resource/user.txt";
	
	/**
	 * 创建文件副本
	 * 返回类型 void
	 * @throws IOException
	 * @注
	 */
	public static void copyDir() throws IOException {
		
		List<String> users = StreamUtil.readFileByLines(UserDefined.ACCOUNTPATH);
		if(users!=null && users.size()>0) {
			int num=0;
			for (String str : users) {
				if(str.contains(",")) {
					String parentPath=new File(UserDefined.WORKPATH).getParent();
					File temp=new File(parentPath+"/"+num);
					temp.mkdir();
					com.oracle.sun.common.utils.file.one.FileUtil.delDirNoThis(temp);
					//复制文件
					//FileUtil.copyFolder(new File(UserDefined.WORKPATH),new File(parentPath+"/"+num));
					FileUtil.copyFiles(new File(UserDefined.WORKPATH),new File(parentPath+"/"+num));
					//重写文件
					FileUtil.write(new File(parentPath+"/"+num+UserDefined.USERTXT), str,"utf-8");
					num++;
				}
			}
			
		}
	}
	
}
