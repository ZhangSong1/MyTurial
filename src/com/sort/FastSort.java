package com.sort;

public class FastSort implements ISort {

	@Override
	public Integer[] sort(Integer[] unsort) {
		segmentSort(unsort, 0, unsort.length - 1);
		return unsort;
	}

	private void segmentSort(Integer[] unsort, int start, int end) {
		if (end <= start)
			return;
		int left = start;
		int right = end;
		while (left < right) {
			for (; right > left; right--) {
				if (unsort[left] > unsort[right]) {
					swap(unsort, left, right);
					break;
				}
			}
			for (; left < right; left++) {
				if (unsort[left] > unsort[right]) {
					swap(unsort, left, right);
					break;
				}
			}
		}
		segmentSort(unsort, start, left - 1);
		segmentSort(unsort, right + 1, end);
	}

	@Override
	public String getName() {
		return "快速排序";
	}

}
