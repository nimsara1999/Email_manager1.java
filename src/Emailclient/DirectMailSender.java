package Emailclient;
import java.util.Objects;

class DirectMailSender {
    String email,subject,content,todayDateString;

    public DirectMailSender(String email, String subject, String content,String todayDateString){
        this.email=email;
        this.subject=subject;
        this.content=content;
        this.todayDateString=todayDateString;
    }

    public void send(){
        Recipient newmail= checkExit();
        if (newmail != null) {
            System.out.println("Recipient found in Recipient list.");
            newmail.SendNewMail(subject,content);
        } else {
            // email to given address
            CreateEmail newmail12 = new CreateEmail(email, null, null, subject, content);
            boolean err = newmail12.sendmail();

            //serialization(If not any error while email send)
            if (err) {
                Serialize newser = Serialize.getInstance();
                newser.Serialization(newmail12);
            }
        }
    }

    public Recipient checkExit() {
            //If given new email is already in our recipient list
        for (Recipient recipients : ObjectsCreater.vecObj) {
            if (Objects.equals(recipients.mail, email)) {
                if (recipients instanceof OfficialFriend officialfriend1) { //if officialfriend mail
                    return new Official(todayDateString, email, officialfriend1.name, officialfriend1.post);
                }
                if (recipients instanceof Personal personal1) { //if personal mail
                    return new Personal(todayDateString, email, personal1.name);
                }
                if (recipients instanceof Official official1) { //if official mail
                    return new Official(todayDateString, email, official1.name, official1.post);
                }
            }
        }
        return null;
    }
}

