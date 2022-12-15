package Emailclient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Email_Client {
    public static void main(String[] args) {
        //get current date and send birthday wishes on that day
        Date todayDate = new Date();
        String todayDateString = new SimpleDateFormat("yyyy/MM/dd").format(todayDate);

        try {
            ObjectsCreater ResObj = new ObjectsCreater(); //create objects
            ObjectsCreater.vecObj = ResObj.objectsCreate("clientList.txt");

            // Send birthday wishes while checking today wishes already sent
            WishSendController checkForSend = new WishSendController(todayDateString);
            if (!checkForSend.wishControl()) {
                BirthdayHandler check1 = new BirthdayHandler(todayDateString);
                check1.todayWish();
                AddtoTxt.appendData("lastAccessDate.txt", todayDateString, false);
            } else {
                System.out.println("Today birthday wishes already sent");
            }
        }catch (Exception e){
            try {
                Files.deleteIfExists(Paths.get("lastAccessDate.txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //*****************************************************************************
        while (true) {
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println(new String(new char[70]).replace('\0', '='));
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + "6 - Exit application");
            System.out.println(new String(new char[70]).replace('\0', '='));
            System.out.print("Enter your choice: ");
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        // input format - Emailclient.Official: nimal,nimal@gmail.com,ceo
                        // Use a single input to get all the details of a recipient
                        System.out.println("input format - Official: name,email address,post");
                        System.out.println("               Official_friend: name,email address,post,birthday");
                        System.out.println("               Personal: name,nickname,email address ,birthday");
                        System.out.print("Enter details: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String new_client = scanner1.nextLine();
                        System.out.println();

                        // store details in clientList.txt file
                        AddtoTxt.appendData("clientList.txt", new_client, true);

                        //Live update object list by new input recipient
                        ObjectsCreater newRes = new ObjectsCreater();
                        newRes.instObjectCreate(new_client);
                        break;

                    case 2:
                        // input format - email, subject, content
                        System.out.println("input format - email, subject, content");
                        System.out.print("Enter details: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String dataStr = scanner2.nextLine();
                        System.out.println();

                        //separate input by comma and store at arrOfStr
                        String[] arrOfStr = dataStr.split(",");
                        String email = arrOfStr[0];
                        String subject = arrOfStr[1];
                        String content = arrOfStr[2];

                        //check input mail address valid or not.
                        // send mail with checking recipient already in our list.
                        if(!ValidChecker.isValidEmail(email)){
                            System.out.println("Invalid email. Please try again.");
                            break;
                        }
                        else{
                            DirectMailSender directsend=new DirectMailSender(email,subject,content,todayDateString);
                            directsend.send();
                        }
                        break;

                    case 3:
                        String givenDate1=getdate();
                        if(givenDate1=="x"){
                            break;
                        }
                        // code to print recipients who have birthdays on the given date
                        BirthdayHandler getnames = new BirthdayHandler(givenDate1); //create object
                        getnames. getNameOnTodayBD(); //print names
                        break;

                    case 4:
                        String givenDate2=getdate();
                        if(givenDate2=="x"){
                            break;
                        }
                        // code to print the details of all the emails sent on the input date
                        SentDataPrinter print = new SentDataPrinter();
                        print.printdata(givenDate2);
                        break;

                    case 5:
                        // code to print the number of recipient objects in the application
                        System.out.println(ObjectsCreater.count);
                        break;

                    case 6:
                        System.exit(0);
                        //exit program
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Go to the main menu. Please try again.");
            }
        }
    }
    //Method for input date
    public static String getdate(){
        System.out.println("input format - yyyy/MM/dd");
        System.out.print("Enter date: ");
        Scanner scanner3 = new Scanner(System.in);
        String givenDate1 = scanner3.nextLine();
        System.out.println();
        int n=0;
        while(!ValidChecker.isValidDate(givenDate1)){
            System.out.println("Date input format is not valid!");
            System.out.print("Reenter date: ");
            givenDate1 = scanner3.nextLine();
            n++;
            if(n==3){
                System.out.println("Go to the main menu");
                return "x";
            }
        }
        return givenDate1;
    }
}



