package com.test;

public class DnaStrand {

	  public static String makeComplement(String dna) {
		  char[] chars=dna.toCharArray();
		  for(int i=0;i<chars.length;i++) {
			  chars[i]=replace(chars[i]);
		  }
		   return new String(chars);
		  }
	  
	  private static char replace(char input) {
		  switch(input) {
		  case 'A':return 'T';
		  case 'T':return 'A';
		  case 'C':return 'G';
		  case 'G':return 'C';
		  default:
			  return input;
		  }

	  }

}
