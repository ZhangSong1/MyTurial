package com.test;

public class HumanReadableTime {

	  public static String makeReadable(int seconds) {
		    return String.join(":", ConvertToString(seconds/3600),ConvertToString((seconds/60)%60),ConvertToString(seconds%60));
		  }
	  
	  public static String ConvertToString(int time) {
		  if(time<=9) {
			  return "0"+time;
		  }else {
			  return String.valueOf(time);
		  }
	  }
}
