package com.oracle.sun.common.constant;

import com.oracle.sun.common.utils.PropUtil;

public class UserDefined {

	public static String PROJECTPATH=System.getProperty("user.dir");
	
	public static String BASEPROPERTY="/src/main/resource/params.properties";
	//上传文件位置
	public static String UPFILEBASE=PropUtil.getProperties(PROJECTPATH+BASEPROPERTY, "UPFILEBASE");
	//保存路径
	public static String LINUXPATH=PropUtil.getProperties(PROJECTPATH+BASEPROPERTY, "LINUXPATH");
	//ip地址
	public static String IPFILE=PROJECTPATH+"/src/main/resource/ip.txt";
	
	
}