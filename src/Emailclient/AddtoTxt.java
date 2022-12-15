package Emailclient;

import java.io.*;

class AddtoTxt {
    public static void appendData(String fileName, String data,boolean append) {
        try {
            // Open given file in append mode by creating an
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, append));
            out.write(data+"\n");
            out.close();
        }

        catch (IOException e) {
            System.out.println("Error ocured while data writing");
        }
    }
}




