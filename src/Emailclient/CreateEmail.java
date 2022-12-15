package Emailclient;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class CreateEmail implements Serializable {
    protected String recipientmail, name, post, subject, content, date;
    protected String time;

    public CreateEmail(String recipientmail, String name, String post, String subject, String content) {
        this.recipientmail = recipientmail;
        this.name = name;
        this.post = post;
        this.subject = subject;
        this.content = content;
        this.date = date();
        this.time= String.valueOf(java.time.LocalTime.now()).substring(0,8);
    }

    public boolean sendmail(){
        return TLSMailSender.sendmail1(recipientmail, subject, content);
    }

    public String date(){
        Date todayDate = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(todayDate);
    }
}