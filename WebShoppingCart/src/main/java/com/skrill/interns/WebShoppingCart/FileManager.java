package com.skrill.interns.WebShoppingCart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class FileManager implements IFileManager {

    File file;
    FileReader in;
    OutputStreamWriter out;
    String fileContent = null;
    private static final String PATH = "/ShoppingCartFiles/"; // (C:// ... ) needs to be changed for OS, different from Windows


    @Override
    public void appendToFile(String filename, String item) throws FileNotFoundException {

        File dir = new File(PATH);

        if (!dir.exists()) {
            dir.mkdir();
        }

        file = new File(PATH + filename);
        if (file.exists()) {
            try {
                out = new FileWriter(file, true);
                out.append(item + ", ");
                out.flush();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
//            finally {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
        } else if (item.startsWith("-") || "".equals(item)) {

            throw new FileNotFoundException("File " + filename + "does not exist");

        } else {

            try {
                file.createNewFile();
                out = new FileWriter(file, true);
                out.append(item + ", ");
                out.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            try {
            out.close();
            System.out.println("CLOSING THE OUTPUT STREAM");
            } catch (IOException e) {
                e.printStackTrace();
        } catch (NullPointerException exc) {
        }
    }
//            finally {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }

    @Override
    public String readFile(String filename) throws FileNotFoundException {

        file = new File(PATH + filename);
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        String part;
        try {
            in = new FileReader(file);
            br = new BufferedReader(in);
            while ((part = br.readLine()) != null) {
                sb.append(part);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            } catch (NullPointerException exc) {
        } 
//        finally {
//            try {
//                in.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        fileContent = sb.toString();
        return fileContent;

    }

    public boolean checkForExistingSession(String cookie) {
        file = new File(PATH + cookie);
        return file.exists();
    }

}
