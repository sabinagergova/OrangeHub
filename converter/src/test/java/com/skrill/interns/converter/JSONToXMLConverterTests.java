package com.skrill.interns.converter;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skrill.interns.converter.JSONToXMLConverter;

public class JSONToXMLConverterTests {

	JSONToXMLConverter converter;

	@BeforeMethod
	public void initialise() {
		converter = new JSONToXMLConverter();
	}

	String input = null;
	String result = null;

	@Test
	public void givenJSONStringInputReturnXMLString() throws Exception {
		// GIVEN
		input = "{'foo':'bar','coolness':2.0,'altitude':39000,'pilot':{'firstName':'Buzz','lastName':'Aldrin'},'mission':'apollo 11'}";
		// WHEN
		result = converter.convertJSONtoXml(input);
		// THEN
		String test = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<XMLResult><altitude>39000</altitude><coolness>2.0</coolness><foo>bar</foo><mission>apollo 11</mission><pilot><firstName>Buzz</firstName><lastName>Aldrin</lastName></pilot></XMLResult>\r\n");
		Assert.assertTrue(test.equals(result));
	}
}
