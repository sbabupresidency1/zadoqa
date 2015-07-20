package com.babu.zadoqa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

	public static String getCurrentTime;
    public static void SendMail() 
    {

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
	    String file = "C:/Softwares/Babu/OutputFile/"+getCurrentTime+".zip";
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
    
    ////FTPS///GOBI.E////ZippFile///////
    public static void MailZipped() throws Exception
	{
		File directoryToZip = new File("C:/Softwares/Babu/Reports");			
		List<File> fileList = new ArrayList<File>();
		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		getAllFiles(directoryToZip, fileList);
		System.out.println("---Creating zip file");
		writeZipFile(directoryToZip, fileList);
		System.out.println("---Done");
		
	}	
    ////FTPS///GOBI.E////ZippFile///////
	public static void getAllFiles(File dir, List<File> fileList) throws InterruptedException 
	{
		try 
		{
			//Thread.sleep(12000);
			File[] files = dir.listFiles();
			for (File file : files) 
			{
				fileList.add(file);
				if (file.isDirectory()) 
				{
					System.out.println("directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				}
				else 
				{
					System.out.println("file:" + file.getCanonicalPath());
				}
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	 ////FTPS///GOBI.E////ZippFile///////
	public static void writeZipFile(File directoryToZip, List<File> fileList) throws Exception
	{
		try
		{		//E yyyy.MM.dd 'at' hh:mm:ss a
			getCurrentTime = new SimpleDateFormat("E_yyyy_MM_dd_HH_mm_ss_a").format(Calendar.getInstance().getTime());
			FileOutputStream fos = new FileOutputStream("C:/Softwares/Babu/OutputFile/"+getCurrentTime+".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (File file : fileList)
			{
				if (!file.isDirectory()) 
				{
					//Thread.sleep(4000);
					addToZip(directoryToZip, file, zos);
				}
			}
			zos.close();
			fos.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	 ////FTPS///GOBI.E////ZippFile///////
	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
	IOException
	{
		FileInputStream fis = new FileInputStream(file);
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0)
		{
			zos.write(bytes, 0, length);
		}
		zos.closeEntry();
		fis.close();
	}

    
    
    
}
