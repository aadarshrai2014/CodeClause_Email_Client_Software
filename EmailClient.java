import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

 class EmailClient {

    public static void main(String[] args) {
        // Sender's email address and password
        String senderEmail = "your-email@gmail.com";
        String senderPassword = "your-password";

        // Recipient's email address
        String recipientEmail = "recipient-email@example.com";

        // Email subject and content
        String emailSubject = "Test Email";
        String emailContent = "This is a test email sent from Java.";

        // Set up the JavaMail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a Session object with the authentication information
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a new MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the subject and content of the email
            message.setSubject(emailSubject);
            message.setText(emailContent);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error message: " + e.getMessage());
        }
    }
}
