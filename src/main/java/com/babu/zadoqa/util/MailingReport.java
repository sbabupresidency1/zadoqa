package com.babu.zadoqa.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
import javax.mail.internet.MimeUtility;

import com.babu.zadoqa.utils.Directory;

public class MailingReport {

    public static void SendMail() {

	final String username = Directory.userName;
	final String password = Directory.password;
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.host", Directory.smtpHost);
	Session session = Session.getInstance(props,
		new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		    }
		});
	try {
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(Directory.fromAddress));
	    message.setRecipients(Message.RecipientType.TO,
		    InternetAddress.parse(Directory.toAddress));
	    message.setRecipients(Message.RecipientType.CC,
			    InternetAddress.parse(Directory.ccAddress));
	    message.setSubject("Execution Result");
	    message.setText("PFA");

	    message.setText(MimeUtility.encodeText("Pode Has Fallen Down",
		    "utf-8", "B"));
	    message.setText(MimeUtility.encodeText("Fallen Down It Has",
		    "utf-8", "B"));
	    message.setText(MimeUtility.encodeText("It Has Fallen Down",
		    "utf-8", "B"));

	    MimeBodyPart messageBodyPart = new MimeBodyPart();
	    Multipart multipart = new MimeMultipart();
	    messageBodyPart = new MimeBodyPart();
	    String file = "C:/Softwares/Babu/Reports/index.html";
	    String fileName = "Sample File";
	    DataSource source = new FileDataSource(file);
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.attachFile(file);
	    // messageBodyPart.setFileName(fileName);
	    multipart.addBodyPart(messageBodyPart);
	    message.setContent(multipart);
	    System.out.println("Mail Sending");
	    Transport.send(message);
	    System.out.println("Done");
	} catch (MessagingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
