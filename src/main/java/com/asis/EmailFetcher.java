package com.asis;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Properties;

public class EmailFetcher {

	  private static final String USER = "asiskaur5678@gmail.com";// Use environment variables for security
	    private static final String PASSWORD = "Asiskr78@"; // Use environment variables for security
	    private static final String HOST = "imap.gmail.com"; // Determine the host based on email


    public static void main(String[] args) {
        fetchOtp();
    }
/*
    private static String getHostForEmail(String email) {
        if (email.endsWith("@gmail.com")) {
            return "imap.gmail.com";
        } else if (email.endsWith("@outlook.com") || email.endsWith("@hotmail.com")) {
            return "outlook.office365.com";
        } else if (email.endsWith("@yahoo.com")) {
            return "imap.mail.yahoo.com";
        } else if (email.endsWith("@icloud.com")) {
            return "imap.mail.me.com";
        } else if (email.endsWith("@zoho.com")) {
            return "imap.zoho.com";
        } else {
            throw new IllegalArgumentException("Unsupported email provider");
        }
    }
*/
    private static void fetchOtp() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", HOST);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.starttls.enable", "true");

        try {
            System.out.println("Connecting to email server with user: " + USER);
            
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(HOST, USER, PASSWORD);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // Search for unread messages
            FlagTerm unseenFlagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message[] messages = emailFolder.search(unseenFlagTerm);

            if (messages.length > 0) {
                // Process the most recent email
                Message message = messages[messages.length - 1];
                String content = getTextFromMessage(message);

                System.out.println("Email Subject: " + message.getSubject());
                System.out.println("Email Content: " + content);

                // Extract OTP from the email content
                String otp = extractOtp(content);
                System.out.println("Extracted OTP: " + otp);
            } else {
                System.out.println("No new messages found.");
            }

            emailFolder.close(false);
            store.close();

        } catch (AuthenticationFailedException e) {
            System.err.println("Authentication failed. Check your email and password.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromMessage(Message message) throws Exception {
        StringBuilder text = new StringBuilder();
        Object content = message.getContent();

        if (content instanceof String) {
            text.append((String) content);
        } else if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.getContentType().contains("text/plain")) {
                    text.append((String) bodyPart.getContent());
                }
            }
        }

        return text.toString();
    }

    private static String extractOtp(String content) {
        // Implement your OTP extraction logic here
        // Example: Assume OTP is a 6-digit number
        String otp = content.replaceAll("\\D", ""); // Remove non-digit characters
        return otp.length() >= 6 ? otp.substring(0, 6) : null;
    }
}
