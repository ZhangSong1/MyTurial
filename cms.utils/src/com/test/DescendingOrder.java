package com.test;

import java.util.Arrays;
import java.util.Collections;

public class DescendingOrder {

	  public static int sortDesc(final int num) {
		  String[] str=String.valueOf(num).split("");
		  Arrays.parallelSort(str,Collections.reverseOrder());
		  return Integer.valueOf(String.join("", str));
		  }

}
