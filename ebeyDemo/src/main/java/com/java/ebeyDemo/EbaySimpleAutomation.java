package com.java.ebeyDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EbaySimpleAutomation {
	public static void main(String[] args) {
		// Set the path for ChromeDriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\VARUN KUMAR\\OneDrive\\Documents\\OneDrive\\Desktop\\New folder\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();
		// Open eBay website
		driver.get("https://www.ebay.com");
		// maximize window
		driver.manage().window().maximize();
		// Search for 'book'
		System.out.println("test**************");
		WebElement searchBox = driver.findElement(By.xpath("//*[@id='gh-ac']")); 
		searchBox.sendKeys("book");
		System.out.println("test1");
		WebElement searchButton = driver.findElement(By.id("gh-search-btn"));
		searchButton.click();
		System.out.println("test2");
		// Click on the first book in the search result
		WebElement firstBook = driver.findElement(By.xpath("(//li[@class='s-item'])[1]//a"));
		firstBook.click();
		System.out.println("test3");
		// Click the 'Add to cart' button
		WebElement addToCartButton = driver.findElement(By.id("atcRedesignId_btn"));
		addToCartButton.click();
		System.out.println("test4");
		// Get the cart count and verify it
		WebElement cartIcon = driver.findElement(By.id("gh-cart-n"));
		String cartCount = cartIcon.getText();
		System.out.println("Items in the cart: " + cartCount);
		// Check if the cart has at least one item
		if (!cartCount.equals("0")) {
			System.out.println("Test Passed: Item added to the cart.");
		} else {
			System.out.println("Test Failed: Cart is empty.");
		}

		// Close the browser
		driver.quit();
	}
}
