package Emailclient;

class OfficialFriend extends Recipient implements BirthDayWish {
    String date;
    String post;

    public OfficialFriend(String date, String mail, String name, String post) {
        super(mail,name);
        this.date=date;
        this.post=post;
    }

    @Override
    public void SendWish()  {
        String subject="BIRTH DAY WISH";
        String content="Wish you a Happy Birthday. P.D.N.T.Paramulla";
        System.out.println("Sending birthday wish to "+super.name);
        CreateEmail mywish = new CreateEmail(super.mail,super.name,post,subject,content);
        boolean err = mywish.sendmail();
        //serialize
        if(err) {
            Serialize newser = Serialize.getInstance();
            newser.Serialization(mywish);
        }
    }

    @Override
    public void SendNewMail(String subject,String content)  {
        System.out.println("Sending email to "+super.name);
        CreateEmail mymail = new CreateEmail(super.mail,super.name,post, subject,content);
        boolean err = mymail.sendmail();
        //serialize
        if(err) {
            Serialize newser = Serialize.getInstance();
            newser.Serialization(mymail);
        }
    }

}
