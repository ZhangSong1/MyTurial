package com.test;

import org.springframework.stereotype.Component;

@Component("targetAop")
public class TargetAop {
	public void excute() {
		System.out.println("ִ�з�����...");
	}
}
