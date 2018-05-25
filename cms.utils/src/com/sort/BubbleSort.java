package com.sort;

public class BubbleSort implements ISort {

	@Override
	public String getName() {
		return "冒泡排序";
	}

	@Override
	public Integer[] sort(Integer[] unsort) {
		int size = unsort.length;
		while (size > 0) {
			for (int i = 0; i < size-1; i++) {
				if (unsort[i] > unsort[i + 1]) {
					swap(unsort, i, i + 1);
				}
			}
			--size;
		}
		return unsort;
	}
}
