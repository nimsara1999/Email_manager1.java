package Emailclient;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class Serialize {
    private static Serialize serializeObj;
    private Serialize(){
    }

    //create object if it already not created
    public static Serialize getInstance(){
        if (serializeObj==null){
            serializeObj=new Serialize();
        }
        return serializeObj;
    }

    public void Serialization(CreateEmail mail) {
        //serializate given mail
        try {
            FileOutputStream fout = new FileOutputStream("details.ser", true);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(mail);
            out.flush();
            out.close();
            System.out.println("Object serialization is successfull");
            System.out.println();

        }catch (Exception e){
            System.out.println("details.ser file not found");
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream("details.ser");
                ObjectOutputStream out = new ObjectOutputStream(fout);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
