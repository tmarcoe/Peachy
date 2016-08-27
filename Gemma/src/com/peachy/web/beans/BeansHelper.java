package com.peachy.web.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansHelper {
	private ApplicationContext context = null;
	private final String path = "com/peachy/web/config/";
	
	public Object getBean(String file, String bean) {
		 context = new ClassPathXmlApplicationContext(path + file);
		 Object object = context.getBean(bean);
		 
		 ((ClassPathXmlApplicationContext)context).close();
		 
		 return object;
	}
	
}
