package MYOB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import com.asis.Excel;
import com.asis.QuaterData;
import com.asis.util.BaseClass;

public class GenrateExcel extends BaseClass {

	Double reportingVarValue;
	Double unknownVarValue;

	String formattedUnknownVar;
	String formattedreportingVar;

	Double unknownVar;
	Double reportingVar;

	public void getMYOBData() {
		ArrayList<QuaterData> xero_data = new ArrayList<>();
		QuaterData xeroObj = new QuaterData("As per the book");
		xeroObj.set_G1(Double.parseDouble(fetchCaptureA1G1B1Data.get(0)), false);
		xeroObj.set_1A(Double.parseDouble(fetchCaptureA1G1B1Data.get(1)), false);
		xeroObj.set_1B(Double.parseDouble(fetchCaptureA1G1B1Data.get(2)), false);
		xeroObj.set_GST_Refund(xeroObj.get_1A() - xeroObj.get_1B(), false);
		xeroObj.set_ATO_Total_Refund(xeroObj.get_GST_Refund() + xeroObj.get_4() + xeroObj.get_5A() - xeroObj.get_7D(), false);
		xero_data.add(xeroObj);
		XERO_DATA.add(xero_data);

		ArrayList<QuaterData> variance_data = new ArrayList<>();
		QuaterData variance = new QuaterData("Variance");
		variance.set_G1(QuaterData.getTotal_of_year_G1() - xeroObj.get_G1(), false);
		variance.set_1A(QuaterData.getTotal_of_year_1A() - xeroObj.get_1A(), false);
		variance.set_1B(QuaterData.getTotal_of_year_1B() - xeroObj.get_1B(), false);
		variance.set_W1(QuaterData.getTotal_of_year_W1() - xeroObj.get_W1(), false);
		variance.set_4(QuaterData.getTotal_of_year_4() - xeroObj.get_4(), false);
		variance.set_GST_Refund(variance.get_1A() - variance.get_1B(), false);
		variance.set_ATO_Total_Refund(variance.get_GST_Refund() + variance.get_4() + variance.get_5A() - variance.get_7D(), false);
		variance_data.add(variance);
		XERO_DATA.add(variance_data);

		HashMap<String, Double> hm7 = new HashMap<>();
		hm7.put("Reason for Variance:", 0.0);
		LAST_TABLE_DATA.add(hm7);

		HashMap<String, Double> hm8 = new HashMap<>();
		hm8.put("Reporting variance", variance.get_GST_Refund());
		LAST_TABLE_DATA.add(hm8);

		// Logging the size and contents of LAST_TABLE_DATA
		System.out.println("LAST_TABLE_DATA size: " + LAST_TABLE_DATA.size());
		for (int i = 0; i < LAST_TABLE_DATA.size(); i++) {
			System.out.println("Index " + i + ": " + LAST_TABLE_DATA.get(i));
		}

		// Check if LAST_TABLE_DATA has enough elements before accessing index 7
		if (LAST_TABLE_DATA.size() > 7) {
			reportingVarValue = LAST_TABLE_DATA.get(7).get("Reporting variance");
			formattedreportingVar = String.format("%.2f", reportingVarValue);
			reportingVar = Double.parseDouble(formattedreportingVar);
		} else {
			System.out.println("Error: LAST_TABLE_DATA does not have enough elements to access index 7.");
			return;
		}

		HashMap<String, Double> hm9 = new HashMap<>();
		if (LAST_TABLE_DATA.size() > 5 && LAST_TABLE_DATA.size() > 6 && LAST_TABLE_DATA.size() > 7) {
			hm9.put("Unknown variance", LAST_TABLE_DATA.get(5).get("Total - GST as per balance sheet") +
					LAST_TABLE_DATA.get(6).get("Reason for Variance:") +
					LAST_TABLE_DATA.get(7).get("Reporting variance"));
		} else {
			System.out.println("Error: LAST_TABLE_DATA does not have enough elements to calculate unknown variance.");
			return;
		}
		LAST_TABLE_DATA.add(hm9);

		unknownVarValue = LAST_TABLE_DATA.get(8).get("Unknown variance");
		formattedUnknownVar = String.format("%.2f", unknownVarValue);
		unknownVar = Double.parseDouble(formattedUnknownVar);

		ArrayList<QuaterData> bas_relodged_data = new ArrayList<>();
		QuaterData bas_relodged = new QuaterData("BAS to be relodged for Period ended Jun 23");

		bas_relodged.set_G1(TEMP_JUNE_G1 - variance.get_G1(), false);
		bas_relodged.set_1A(TEMP_JUNE_A1 - variance.get_1A(), false);
		bas_relodged.set_1B(TEMP_JUNE_B1 - variance.get_1B(), false);
		bas_relodged.set_W1(TEMP_JUNE_W1 - variance.get_W1(), false);
		bas_relodged.set_4(TEMP_JUNE_4 - variance.get_4(), false);
		bas_relodged.set_GST_Refund(TEMP_JUNE_GST_Refund - variance.get_GST_Refund(), false);
		bas_relodged.set_ATO_Total_Refund(bas_relodged.get_GST_Refund() + bas_relodged.get_4() + bas_relodged.get_5A() - bas_relodged.get_7D(), false);
		bas_relodged_data.add(bas_relodged);
		XERO_DATA.add(bas_relodged_data);
	}

