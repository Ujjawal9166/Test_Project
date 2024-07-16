package com.slider.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SliderDemo2 {

	public static void main(String[] args) throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
	        WebDriver driver = new ChromeDriver();

	        try {
	            // Run the test
	            driver.get("https://demoqa.com/slider");
	            driver.manage().window().maximize();
	            moveSliderToValue(driver, 57);
	            validateSliderValue(driver, "57");

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Quit the driver
	            driver.quit();
	        }
	    }

	    public static void moveSliderToValue(WebDriver driver, int targetValue) {
	        WebElement slider = driver.findElement(By.className("range-slider"));
	        int initialValue = Integer.parseInt(slider.getAttribute("value"));
	        int sliderWidth = slider.getSize().getWidth();
	        int xOffset = (targetValue - initialValue) * (sliderWidth / 100);

	        Actions actions = new Actions(driver);
	        actions.clickAndHold(slider);
			actions.moveToElement(slider, 36, 0).contextClick().build().perform();

	    }

	    public static void validateSliderValue(WebDriver driver, String expectedValue) {
	        WebElement sliderValue = driver.findElement(By.id("sliderValue"));
	        String value = sliderValue.getAttribute("value");

	        if (value.equals(expectedValue)) {
	            System.out.println("Test Passed: Slider value is " + expectedValue);
	        } else {
	            System.out.println("Test Failed: Slider value is " + value);
	        }
	    }
		
		
		

	}


