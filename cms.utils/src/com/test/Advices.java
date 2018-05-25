package com.test;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component("advices")
public class Advices {
    public void before(JoinPoint jp){
        System.out.println("----------前置通知----------");
        System.out.println();
    }
    
    public void after(){
        System.out.println("----------最终通知----------");
    }

}
