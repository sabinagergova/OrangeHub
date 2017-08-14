package com.skrill.interns.converter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;
import com.github.fge.jsonschema.util.JsonLoader;
import com.github.fge.jsonschema.main.JsonSchema;

public class JSONToXMLConverter {

    String resultXML;
    String jsonInput;

    public String convertJSONtoXml(String input) throws Exception {
        jsonInput = input;
        if (validateBySchema(jsonInput)) {
            JSON jsonString = JSONSerializer.toJSON(input);
            XMLSerializer serializer = new XMLSerializer();
            serializer.setTypeHintsEnabled(false);
            serializer.setRootName("XMLResult");
            resultXML = serializer.write(jsonString);
            resultXML = resultXML.replaceAll("<XMLResult>|</XMLResult>|<e>|</e>", "");
            return resultXML;
        } else {
            return "The input JSON doesn't fit the business card schema";
        }
    }

    public boolean validateBySchema(String input) throws ProcessingException {
        boolean result = false;
        StringBuilder schema = new StringBuilder();
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream stream;
        stream = loader.getResourceAsStream("bcard.json");
        Scanner sc = new Scanner(stream);
        while (sc.hasNextLine()) {
            schema.append(sc.nextLine());
        }

        try {
            JsonNode schemaNode = JsonLoader.fromString(schema.toString());
            JsonNode dataNode = JsonLoader.fromString(input);
            JsonSchema jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);
            ProcessingReport report = jsonSchema.validate(dataNode);
            result = report.isSuccess();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
