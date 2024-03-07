package com.studyroom.user.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.studyroom.user.model.dto.EmailTemplate;

public class NetworkManager {

	public static void sendResponse(String string) {
		if (string.equals("User Added Successfully")) {
			try {
				// URL of the REST controller endpoint
				String url = "http://localhost:8081/sendEmail";

				// URL of the REST controller endpoint when using RequestParams
				// String url ="http://localhost:8081/sendEmail?fromEmail=email1@gmail.com&toEmail=email2@gmail.com&subject=testing&body=testing";

				// Create URL object
				URL obj = new URL(url);

				// Open HttpURLConnection
				HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

				// Set request method
				connection.setRequestMethod("POST");

				// Set request headers
				connection.setRequestProperty("Content-Type", "application/json");

				// Enable output
				connection.setDoOutput(true);

				/* When we use RequestObject instead of RequestParams */
				// Email Template Object Creation
				EmailTemplate emailTemplate = new EmailTemplate();
				emailTemplate.setFromEmail("email1@gmail.com");
				emailTemplate.setToEmail("email2@gmail.com");
				emailTemplate.setSubject("Testing Template Subject");
				emailTemplate.setBody("Testing Template Body");

				// Convert your object to JSON
				Gson gson = new Gson();
				String jsonBody = gson.toJson(emailTemplate);

				// Write the JSON data to the request
				try (OutputStream os = connection.getOutputStream()) {
					byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
					os.write(input, 0, input.length);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Get response code
				int responseCode = connection.getResponseCode();

				// Print response code (optional)
				System.out.println("Response Code: " + responseCode);

				// Close connection
				connection.disconnect();
			} catch (Exception e) {
				// Handle exception
				e.printStackTrace();
			}
		}
	}
}
