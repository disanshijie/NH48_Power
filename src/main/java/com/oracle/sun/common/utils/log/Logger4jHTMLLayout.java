package com.oracle.sun.common.utils.log;

import org.apache.log4j.HTMLLayout;

/**
 * 
* @ClassName: Logger4jHTMLLayout
* @Description: TODO(这里用一句话描述这个类的作用)
* @author sun
* @date 2018年1月7日 下午7:27:14
* 	PatternLayout
* 	HTMLLayout
 */
public class Logger4jHTMLLayout extends HTMLLayout {   //HTMLLayout
	  
    @Override  
    public String getContentType() {  
        return "text/html;charset=utf-8";   
    }
}  