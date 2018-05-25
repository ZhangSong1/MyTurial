package com.test;

import java.util.stream.LongStream;

public class ASum {

	public static Long MAX;
	
	public static long findNb(long m) {
		MAX = m;
		long start = 0;
		long end = Math.round(Math.pow(m, (double) 1 / 3))/2;				
		long half = end;
		while (sum(Math.round(half)) > m) {
			end = half;
			half = half / 2;
		}
		start = half;
		long result = LongStream.rangeClosed(start, end).parallel().filter(i -> {return sum(i) == MAX;}).findFirst().getAsLong();
	
		return result == 0?-1:result;
	}

	public static long sum(long arg) {
		long result = 0;
		while (arg > 0) {
			if (result > MAX)
				break;
			result = result + Math.round(Math.pow(arg, 3));
			arg--;
		}
		return result;
	}

	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		System.out.println(ASum.findNb(1700671554481060225L));
		System.out.println((System.currentTimeMillis()-begin));
	}

}
