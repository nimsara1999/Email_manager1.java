package Emailclient;

class Personal extends Recipient implements BirthDayWish {
    String date;
    String post;

    public Personal(String date, String mail, String name) {
        super(mail, name);
        this.date=date;
    }

    public void SendWish()  {
        String subject="BIRTH DAY WISH";
        String content="Wish you a Happy Birthday, and hugs and love on your birthday. P.D.N.T.Paramulla";
        System.out.println("Sending birthday wish to " + super.name);
        CreateEmail mywish = new CreateEmail(super.mail, super.name, post, subject, content);
        boolean err = mywish.sendmail();
        //serialize
        if (err) {
            Serialize newser = Serialize.getInstance();
            newser.Serialization(mywish);
        }
    }

    @Override
    public void SendNewMail(String subject,String content)  {
        System.out.println("Sending email to " + super.name);
        CreateEmail mymail = new CreateEmail(super.mail, super.name, post, subject, content);
        boolean err = mymail.sendmail();
        //serialize
        if (err) {
            Serialize newser = Serialize.getInstance();
            newser.Serialization(mymail);
        }
    }
}
