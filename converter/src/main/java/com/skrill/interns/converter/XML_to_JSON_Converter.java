package com.skrill.interns.converter;

import org.json.JSONException;
import org.json.XML;

public class XML_to_JSON_Converter {

	public static String convertXMLtoJSON(String xml) throws JSONException {

        String output = XML.toJSONObject(xml).toString(2);
        return output;
    }
}
