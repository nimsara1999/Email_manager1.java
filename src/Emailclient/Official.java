package Emailclient;

class Official extends Recipient{
    String date;
    String post;

    public Official(String date, String mail, String name, String post) {
        super(mail,name);
        this.date=date;
        this.post=post;
    }

    @Override
    public void SendNewMail(String subject, String content)  {
        System.out.println("Sending email to "+super.name);
        CreateEmail mymail = new CreateEmail(super.mail,super.name,post,subject,content);
        mymail.sendmail();
        //serialize
        Serialize newser = Serialize.getInstance();
        newser.Serialization(mymail);
    }
}
