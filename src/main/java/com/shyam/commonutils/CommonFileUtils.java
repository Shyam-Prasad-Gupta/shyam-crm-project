package com.shyam.commonutils;

import org.springframework.util.StringUtils;

public class CommonFileUtils {
	
	public static boolean isValidFileFormat(String fullFileName) {
		
		if(!StringUtils.isEmpty(fullFileName)) {
			String fileExtension = fullFileName.substring(fullFileName.lastIndexOf(".")+1);
			if(fileExtension.equalsIgnoreCase("txt") || fileExtension.equalsIgnoreCase("csv")){
				return true;
			}
		}
		return false;
	}
}
