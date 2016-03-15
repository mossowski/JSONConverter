package com.moss.jsonconverter.application;

import com.moss.jsonconverter.json.Converter;
import com.moss.jsonconverter.model.Person;
import com.moss.jsonconverter.model.Sandwich;

public class Application {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Converter converter = new Converter();
		Person person = new Person();
		Sandwich sandwich = new Sandwich();
		
		converter.convertToJSON(person, "person");
		converter.convertToJSON(sandwich, "sandwich");
	}

}
