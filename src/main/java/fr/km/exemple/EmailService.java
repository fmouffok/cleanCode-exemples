package fr.km.exemple;

import lombok.ToString;

import java.util.Random;
import java.util.function.Consumer;

/**
 * use Consumer <Email> fillEmail to extract the variable part outside to the client part
 */
public class EmailService {

    // solution 3 with consumer

    public void sendEmail(String emailAddress, Consumer<Email> fillEmail) {
        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
        int MAX_RETRIES = 3;
        for (int i = 0; i < MAX_RETRIES; i++ ) {
            Email email = new Email(); // constructor generates new unique ID
            email.setSender("noreply@corp.com");
            email.setReplyTo("/dev/null");
            fillEmail.accept(email);
            email.setTo(emailAddress);
            boolean success = context.send(email);
            if (success) break;
        }
    }
    // we can transform this method to consumer because it take parameter and it return nothing
    //public  abstract void fillEmail (Email email);

// solution 2 with polymorphism
//    public void sendEmail(String emailAddress) {
//        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
//        int MAX_RETRIES = 3;
//        for (int i = 0; i < MAX_RETRIES; i++ ) {
//            Email email = new Email(); // constructor generates new unique ID
//            email.setSender("noreply@corp.com");
//            email.setReplyTo("/dev/null");
//            fillEmail(email);
//            email.setTo(emailAddress);
//            boolean success = context.send(email);
//            if (success) break;
//        }
//    }
//    public  abstract void fillEmail (Email email);


// initial case
//    public void sendReceivedEmail(String emailAddress ) {
//        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
//        int MAX_RETRIES = 3;
//        for (int i = 0; i < MAX_RETRIES; i++ ) {
//            Email email = new Email(); // constructor generates new unique ID
//            email.setSender("noreply@corp.com");
//            email.setReplyTo("/dev/null");
//            email.setSubject("order received");
//            email.setBody("Thank you for your order");
//            email.setTo(emailAddress);
//            fillEmail.accept(email);
//            boolean success = context.send(email);
//            if (success) break;
//        }
//    }
//
//    public void sendShippedEmail(String emailAddress) {
//        EmailContext context = new EmailContext(/*smtpConfig,etc*/);
//        int MAX_RETRIES = 3;
//        for (int i = 0; i < MAX_RETRIES; i++ ) {
//            Email email = new Email(); // constructor generates new unique ID
//            email.setSender("noreply@corp.com");
//            email.setReplyTo("/dev/null");
//            email.setSubject("order shipped");
//            email.setBody("Your order has been shipped");
//            email.setTo(emailAddress);
//            boolean success = context.send(email);
//            if (success) break;
//        }
//    }

}
//solution 2
//class SendEmailReceived extends  EmailService{
//    public void fillEmail(Email email) {
//        email.setSubject("order received");
//        email.setBody("Thank you for your order");
//    }
//}
//
//class SendEmailShipped extends  EmailService{
//    public void fillEmail(Email email) {
//        email.setSubject("order shipped");
//        email.setBody("Your order has been shipped");
//    }
//}

class ClientCode{

    public static void fillReceivedEmail(Email email) {
        email.setSubject("order received");
        email.setBody("Thank you for your order");
    }

    public static void fillShippedEmail(Email email) {
        email.setSubject("order shipped");
        email.setBody("Your order has been shipped");
    }

    public static void main(String[] args) {
        //solution 2 polymorphism
//        new SendEmailReceived().fillEmail(new Email());
//        new SendEmailShipped().fillEmail(new Email());

        // solution 3 with consumer
        new EmailService().sendEmail("aa@aa.fr", ClientCode::fillReceivedEmail);
        new EmailService().sendEmail("aa@aa.fr", ClientCode::fillShippedEmail);
    }
}




class EmailContext {
    public boolean send(Email email) {
        System.out.println("Trying to send " + email);
        return new Random(System.nanoTime()).nextBoolean();
    }
}

@ToString
class Email {

    private long id = new Random(System.nanoTime()).nextLong();
    private String sender;
    private String subject;
    private String body;
    private String replyTo;
    private String to;

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}