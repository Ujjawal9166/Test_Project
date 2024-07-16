package com.progressBar.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoCompleteText {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        
        try {
            // Navigate to the specified URL
            driver.get("https://demoqa.com/auto-complete");

            // Locate the multiple colors input box
            WebElement multipleColorInput = driver.findElement(By.id("autoCompleteMultipleInput"));

            // Input 3 different colors
            String[] colors = {"Red", "Green", "Blue"};
            for (String color : colors) {
                multipleColorInput.sendKeys(color);
                multipleColorInput.sendKeys("\n");
            }

            // Validate the colors
            List<WebElement> colorElements = driver.findElements(By.className("auto-complete__multi-value__label"));
            for (int i = 0; i < colors.length; i++) {
                if (!colorElements.get(i).getText().equals(colors[i])) {
                    System.out.println("Validation failed for color: " + colors[i]);
                } else {
                    System.out.println("Validated color: " + colors[i]);
                }
            }

            // Locate the single color input box
            WebElement singleColorInput = driver.findElement(By.id("autoCompleteSingleInput"));

            // Input a single color
            String singleColor = "Yellow";
            singleColorInput.sendKeys(singleColor);
            singleColorInput.sendKeys("\n");

            // Validate the single color
            WebElement singleColorElement = driver.findElement(By.className("auto-complete__single-value"));
            if (!singleColorElement.getText().equals(singleColor)) {
                System.out.println("Validation failed for single color: " + singleColor);
            } else {
                System.out.println("Validated single color: " + singleColor);
            }

        } finally {
            // Close the browser
            driver.quit();
        }
	}

}
