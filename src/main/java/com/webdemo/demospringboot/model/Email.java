/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webdemo.demospringboot.model;

import java.util.Properties;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Admin
 */
public class Email {

    public Email() {
    }
//    public static void main(String[] args) {
//        Email e= new Email();
//        e.SentEmai("0cauchin0@gmail.com");
//    }
//    public static void main(String[] args) {
//        String Code="";
//        String toEmail="0cauchin0@gmail.com";
//        String fromEmail="cuuphan00@gmail.com";
//        String Password="nxlalmmqfvnggqrl";
//        try {
//            Properties pr= new Properties();
//            pr.put("mail.smtp.auth", "true");
//        pr.put("mail.smtp.host", "smtp.gmail.com");
//        pr.put("mail.smtp.starttls.enable", "true");
//        pr.put("mail.smtp.socketFactory.port", "587");
//        pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        pr.put("mail.smtp.port", "587");
//            Session session = Session.getDefaultInstance(pr, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, Password);
//                }
//            });
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(fromEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//            message.setSubject("your code");
//            Random rnd= new Random();
//        int number=rnd.nextInt(999999);
//        String code= String.format("%06d", number);
//            
//            message.setText("Your code is " +code);
//            Code=code;
//            Transport.send(message);
//        } catch (Exception e) {
//        }
//        System.out.println(Code);
//    }
    public String getRandom(){
        Random rnd= new Random();
        int number=rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    public String SentEmai(String email){
        String Code="";
        String toEmail=email;
        String fromEmail="cuuphan00@gmail.com";
        String Password="nxlalmmqfvnggqrl";
        try {
            Properties pr= Configemail(new Properties());
            Session session = Session.getDefaultInstance(pr, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, Password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("your code");
            String code=getRandom();
            message.setText("Your code is " +code);
            Code=code;
            Transport.send(message);
        } catch (Exception e) {
        }
        return Code;
    }
    public Properties Configemail(Properties pr){
        pr.put("mail.smtp.auth", "true");
        pr.put("mail.smtp.host", "smtp.gmail.com");
        pr.put("mail.smtp.starttls.enable", "true");
        pr.put("mail.smtp.socketFactory.port", "587");
        pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pr.put("mail.smtp.port", "587");
        return pr;
    }
    
    
}


