package common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	private String toMail;

	public Mailer(String _toMail) {
		toMail = _toMail;
	}

	public void sendMail(String text) {

		final String username = "lilacteam10@gmail.com";
		final String password = "khklecumv101";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
			message.setSubject("מערכת לילך אוטומטית");
			message.setText(text);

			Transport.send(message);

			System.out.println("Mail sent to " + this.toMail);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

}