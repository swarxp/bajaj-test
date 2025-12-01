package com.bajaj.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			try {

				String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

				Map<String, String> requestBody = new HashMap<>();


				requestBody.put("name", "SWAROOP WARADE");
				requestBody.put("email", "waradeswaroop@gmail.com");
				requestBody.put("regNo", "22BLC1078");

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

				System.out.println("Step 1: Requesting Webhook...");
				ResponseEntity<Map> response = restTemplate.postForEntity(generateUrl, entity, Map.class);
				Map<String, Object> responseBody = response.getBody();

				if (responseBody != null) {

					String accessToken = (String) responseBody.get("accessToken");
					String submissionUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

					System.out.println("Received Token. Preparing Solution...");


					String finalSql = "SELECT d.DEPARTMENT_NAME, AVG(TIMESTAMPDIFF(YEAR, e.DOB, CURDATE())) AS AVERAGE_AGE, SUBSTRING_INDEX(GROUP_CONCAT(DISTINCT CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) SEPARATOR ', '), ', ', 10) AS EMPLOYEE_LIST FROM DEPARTMENT d JOIN EMPLOYEE e ON d.DEPARTMENT_ID = e.DEPARTMENT JOIN PAYMENTS p ON e.EMP_ID = p.EMP_ID WHERE p.AMOUNT > 70000 GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME ORDER BY d.DEPARTMENT_ID DESC;";


					Map<String, String> finalBody = new HashMap<>();
					finalBody.put("finalQuery", finalSql);

					HttpHeaders authHeaders = new HttpHeaders();
					authHeaders.setContentType(MediaType.APPLICATION_JSON);
					authHeaders.set("Authorization", accessToken);

					HttpEntity<Map<String, String>> finalEntity = new HttpEntity<>(finalBody, authHeaders);

					System.out.println("Step 2: Submitting Solution...");
					ResponseEntity<String> result = restTemplate.postForEntity(submissionUrl, finalEntity, String.class);

					System.out.println("--- SUCCESS ---");
					System.out.println("Server Response: " + result.getBody());
				} else {
					System.out.println("Error: Failed to get response");
				}

			} catch (Exception e) {
				System.out.println("--- ERROR OCCURRED ---");
				e.printStackTrace();
			}
			System.exit(0); // Exit when done
		};
	}
}