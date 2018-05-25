package com.test;

import java.util.Arrays;

public class Kata {

    public static int findShort(String s) {
       return Arrays.stream(s.split("\\s")).mapToInt(String::length).min().getAsInt();
    }
    
    public static double findUniq(double arr[]) {
    	return Arrays.stream(arr).sorted().filter(i->Arrays.binarySearch(arr, i) == 1).count();
      }
    public static void main(String[] args) {
    	System.out.println(Kata.findUniq(new double[]{1, 1, 1, 2, 1, 1}));
    	double[] aa=new double[]{1, 1, 1, 2, 1, 1};
    	Arrays.sort(aa);
    	System.out.println(Arrays.binarySearch(aa, 1));
    }
}
