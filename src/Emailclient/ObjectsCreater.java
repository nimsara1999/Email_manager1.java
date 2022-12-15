package Emailclient;//error occure if clientlist.txt is not present

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

//Store all recipient objects in array
class ObjectsCreater {
    static Vector<Recipient> vecObj;
    static int count=0;

    protected Vector objectsCreate(String Clientfile ) {
        String birthday, email, name, post;
        vecObj = new Vector<>();
        File myObj = new File(Clientfile);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            try {
                FileWriter myWriter;
                myWriter = new FileWriter(Clientfile);
                myWriter.write("");
                myWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] arrOfStr = data.split(": ", 2);
            String[] data_arr = arrOfStr[1].split(",", 5);
            count++;

            if (data.substring(0, 8).equalsIgnoreCase("personal")) {
                birthday = data_arr[3];
                email = data_arr[2];
                name = data_arr[0];
                Recipient obj = new Personal(birthday,email, name);
                vecObj.addElement(obj);
            }
            if (data.substring(0, 15).equalsIgnoreCase("official_friend")) {
                birthday = data_arr[3];
                email = data_arr[1];
                name = data_arr[0];
                post = data_arr[2];
                Recipient obj = new OfficialFriend(birthday, email, name, post);
                vecObj.addElement(obj);
            }
            if (data.substring(0, 8).equalsIgnoreCase("official")) {
                birthday = null;
                email = data_arr[1];
                name = data_arr[0];
                post = data_arr[2];
                Recipient obj = new Official(birthday, email, name, post);
                vecObj.addElement(obj);
            }
        }
        myReader.close();
        return vecObj;
    }

    // if new recipient add in case 2, then create new object and add to the object list
    public void instObjectCreate(String data){
        String birthday;
        String email;
        String name;
        String post;
        String[] arrOfStr= data.split(": ",2);
        String[] data_arr = arrOfStr[1].split(",",5);
        count++;

        if(vecObj==null){
            vecObj = new Vector<>();
        }
        if (data.substring(0,8).equalsIgnoreCase("personal")){
            birthday = data_arr[3];
            email = data_arr[2];
            name = data_arr[0];
            vecObj.addElement(new Personal(birthday, email, name));
        }
        else if(data.substring(0,15).equalsIgnoreCase("official_friend")){
            birthday = data_arr[3];
            email = data_arr[1];
            name = data_arr[0];
            post= data_arr[2];
            vecObj.addElement(new OfficialFriend(birthday, email, name,post));
        }
        else if(data.substring(0,8).equalsIgnoreCase("official")){
            birthday=null;
            email = data_arr[1];
            name = data_arr[0];
            post= data_arr[2];
            vecObj.addElement(new Official(birthday, email, name,post));
        }
    }
}
