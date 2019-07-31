package com.luv2code.springdemo.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.shyam.security.SecurityConfig;

@Configuration
//@EnableAspectJAutoProxy
public class MySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {AppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		
		return new String[] {"/"};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		//super.customizeRegistration(registration);
		registration.setMultipartConfig(new MultipartConfigElement("/uploads", 21000, 41000, 0));
	}

}
