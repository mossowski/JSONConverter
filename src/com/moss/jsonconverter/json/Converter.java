package com.moss.jsonconverter.json;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Array;

public class Converter {

	public Converter() {

	}

	public void convertToJSON(Object model, String name) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sBuilder = new StringBuilder("{");
		Field[] fields = model.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			Object fieldValue = fields[i].get(model);
			sBuilder.append("\"");
			sBuilder.append(fieldName);
			sBuilder.append("\"");
			sBuilder.append(":");
			System.out.println(fieldValue.getClass());
			if (fieldValue.getClass().isArray()) {
				sBuilder.append(convertArray(fields[i].get(model)));
			}
			else {
				sBuilder.append("\"");
				sBuilder.append(fieldValue);
				sBuilder.append("\"");
			}
			if (i != fields.length - 1) {
				sBuilder.append(",");
			}
		}
		sBuilder.append("}");

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("json/" + name + ".json"), "utf-8"))) {
			writer.write(sBuilder.toString());
		} catch (IOException anException) {
			anException.printStackTrace();
		}
	}
	
	public String convertArray(Object array) throws IllegalArgumentException {
		StringBuilder sBuilder = new StringBuilder("[");
		int length = Array.getLength(array);
		for (int i = 0; i < length; i++) {
			Object value = Array.get(array, i);
			sBuilder.append("\"");
			sBuilder.append(value);
			sBuilder.append("\"");
			if (i != length - 1) {
				sBuilder.append(",");
			}
		}
		sBuilder.append("]");
		return sBuilder.toString();
	}

}
