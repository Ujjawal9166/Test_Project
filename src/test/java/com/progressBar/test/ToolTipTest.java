package com.progressBar.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolTipTest {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Navigate to the demo page
            driver.get("https://demoqa.com/tool-tips");
            
            WebElement hoverButton = driver.findElement(By.id("toolTipButton"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hoverButton);

            // Initialize Actions class
            Actions actions = new Actions(driver);

           
            // Perform mouse hover action on the button
            actions.moveToElement(hoverButton).perform();

            // Wait for the tooltip to be visible and get its text
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            WebElement toolTip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));
            String toolTipText = toolTip.getText();

            // Validate the tooltip text
            if (toolTipText.equals("You hovered over the Button")) {
                System.out.println("Button tooltip text validation passed");
            } else {
                System.out.println("Button tooltip text validation failed");
            }

            // Locate the "Hover me to see text box" field
            WebElement hoverTextBox = driver.findElement(By.id("toolTipTextField"));

            // Perform mouse hover action on the text box
            actions.moveToElement(hoverTextBox).perform();

            // Wait for the tooltip to be visible and get its text
            WebElement textBoxToolTip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));
            String textBoxToolTipText = textBoxToolTip.getText();

            // Validate the tooltip text
            if (textBoxToolTipText.equals("You hovered over the text field")) {
                System.out.println("Text box tooltip text validation passed");
            } else {
                System.out.println("Text box tooltip text validation failed");
            }

        } finally {
            // Close the browser
            driver.quit();
        }
	}

}
