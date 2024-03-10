package com.studyroom.user.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.studyroom.user.model.dto.EmailTemplate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class NetworkManager {
	
	 @Autowired
	 private DiscoveryClient discoveryClient;

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
	
	public String sendGetRequest() {
		 HttpURLConnection connection = null;
        try {
//        	//String url = "http://localhost:8081/notification";
//        	
//        	String url = "http://NOTIFICATION-SERVICE/notification";    
        	
            // Get service instance by its registered name
            List<ServiceInstance> instances = discoveryClient.getInstances("NOTIFICATION-SERVICE");
            if (instances.isEmpty()) {
                throw new RuntimeException("No instances available for NOTIFICATION-SERVICE");
            }
            // Assuming you want to call the first instance
            ServiceInstance instance = instances.get(0);
            
            URI instanceUri = instances.get(0).getUri();

            // Construct URL using the instance information
            String url = instance.getUri().toString() + "/notification"; // Change "/your-endpoint" to the actual endpoint

        	// Create URL object
            URL obj = new URL(url);

            // Open HttpURLConnection
             connection = (HttpURLConnection) obj.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            // Print response code (optional)
            System.out.println("Response Code: " + responseCode);

            // Read response body
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print response body (optional)
            System.out.println("Response Body: " + response.toString());
            
            return response.toString();
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
        finally {
            // Close connection
            connection.disconnect();
        }
		return null;
    }

}
