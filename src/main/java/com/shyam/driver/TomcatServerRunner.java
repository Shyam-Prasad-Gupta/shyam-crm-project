package com.shyam.driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TomcatServerRunner {

	public static void main(String... args) {
		Tomcat tomcat = new Tomcat();
		String contextPath = "/CustomerTracker";
		String warFilePath = "/Volumes/shyam/MyProjects/MyJavaWorkspace/shyam-crm-project/target/spring-web-customer-tracker.war";
		try {
			tomcat.setPort(8080);
			tomcat.setBaseDir("temp");
			
			tomcat.getHost().setAppBase(".");
			tomcat.addWebapp(contextPath, warFilePath);
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// setup connection variables
//		String user = "root";
//		String pass = "shyam@MYSQL@100";
//
//		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
//		String driver = "com.mysql.jdbc.Driver";//com.mysql.cj.jdbc.Driver
//
//		// get connection to database
//		try {
//			//PrintWriter out = response.getWriter();
//
//			//out.println("Connecting to database: " + jdbcUrl);
//
//			Class.forName(driver);
//
//			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
//
//			//.out.println("SUCCESS!!!");
//
//			myConn.close();
//
//		} catch (Exception exc) {
//			exc.printStackTrace();
//			//throw new ServletException(exc);
//		}

	}

}
