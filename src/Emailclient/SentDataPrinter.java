package Emailclient;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Objects;

class SentDataPrinter {
    public void printdata(String givenDate2)  {
        try {
            //"details.ser" file deserialize and print all data
            CreateEmail newobject;
            FileInputStream file = new FileInputStream("details.ser");
            boolean flag=false;
            while (true) {
                try {
                    ObjectInputStream in = new ObjectInputStream(file);
                    newobject = (CreateEmail) in.readObject();
                    if (newobject != null) {
                        if (Objects.equals(newobject.date, givenDate2)) {
                            System.out.println(new String(new char[70]).replace('\0', '*'));
                            if (newobject.name==null){
                                System.out.println("Time: "+newobject.time);
                                System.out.println("Mail: " + newobject.recipientmail);
                                System.out.println("Subject: " + newobject.subject);
                                flag=true;
                            }
                            else{
                                System.out.println("Time: "+newobject.time);
                                System.out.println("Mail: " + newobject.recipientmail + ",  Name: " + newobject.name);
                                System.out.println("Subject: " + newobject.subject);
                                flag=true;
                            }

                        } else if (Objects.equals(newobject.subject, "BIRTH DAY WISH") && Objects.equals(newobject.date.substring(5, 10), givenDate2.substring(5, 10))) {
                            System.out.println("Mail: " + newobject.recipientmail + ",  Name: " + newobject.name);
                            System.out.println("Subject: " + newobject.subject);
                            flag=true;
                        }
                    }
                } catch (Exception e) {
                    if(flag){
                        System.out.println(new String(new char[70]).replace('\0', '*'));
                        System.out.println("\nFinished.");
                    }
                    break;
                }
            }
            file.close();
        }catch (Exception e){
            System.out.println("Sent box is empty.");//if "details.ser" file is empty
        }
    }
}
