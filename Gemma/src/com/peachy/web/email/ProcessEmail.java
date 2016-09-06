package com.peachy.web.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.support.PagedListHolder;

import com.peachy.web.beans.BeansHelper;
import com.peachy.web.beans.FileLocations;
import com.peachy.web.orm.UserProfile;

public class ProcessEmail {
	final private String configFile = "email.properties";
	final private String dsEmail = "DailySpecials.html";
	final private String from = "customer_service@donzalmart.com";
	final private String password = "In_heaven3!";
	final private String name = "Customer Service";
		
	public void sendMail(final Email email) throws Exception {
		Properties properties = new Properties();
		FileLocations loc = (FileLocations) new BeansHelper().getBean("config-context.xml", "fileLocations");
		URL url = new URL(loc.getEmailConfig() + configFile);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
        try {
            properties.load(in);
        } catch (SecurityException | IOException | IllegalArgumentException e) {
            throw e;
        } finally {
            try { in.close(); }
            catch (IOException ie) { throw ie; }
        }

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFrom(),
						email.getPassword());
			}
		};

		Session session = Session.getDefaultInstance(properties, auth);

		MimeMessage msg = new MimeMessage(session);
		
			
			msg.setSubject(email.getSubject());
			msg.setContent(email.getMessage(), "text/html");

			msg.setFrom(new InternetAddress(email.getFrom(), email
					.getName()));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					email.getTo()));

			Transport.send(msg);

	}

	public void sendLoginLink(UserProfile user, String baseUrl) throws Exception {
		Email email = new Email();
		
		email.setName(user.getFirstname() + " " + user.getLastname());
		email.setFrom(from);
		email.setPassword(password);
		email.setSubject("Welcome to Peachy's Coffee");
		String msg = "<h1>Welcome " + email.getName() + "</h1>" +
		             "<h3>Please click the link to activate your account</h3>" +
				     "<a href='" + baseUrl + user.getUserID() + "&h=" + user.getPassword() + "'>" +
				     "Activate your accout</a>";

		email.setTo(user.getUsername());
		email.setMessage(msg);
		
		sendMail(email);
		
	}
	
	public String getDailySpecials() throws IOException {
		String fileContent = "";
		
		FileLocations loc = (FileLocations) new BeansHelper().getBean("config-context.xml", "fileLocations");
		URL url = new URL(loc.getEmailConfig() + dsEmail);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String line = "";
		while ((line = in.readLine()) != null) {
			fileContent += line;
		}
		
		return fileContent;
	}
		
	public PagedListHolder<MsgDisplay> receiveEmail(Email email) throws MessagingException, IOException, URISyntaxException {
			List<MsgDisplay> msgList = new ArrayList<MsgDisplay>();
			FileLocations loc = (FileLocations) new BeansHelper().getBean("config-context.xml", "fileLocations");
			URL url = new URL(loc.getEmailConfig() + configFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			Properties properties = new Properties();
			
	        try {
	            properties.load(in);
	        } catch (SecurityException | IOException | IllegalArgumentException e) {
	            throw e;
	        } finally {
	            try { in.close(); }
	            catch (IOException ie) { throw ie; }
	        }

			Session emailSession = Session.getDefaultInstance(properties);

			Store store = emailSession.getStore();
			String mailHost = properties.getProperty("mail.imap.host");
			store.connect(mailHost, email.getFrom(), email.getPassword());

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
			
		return new PagedListHolder<MsgDisplay>(msgList);
	}
	
	public void dailySpecials(List<UserProfile> users, String message) throws Exception {
		
		Email email = new Email();
		
		email.setName(name);
		email.setFrom(from);
		email.setPassword(password);
		email.setSubject("Daily Specials");
		for (UserProfile user : users) {
			email.setTo(user.getUsername());
			email.setMessage(message);
			sendMail(email);
		}
	}

}
