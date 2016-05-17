package com.gemma.web.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.support.PagedListHolder;

import com.gemma.web.beans.BeansHelper;
import com.gemma.web.beans.EmailProperties;

public class ProcessEmail {
		
	public void sendMail(final Email email) {
		EmailProperties emailProperties = (EmailProperties) new BeansHelper().getBean("email-context.xml", "emailProperties");
		Properties props = new Properties();

		props.setProperty("mail.transport.protocol", emailProperties.getTransportProtocol());
		props.put("mail.smtp.auth", emailProperties.getSmtpAuth());
		props.setProperty("mail.host", emailProperties.getSmtpHost());
		props.put("mail.smtp.port", emailProperties.getSmtpPort());
		props.setProperty("mail.smtp.starttls.enable", emailProperties.getSmtpStarttlsEnable());

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFrom(),
						email.getPassword());
			}
		};

		Session session = Session.getDefaultInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setText(email.getMessage(), "utf-8", "html");
			msg.setSubject(email.getSubject());
			msg.setText(email.getMessage());
			msg.setFrom(new InternetAddress(email.getFrom(), email
					.getName()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					email.getTo()));

			Transport.send(msg);
		} catch (MessagingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Finished.");
	}

	public PagedListHolder<MsgDisplay> receiveEmail(Email email) {
			List<MsgDisplay> msgList = new ArrayList<MsgDisplay>();

			EmailProperties emailProperties = (EmailProperties) new BeansHelper().getBean("email-context.xml", "emailProperties");
		try {
			Properties properties = new Properties();
			String host = "pop3.live.com";

			properties.put("mail.pop3.host",emailProperties.getPop3Host());
			properties.put("mail.pop3.port", emailProperties.getPop3Port());
			properties.put("mail.pop3.starttls.enable", emailProperties.getPop3StarttlsEnable());
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, email.getFrom(), email.getPassword());

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();

			for (int i = 0, n = messages.length; i < n; i++) {

				MsgDisplay msg = new MsgDisplay();
				msg.setFrom(messages[i].getFrom()[0].toString());
				msg.setSubject(messages[i].getSubject());
				msg.setText(messages[i].getContent().toString());
				msg.setRead(messages[i].isSet(Flag.SEEN));
				if(msg.isRead() == false) {
					msgList.add(msg);
				}
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PagedListHolder<MsgDisplay>(msgList);
	}

}
