package com.skrill.interns.WebShoppingCart;

import java.io.FileNotFoundException;

public interface IFileManager {

    public void appendToFile(String filename, String item) throws FileNotFoundException;

    public String readFile(String filename) throws FileNotFoundException;

}
