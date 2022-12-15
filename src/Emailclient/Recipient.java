package Emailclient;

abstract class Recipient  {
    protected String mail,name;

    public Recipient(String mail, String name) {
        this.mail = mail;
        this.name = name;
    }
    public abstract void SendNewMail(String subject,String content) ;
}
