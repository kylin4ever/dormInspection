package com.haakimi.dormInspection.utils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static boolean checkAnyNotNull(String...strings){
		for(String s:strings){
			if(isNotBlank(s)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkAnyNull(String...strings){
		for(String s:strings){
			if(isBlank(s)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean equalsAny(String str,String[] arr){
		for(String s:arr){
			if(equals(s, str)){
				return true;
			}
		}
		return false;
	}

	public static boolean checkNullAndEmpty(Object obj){
		if(obj == null){
			return false;
		}
		return isNotBlank(obj.toString());
	}
	
}
