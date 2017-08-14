package com.skrill.interns.WebShoppingCart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {

    FileManager f = new FileManager();
    File file;

    @Test
    public void givenFileNameAndItemWhenFileExists() throws FileNotFoundException {
        // GIVEN
        String str = "asdfg";
        String filename = "test";
        file = new File("/ShoppingCartFiles/" + filename);
        // WHEN
        f.appendToFile(filename, str);
        // THEN
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void givenFileNameAndItemWhenFileNotExistAndCreatesNewFile() throws FileNotFoundException {
        // GIVEN
        String str = "banan";
        String filename = "banan";
        file = new File("/ShoppingCartFiles/" + filename);
        // WHEN
        f.appendToFile(filename, str);
        // THEN
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void givenFileNameAndItemStartingWithMinusThrowingFileNotFoundException() throws FileNotFoundException {
        // GIVEN
        String str = "-banan";
        String filename = "empty";
        file = new File("/ShoppingCartFiles/" + filename);
        // WHEN
        f.appendToFile(filename, str);
        // THEN
    }

    @Test
    public void givenFileNameAndReturningFileContentToString() throws FileNotFoundException {
        // GIVEN
        String str = null;
        String item = "banan, sliva";
        String filename = "fruit";
        file = new File("/ShoppingCartFiles/" + filename);
        try {
            file.createNewFile();
            f.appendToFile(filename, item);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // WHEN
        str = f.readFile(filename);
        System.out.println(str);
        // THEN
        Assert.assertTrue("banan, sliva, ".equals(str));
        file.delete();
    }
}
