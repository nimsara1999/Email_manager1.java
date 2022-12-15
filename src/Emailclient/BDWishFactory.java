package Emailclient;

class BDWishFactory {
    public BirthDayWish getType(String recipientType,String email,String name, String post,String birthdate) {
        if (recipientType == null) {
            return null;
        }
        if (recipientType.equalsIgnoreCase("personal")) {
            return new Personal(birthdate,email,name);
        }
        if (recipientType.equalsIgnoreCase("official_friend")) {
            return new OfficialFriend(birthdate,email,name,post);
        }
        return null;
    }
}
