package com.sort;

import java.util.LinkedList;
import java.util.Vector;

public class MergeSort implements ISort {

	@Override
	public Integer[] sort(Integer[] unsort) {
		int gap = 1;
		while(gap<unsort.length){
			
		for (int i = 0; i < unsort.length; i = i + 2*gap) {
			if (unsort[i] > unsort[i + gap]) {
				swap(unsort, i, i + gap);
			}
		}
		gap=2*gap;
		merge(unsort,0,gap*2,gap);
		}
		return unsort;
	}
		
	private void merge(Integer[] unsort,int start,int end,int gap){	
		LinkedList  aa=new LinkedList();
		aa.add(null);
		Vector  bb=new Vector();
		bb.add(null);
		for(int i=start;i<gap;i++){
			if(unsort[i+gap]<unsort[i]){
				swap(unsort,i,i+gap);
			}
		}
//		merge(unsort,start+2*gap,);
	}

	@Override
	public String getName() {
		return "归并排序";
	}

}
