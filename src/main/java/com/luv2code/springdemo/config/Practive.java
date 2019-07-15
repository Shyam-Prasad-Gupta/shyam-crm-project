package com.luv2code.springdemo.config;

import java.util.*;
import java.lang.*;
import java.io.*;
class Practive
 {
	public static void main (String[] args)
	 {
	 //code
	 int testCases = 0;
	 Scanner scn = new Scanner(System.in);
	 testCases = scn.nextInt();
	 
	 //declaring the three variable for int, string and float values respectively.
	 int firstVar;
	 String secondVar;
	 float thirdVar;
	 /*while(testCases > 0){
	     
	     firstVar = scn.nextInt();
	     secondVar = scn.next();
	     thirdVar = scn.nextFloat();
	     
	     System.out.println(firstVar+" "+secondVar+" "+thirdVar);
	     testCases--;
	 }*/
	 File fl = new File("src/main/upload/shyam.txt");
	 try {
		fl.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }
}
