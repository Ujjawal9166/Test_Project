package com.slider.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class SliderDemo {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");

		// Initialize WebDriver
		WebDriver driver = new ChromeDriver();
		try {

			driver.get("https://demoqa.com/slider");

			// Maximize the browser window
			driver.manage().window().maximize();

			// Locate the slider
			WebElement slider = driver.findElement(By.className("range-slider"));

			// Calculate the offset to move the slider to 57
			int targetValue = 57;
			int initialValue = Integer.parseInt(slider.getAttribute("value"));
			
			int sliderWidth = slider.getSize().getWidth();

			int xOffset = (targetValue - initialValue) * (sliderWidth / 100);

			Actions actions = new Actions(driver);
			actions.clickAndHold(slider);
			actions.moveToElement(slider, 36, 0).contextClick().build().perform();

			
			// Validate the text field value
			WebElement sliderValue = driver.findElement(By.id("sliderValue"));
			String value = sliderValue.getAttribute("value");

			if (value.equals("57")) {
				System.out.println("Test Passed: Slider value is 57");
			} else {
				System.out.println("Test Failed: Slider value is " + value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			driver.quit();

		}
	}

}
