package com.sort;

public class ShellSort implements ISort {
	@Override
	public String getName() {
		return "希尔排序";
	}
	@Override
	public Integer[] sort(Integer[] unsort) {
		int gap = 5;
		while (true) {
			for (int i = 0; i < gap; i++) {
				directSort(unsort, i, gap);
			}
			if (gap == 1) {
				break;
			} else {
				gap = gap >= 2 ? gap / 2 : gap;
			}
		}
		return unsort;
	}

	private void directSort(Integer[] unsort, int start, int gap) {
		for (; start < unsort.length;start = start + gap) {
			for (int n = 0; n < start;n = n + gap) {
				if (unsort[start] < unsort[n]) {
					int temp = unsort[n];
					unsort[n] = unsort[start];
					unsort[start] = temp;
				}				
			}
			
		}
	}

}
