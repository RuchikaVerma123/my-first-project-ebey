package com.java.api.automation;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiAutomationDemo2 {

    @Test
    public void verifyBPIInfo() {
        try {
            // URL of the API
            String urlString = "https://api.coindesk.com/v1/bpi/currentprice.json";
            
            // Send GET request
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(responseCode, 200, "API request failed. Response code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract the BPI object
            JSONObject bpi = jsonResponse.getJSONObject("bpi");

            // Verify that there are 3 BPIs: USD, GBP, EUR
            Assert.assertTrue(bpi.has("USD"), "USD BPI not found.");
            Assert.assertTrue(bpi.has("GBP"), "GBP BPI not found.");
            Assert.assertTrue(bpi.has("EUR"), "EUR BPI not found.");

            // Verify the GBP description
            JSONObject gbp = bpi.getJSONObject("GBP");
            String gbpDescription = gbp.getString("description");
            Assert.assertEquals(gbpDescription, "British Pound Sterling", "GBP description mismatch.");

            System.out.println("Test passed: API returned correct BPI information.");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An error occurred during the API test: " + e.getMessage());
        }
    }
}