	public void generateExcelAndSendEmail(String recipientEmail) {
		String[] client_data = { ATO_CLIENT_NAME, ATO_TO_DATE };
		String excelName = ATO_CLIENT_NAME + " " + ATO_TO_DATE;
		Excel obj = new Excel();
		String filePath = "Final_data.xls"; // Assuming this is the file path where the Excel file will be generated
		obj.createFinancialSummaryExcelWithData(filePath, BaseClass.ATO_ROW_DATA, BaseClass.XERO_DATA, BaseClass.ACTIVITY_STATEMENT_DATA, client_data);

		// Then, send the Excel file as an email attachment
		String from = "toptechautomation@theoutsourcepro.com.au";
		String password = "Guf87765";
		String senderMail = from;

		// Outlook SMTP server configuration
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// Creating a session object
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			// Creating a MimeMessage object
			Message message = new MimeMessage(session);
			// Set From: header field
			message.setFrom(new InternetAddress(from));
			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			// Set Subject: header field
			message.setSubject("BAS Summary of " + ATO_CLIENT_NAME);

			// Create a multipart message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();

			String emailBody = "<html><body style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-size: 14px; line-height: 1.5; color: #333;\">"
					+ "<h2 style=\"font-weight: bold; margin-bottom: 5px;\">Financial Summary Report</h2>"
					+ "<table border=\"1\" style=\"border-collapse: collapse; margin-bottom: 10px;\">"
					+ "<tr>"
					+ "<th style=\"background-color: #d3f3ce; padding: 8px;\">Client Name</th>"
					+ "<th style=\"background-color: #d3f3ce; padding: 8px;\">Year</th>"
					+ "<th style=\"background-color: #d3f3ce; padding: 8px;\">Reporting Variance</th>"
					+ "<th style=\"background-color: #d3f3ce; padding: 8px;\">Unknown Variance</th>"
					+ "</tr>"
					+ "<tr>"
					+ "<td style=\"padding: 8px;\">" + ATO_CLIENT_NAME + "</td>"
					+ "<td style=\"padding: 8px;\">" + ATO_TO_DATE + "</td>"
					+ "<td style=\"padding: 8px;\">" + formattedreportingVar + "</td>"
					+ "<td style=\"padding: 8px;\">" + formattedUnknownVar + "</td>"
					+ "</tr>"
					+ "</table>"
					+ "<p>Dear " + recipientEmail + ",</p>"
					+ "<p>Please find attached the BAS Summary for " + ATO_CLIENT_NAME + " for the year ended " + ATO_TO_DATE + ".</p>"
					+ "<p>Best regards,</p>"
					+ "<p>Your Name</p>"
					+ "<p>Software Engineer - I</p>"
					+ "<p>The Outsource Pro</p>"
					+ "</body></html>";

			messageBodyPart.setContent(emailBody, "text/html");

			// Add the message body to the multipart
			multipart.addBodyPart(messageBodyPart);

			// Attach the Excel file
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(excelName + ".xls");
			multipart.addBodyPart(messageBodyPart);

			// Set the complete message parts
			message.setContent(multipart);

			// Send the message
			Transport.send(message);

			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
