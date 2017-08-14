package com.skrill.interns.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.json.JSONException;
import org.json.XML;
import org.xml.sax.SAXException;

public class XMLToJSONConverter {

    ClassLoader loader = this.getClass().getClassLoader();

    public String convertXMLtoJSON(String xml) throws JSONException {

        OutputStreamWriter out = null;
        Source schemaFile = new StreamSource(loader.getResourceAsStream("XMLScheme.xsd"));
        File sourceFile = new File("File");
        try {
            sourceFile.createNewFile();
            out = new FileWriter(sourceFile, true);
            out.append(xml);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Source sourceInput = new StreamSource(sourceFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema;
        Validator validator;
        String error = "Invalid XML";
        try {
            schema = schemaFactory.newSchema(schemaFile);
            validator = schema.newValidator();
            try {
                validator.validate(sourceInput);
                sourceFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("test1");
                return error;
            }
        } catch (SAXException e) {
            e.printStackTrace();
            System.out.println("test2");
            return error;
        }
        String output = XML.toJSONObject(xml).toString(2);
        if ("{}".equals(output))
            throw new JSONException("Incorrect input");
        return output;
    }
}
