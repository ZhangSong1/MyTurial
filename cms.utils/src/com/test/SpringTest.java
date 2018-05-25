package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:com/config/*.xml");
		TargetAop target=(TargetAop)context.getBean("targetAop");
		target.excute();
	}

}
