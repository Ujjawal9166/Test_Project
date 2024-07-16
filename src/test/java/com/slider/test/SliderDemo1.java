package com.slider.test;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SliderDemo1{

	public static void main(String[] args) {
		
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

                 
            driver.get("https://demoqa.com/slider");

           
            driver.manage().window().maximize();

           
            WebElement slider = driver.findElement(By.className("range-slider"));
           
            //target value is 57
            // so initial value plus 36
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
       
            // Close the browser
            driver.quit();
        
    }

}
