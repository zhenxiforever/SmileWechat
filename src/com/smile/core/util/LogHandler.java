package com.smile.core.util;

public class LogHandler {

	private static LogHandler logger = new LogHandler();
	
	private LogHandler(){}
	
	public static LogHandler getLogger(){
		return logger;
	}
	
	public void info(String msg,String className){
		
	}
	
	public void debug(String msg,String className){
		
	}
	
	public void warn(String msg,String className){
		
	}
	
	public void warn(String msg,Exception exception){
		
	}
	
	public void error(String msg,Exception exception){
		
	}
}
