# Bajaj API Test Project Submission

A simple Spring Boot command-line application that completes the Bajaj hiring API challenge. The app requests a webhook token and then submits the final SQL solution to the provided endpoint.

## Owner
- **Name:** Swaroop Warade
- **Reg No:** 22BLC1078

## How it works
1. On startup, the application calls the `generateWebhook` endpoint to fetch an access token.
2. It prepares the final SQL query and sends it, along with the token, to the `testWebhook` endpoint.
3. Progress and the server response are printed to the console, and the app exits automatically when finished.

## Requirements
- Java 17+
- Maven 3.9+ (for building from source)

## Running the project
1. Install the requirements above.
2. Build the project:
   ```bash
   mvn clean package
   ```
3. Run the packaged jar (created in the `target/` directory):
   ```bash
   java -jar target/test-0.0.1-SNAPSHOT.jar
   ```

Alternatively, you can run directly from source without packaging:

```bash
mvn spring-boot:run
```

The console will log each step (requesting the webhook, submitting the query, and the final response). No additional configuration is required.
