package com.skrill.interns.converter;

import static org.testng.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.fge.jsonschema.exceptions.ProcessingException;

public class TestValidationByJSONSchema {

    JSONToXMLConverter converter;

    @BeforeClass
    public void setup() {
        converter = new JSONToXMLConverter();
    }

    @Test
    public void givenValidJsonInputJsonValidationInvokedThenReturnTrue() throws ProcessingException, FileNotFoundException {
        // GIVEN
        Scanner sc = new Scanner(new File("src\\main\\resources\\test_file\\jsonInput.txt"));
        StringBuilder jsonInput = new StringBuilder();
        while (sc.hasNextLine()) {
            jsonInput.append(sc.nextLine());
        }
        // WHEN
        boolean result = converter.validateBySchema(jsonInput.toString());
        // THEN
        assertEquals(result, true);
    }

}
