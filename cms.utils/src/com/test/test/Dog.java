package com.test.test;

public class Dog {
	
	public int excute() {
		int count=5;
		try {
			return count++;
		}catch(Exception e) {
			System.out.println("invoke catch");
			return ++count;
		}finally {
			System.out.println("invoke finally");
			return count++;
		}
	}
	
	public static void main(String[] args) {
		Dog dog=new Dog();
		System.out.println(dog.excute());
		
	}
}
