package com.progressBar.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProgressBar {

	public static void main(String[] args) {
		 WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        
	        try {
	            // Navigate to the demoqa progress bar page
	            driver.get("https://demoqa.com/progress-bar");
	            
	            // Find the Start button and click it
	            WebElement startButton = driver.findElement(By.id("startStopButton"));
	            startButton.click();
	            
	            // Find the progress bar
	            WebElement progressBar = driver.findElement(By.className("progress-bar"));
	            
	            // Wait until the progress bar reaches 57%
	            
	            while (true) {
	                String progressText = progressBar.getText();
	                if (progressText.equals("57%")) {
	                    break;
	                }
	            }
	            
	            // Click on the Stop button
	            startButton.click();
	            
	            // Validate that the progress bar reached 57%
	            String finalProgressText = progressBar.getText();
	            if (finalProgressText.equals("57%")) {
	                System.out.println("Test Passed: Progress bar reached 57%");
	            } else {
	                System.out.println("Test Failed: Progress bar did not reach 57%");
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	}

}
