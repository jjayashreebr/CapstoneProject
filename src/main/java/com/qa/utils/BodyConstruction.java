package com.qa.utils;

import java.util.HashMap;

public class BodyConstruction {
	
	public static String bodyForCreateUser(HashMap<String, Object> map) {
		return "email="+map.get("email").toString()
				+ "&password="+map.get("password").toString()
				+ "&name="+map.get("name").toString()
				+ "&firstname="+map.get("firstname").toString()
				+ "&lastname="+map.get("lastname").toString()
				+ "&address1="+map.get("address").toString()
				+ "&country="+map.get("country").toString()
				+ "&zipcode="+map.get("zipcode").toString()
				+ "&state="+map.get("state").toString()
				+ "&city="+map.get("city").toString()
				+ "&mobile_number="+map.get("mobile").toString();
	}

}
