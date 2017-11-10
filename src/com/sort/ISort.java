package com.sort;

public interface ISort {
	public Integer[] sort(Integer[] unsort);

	public String getName();

	default void swap(Integer[] unsort, int source, int target) {
		int temp = unsort[source];
		unsort[source] = unsort[target];
		unsort[target] = temp;
	}
}
