Bajaj Finserv Health - Qualifier 1 (Java)

This repository contains the solution for the Bajaj Finserv Health Hiring Challenge (Qualifier 1).

The application is a Spring Boot automation tool that interacts with the Bajaj Health API to retrieve a problem statement, solves a specific SQL challenge, and submits the result using a secure JWT token.

👤 Candidate Details

Name: Swaroop Warade

Reg No: 22BLC107

🚀 Project Overview

The application follows a strict automated workflow triggered immediately upon startup:

Authentication: Sends a POST request to the generateWebhook endpoint with candidate details.

Token Handling: Retrieves a unique accessToken (JWT) and a webhook URL from the response.

Logic Execution: * Targeted Question 2 (based on even Roll Number).

Constructs a complex SQL query to calculate the average age of employees with salaries > ₹70,000 per department.

Submission: Submits the final SQL query string to the testWebhook endpoint with the authorization header.

🛠️ Technology Stack

Language: Java 17+

Framework: Spring Boot 3.x

Build Tool: Maven

Http Client: RestTemplate

📝 SQL Solution (Question 2)

The core logic solves the following problem:

For every department, calculate the average age of individuals with salaries exceeding ₹70,000, and produce a concatenated string containing at most 10 of their names.

The Generated Query:

SELECT 
    d.DEPARTMENT_NAME, 
    AVG(TIMESTAMPDIFF(YEAR, e.DOB, CURDATE())) AS AVERAGE_AGE, 
    SUBSTRING_INDEX(GROUP_CONCAT(DISTINCT CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) SEPARATOR ', '), ', ', 10) AS EMPLOYEE_LIST 
FROM DEPARTMENT d 
JOIN EMPLOYEE e ON d.DEPARTMENT_ID = e.DEPARTMENT 
JOIN PAYMENTS p ON e.EMP_ID = p.EMP_ID 
WHERE p.AMOUNT > 70000 
GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME 
ORDER BY d.DEPARTMENT_ID DESC;


📸 Output

Upon running the application, the console logs the successful transaction with the server:

Step 1: Requesting Webhook...
Received Token. Preparing Solution...
Step 2: Submitting Solution...
--- SUCCESS ---
Server Response: {"success":true, "message":"Webhook processed successfully"}


📦 How to Run

Clone the repository:

git clone [https://github.com/](https://github.com/)[your-username]/[repo-name].git


Build the Project:

./mvnw clean package


Run the JAR:

java -jar target/test-0.0.1-SNAPSHOT.jar


📂 Repository Structure

src/main/java: Contains the Spring Boot application logic (TestApplication.java).

pom.xml: Maven dependencies.

test-0.0.1-SNAPSHOT.jar: The compiled executable file.
