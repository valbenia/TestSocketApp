package com.bkspeed.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BaseHelper {
	
	public static void printLine(String text) {
		System.out.println(text);
	}
	
	public static void clearMemory() {
		System.gc();
	}
	
	public static String createMessage(String prefix, String content) {
		return prefix + "|" + content;
	}
	
	public static String getMessage(String message) {
		return Arrays.asList(message.split(message)).get(1);
	}
	
	public static String getMessageInputStream(DataInputStream input) throws IOException {
		int charactor;
		String text = "";
		while ((charactor = input.read()) != -1) {
		      text+= (char)charactor;
		      if(text.indexOf("<EOF>") > -1) {
		    	  break;
		      }
		}
		return text.replace("<EOF>","");
	}
	
	public static int randomPort() {
		Random generator = new Random();
		Integer port = generator.nextInt((8000-7000)+1)+7000;
		while(BaseUtils.listPort.contains(port)) {
			port = generator.nextInt((8000-7000)+1)+7000;
		}
//		BaseUtils.listPort.add(port.intValue());
		return port.intValue();
	}
}
