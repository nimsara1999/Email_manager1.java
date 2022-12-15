package Emailclient;

//****************************** handle all birthday operations **************************
class BirthdayHandler {
    private final String givenDate;

    public BirthdayHandler(String givenDate) {
        this.givenDate = givenDate;
    }

    //print names of all birthdays who have birthday today by using vector if objects
    public void getNameOnTodayBD() {
        for (Recipient recipients : ObjectsCreater.vecObj) {
            //check object is instance of subclass and object type casting
            if (recipients instanceof OfficialFriend officialFriend1) {
                if (officialFriend1.date.substring(5, 10).equals(givenDate.substring(5, 10))) {
                    System.out.println(officialFriend1.name);
                }
            }
            if (recipients instanceof Personal personal1) {
                if (personal1.date.substring(5, 10).equals(givenDate.substring(5, 10))) {
                    System.out.println(personal1.name);
                }
            }
        }
    }

    //If anyone has birthday today, then create object of Emailclient.BDWishFactory and give data to send wish
    public void todayWish() {
        BDWishFactory wishFac = new BDWishFactory();
        for (Recipient recipients : ObjectsCreater.vecObj) {
            if (recipients instanceof OfficialFriend officialFriend1) {
                if (officialFriend1.date.substring(5, 10).equalsIgnoreCase(givenDate.substring(5, 10))) {
                    BirthDayWish wish1 = wishFac.getType("official_friend", officialFriend1.mail, officialFriend1.name, officialFriend1.post, officialFriend1.date);//we should give all data
                    wish1.SendWish();
                }
            }
            if (recipients instanceof Personal personal1) {
                if (personal1.date.substring(5, 10).equalsIgnoreCase(givenDate.substring(5, 10))) {
                    BirthDayWish wish1 = wishFac.getType("personal", personal1.mail, personal1.name, personal1.post, personal1.date);//we should give all data
                    wish1.SendWish();
                }
            }
        }
    }
}


