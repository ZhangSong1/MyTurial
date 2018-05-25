package com.test;

import java.io.File;

public class FileUtils {

 public static void main(String[] args) {
	 File file =new File("D:\\code\\pdm\\codebase");
	 delete(file);
 }
 
 public static void delete(File file) {
	 if(file.isDirectory()) {
		 File[] childs=file.listFiles();
		 for(int i=0;i<childs.length;i++) {
			 delete(childs[i]);
		 }
	 }else {
		 if(file.getName().toLowerCase().endsWith(".bak")
				 || file.getName().toLowerCase().endsWith(".ca~")
				 ||file.getName().toLowerCase().startsWith("Ñ}Ñu -")
				 || file.getName().toLowerCase().endsWith(".rar")
				 || file.getName().toLowerCase().endsWith("~")
				 || file.getName().toLowerCase().endsWith(".jad")
				 || file.getName().toLowerCase().endsWith(".zip")
				 || file.getName().contains("$")
				 || file.getName().equals("vssver.scc")
				 || file.getName().toLowerCase().endsWith(".old")) {
			 file.deleteOnExit();
		 }
	 }
 }

}
