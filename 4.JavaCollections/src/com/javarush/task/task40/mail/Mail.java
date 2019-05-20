package com.javarush.task.task40.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Crunchify.com
 *
 */

public class Mail {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail();
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
    }

    public static void generateAndSendEmail() throws AddressException, MessagingException {
        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("XXXXXX@gmail.com"));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("XXXXXX@gmail.com"));
        generateMailMessage.setSubject("Hello form JAVA");
        String emailBody = "Test email by JavaMail API example. " + "<br><br> Regards, <br>XXXXXX X.X.";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "XXXXX", "XXXXXX");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
    static void setProxy() {
        System.setProperty("http.proxyHost", "192.168.1.120");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "192.168.1.120");
        System.setProperty("https.proxyPort", "8080");
        /*
        System.setProperty("http.proxyUser", "*****");
        System.setProperty("http.proxyPassword", "*****");
        System.setProperty("https.proxyUser", "*****");
        System.setProperty("https.proxyPassword", "*****");
        */
    }
}