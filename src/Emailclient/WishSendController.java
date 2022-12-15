package Emailclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

//control duplicate birthday wishes in same day
class WishSendController {
    private final String today;

    public WishSendController(String today) {
        this.today=today;
    }

    public boolean wishControl() {
        File myObj = new File("lastAccessDate.txt");
        Scanner myReader = null;

        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            try {
                FileWriter myWriter = null;
                myWriter = new FileWriter("lastAccessDate.txt");
                myWriter.write("");
                myWriter.close();
                return false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String lastAccDate = myReader.nextLine();
        myReader.close();
        return Objects.equals(today, lastAccDate);
    }
}
