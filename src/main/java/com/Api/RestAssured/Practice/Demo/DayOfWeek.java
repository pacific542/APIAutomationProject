package com.Api.RestAssured.Practice.Demo;

public enum DayOfWeek {
	
	Monday(1,"Mon"),
	Tuesday(2,"Tue"),
	Wednesday(3,"Wed"),
	Thursday(4,"Thu"),
	Friday(5,"Fri"),
	Saturday(6,"Sat"),
	Sunday(7,"Sun");
	private int code;
	private String string;
	DayOfWeek(int code, String string) {
		this.string=string;
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getString() {
		return string;
	}
	
	
	
	

}
