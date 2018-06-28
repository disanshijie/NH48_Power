package com.oracle.sun.common.constant;

import com.oracle.sun.common.utils.PropUtil;

public class UserDefined {

	public static String PROJECTPATH=System.getProperty("user.dir");
	//配置文件
	public static String BASEPROPERTY=PROJECTPATH+"/resource/params.properties";
	//ip地址
	public static String IPFILE=PROJECTPATH+"/resource/ip.txt";
	//登陆用户名
	public static String ACCOUNTPATH=PROJECTPATH+"/resource/account.txt";
	
	//上传文件位置
	public static String UPFILEBASE=PropUtil.getProperties(BASEPROPERTY, "UPFILEBASE");
	//保存路径
	public static String LINUXPATH=PropUtil.getProperties(BASEPROPERTY, "LINUXPATH");
	//工作空间
	public static String WORKPATH=PropUtil.getProperties(BASEPROPERTY, "WORKPATH");
	
	//本地保存
	public static String LOG_DOWNLOAD_PATH=PropUtil.getProperties(BASEPROPERTY, "LOG_DOWNLOAD_PATH");
	
	//项目相关
	//日志位置
	public static String NH48LOGPATH=LINUXPATH+"sunLog/NH48log/pickLuck/A-all.log";
	//user.txt位置
	public static final String USERTXT="/resource/user.txt";
	
}