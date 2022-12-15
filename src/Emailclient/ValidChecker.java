package Emailclient;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;


//Check email address is valid
class ValidChecker {
    public static boolean isValidEmail(String emailAdd){
        boolean valid;
        try{
            var address = new InternetAddress(emailAdd);
            address.validate();
            valid = true;

        } catch (AddressException e) {
            valid= false;
        }
        return valid;
    }


    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
