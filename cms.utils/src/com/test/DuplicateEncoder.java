package com.test;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class DuplicateEncoder {

	static String encode(String word){
		return String.join("", Arrays.stream(word.split("")).map(i -> StringUtils.countMatches(word, i)>1 ?")":"(").collect(Collectors.toList()));
	  }
}
