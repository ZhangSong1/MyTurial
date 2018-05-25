package com.sort;

public class SimpleSelectSort implements ISort {
	@Override
	public String getName() {
		return "简单选择排序";
	}
	@Override
	public Integer[] sort(Integer[] unsort) {
		for (int j = 0, size = unsort.length; j < size; j++) {
			int first = j;
			int minIndex = j;
			for (int i = j; i < size; i++) {
				if (unsort[minIndex] > unsort[i]) {
					minIndex = i;
				}
			}
			swap(unsort, first, minIndex);
		}

		return unsort;
	}
}
