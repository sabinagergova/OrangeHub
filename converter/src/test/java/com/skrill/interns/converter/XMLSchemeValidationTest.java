package com.skrill.interns.converter;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XMLSchemeValidationTest {

    XMLToJSONConverter converter = new XMLToJSONConverter();
    String result;
    String test = "{\"businessCard\": {\n  \"address\": {\n    \"city\": \"4000 Stavanger\",\n    \"country\": \"Norway\",\n    \"zipCode\": 4000\n  },\n  \"firstName\": \"John\",\n  \"lastName\": \"Smith\",\n  \"profession\": \"Teacher\"\n}}";

    @Test
    public void givenXmlStringValidatingItWithASchemeReturningValidJason() {
        // GIVEN
        String XmlInput = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><businessCard><firstName>John</firstName><lastName>Smith </lastName>"
                + "<profession>Teacher </profession><address><zipCode>4000 </zipCode><city>4000 Stavanger</city><country>Norway</country></address></businessCard>";
        // WHEN
        try {
            result = converter.convertXMLtoJSON(XmlInput);
            System.out.println(result);
            System.out.println(test);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // THEN
        Assert.assertTrue(test.equals(result));
    }
}
