package com.smartSchoolService.pojo;

public class Test {

	public static void main(String[] args) {
		String str="12334";
		if (str.matches("[0-9]+") && str.length() > 2) {
			System.out.println("number");
		}
		else {
			System.out.println("char");
		}
		
	}

}
