package com.oracle.sun.common.utils.sftp.two.impl;

import java.io.BufferedReader;
import java.io.IOException;

import com.oracle.sun.common.utils.sftp.two.HandelConsole;

public class HandelConsoleImp implements HandelConsole {

	private Integer method=0;
	public HandelConsoleImp(Integer method) {
		this.method= method;
	}

	public void exec(BufferedReader reader) {
		switch(method) {
			case 0:
				print(reader);
				break;
			case 1:
				print(reader);
				break;
			case 2:
				print(reader);
				break;
		}
	}
	
	public void print(BufferedReader reader) {
		String buf = null;
        try {
			while ((buf = reader.readLine()) != null){  
			 //  System.out.println(buf);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("----------------end--------------------");
	}

	

	
}
