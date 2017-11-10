package com.test;

import java.util.Arrays;

public class MinMax {

    public static int[] minMax(int[] arr) {
    	Arrays.sort(arr);
    	int[] result= {arr[0],arr[arr.length-1]};
    	return result;
    }
    
    public static void main(String[] args) {
    	int[] result= {1,4,8,3,4,33,112,234,44};
    	System.out.println(Arrays.toString(MinMax.minMax(result)));
    }

}
