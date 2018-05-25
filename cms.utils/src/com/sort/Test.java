package com.sort;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class Test {

	private ISort sort;
	private Integer[] unsort;

	Test(Integer[] unsort) {
		this.unsort = unsort;
	}

	public void setSort(ISort sort) {
		this.sort = sort;
	}

	public void printUnsortResult() {
		System.out.println("排序前：");
		System.out.println(StringUtils.join(this.unsort, ","));
	}

	public void printSortResult() {
		System.out.println((sort.getName()).concat(":"));
		System.out.println(StringUtils.join(sort.sort(Arrays.copyOf(this.unsort, this.unsort.length)), ","));
	}

	public static void main(String[] args) {
		Integer[] unsort = { 4, 6, 7, 9, 2, 12, 10, 3, 4345, 123, -3, -1 };
		Test test = new Test(unsort);
		test.printUnsortResult();
		test.setSort(new DirectlyInsert());
		test.printSortResult();
		test.setSort(new ShellSort());
		test.printSortResult();
		test.setSort(new SimpleSelectSort());
		test.printSortResult();
		test.setSort(new BubbleSort());
		test.printSortResult();
		test.setSort(new FastSort());
		test.printSortResult();
		test.setSort(new MergeSort());
		test.printSortResult();		
	}

}
