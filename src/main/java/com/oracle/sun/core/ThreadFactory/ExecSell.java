package com.oracle.sun.core.ThreadFactory;

import org.apache.log4j.Logger;

import com.oracle.sun.core.UpDownFile;

public class ExecSell implements Runnable{

	protected static Logger logger = Logger.getLogger(UpDownFile.class);
	public String line;
	public int sort;
	private SellOperate sop;
	
	public ExecSell(String line,int sort,SellOperate sop) {
		this.line=line;
		this.sort=sort;
		this.sop=sop;
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
			//回掉
			sop.exec(IP,USERNAME,PASSWD);
		}else {
			logger.error("第"+sort+"行数据不合法");
			System.out.println("第"+sort+"行数据不合法");
		}
	}
}
