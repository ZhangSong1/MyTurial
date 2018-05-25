package com.sort;

public class DirectlyInsert implements ISort{
	@Override
	public String getName() {
		return "直接插入排序";
	}
	@Override
	public Integer[] sort(Integer[] unsort){
		for(int i=0,n=unsort.length;i<n;i++){
			for(int j=0;j<i;j++){
				if(unsort[j]>unsort[i]){
					int temp=unsort[j];
					unsort[j]=unsort[i];
					unsort[i]=temp;
				}
			}
		}
		return unsort;
	}

}
