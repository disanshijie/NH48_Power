package com.oracle.sun.main;

import org.apache.log4j.Logger;

import com.oracle.sun.common.constant.UserDefined;
import com.oracle.sun.core.FileUps;

public class StartUpFile {
	
	protected static Logger logger = Logger.getLogger(StartUpFile.class);
	
	public static void main(String[] args) {
		//getPath();
		
		FileUps.startUp(1);
	}
	
	public static void start() {
		
	}
	
	public static void getPath() {
		logger.info(UserDefined.LINUXPATH);
		logger.info(UserDefined.UPFILEBASE);
		logger.info(UserDefined.IPFILE);
	}
	
}
