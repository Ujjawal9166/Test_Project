package com.progressBar.test;

	import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class DatePickerAutomation {
	    public static void main(String[] args) {
	        // Set up the WebDriver
	        System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        WebDriver driver = new ChromeDriver(options);
	        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            // Navigate to the date picker page
	            driver.get("https://demoqa.com/date-picker");

	            // Get today's date and the next date
	            LocalDate today = LocalDate.now();
	            LocalDate nextDate = today.plusDays(1);
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	            String nextDateString = nextDate.format(dateFormatter);
	            DateTimeFormatter monthDayFormatter = DateTimeFormatter.ofPattern("MMMM d");
	            String nextMonthDayString = nextDate.format(monthDayFormatter);

	            // Select date for the first date picker
	            WebElement selectDateField = wait.until(ExpectedConditions.elementToBeClickable(By.id("datePickerMonthYearInput")));
	            selectDateField.click();
	            selectDate(nextDate, driver, wait);
	            
	            // Verify the date was selected correctly
	            String selectedDate = selectDateField.getAttribute("value");
	            if (nextDateString.equals(selectedDate)) {
	                System.out.println("Select Date: Date selected correctly: " + selectedDate);
	            } else {
	                System.out.println("Select Date: Date selection failed. Selected: " + selectedDate);
	            }

	            // Select date for the second date picker
	            WebElement dateTimeField = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateAndTimePickerInput")));
	            // Scroll to the element to ensure it's in view
	            js.executeScript("arguments[0].scrollIntoView(true);", dateTimeField);
	            dateTimeField.click();
	            selectDate(nextDate, driver, wait);
	            
	            // Verify the date was selected correctly
	            selectedDate = dateTimeField.getAttribute("value").split(",")[0]; // Only compare the date part
	            if (nextMonthDayString.equals(selectedDate)) {
	                System.out.println("Date and Time Picker: Date selected correctly: " + selectedDate);
	            } else {
	                System.out.println("Date and Time Picker: Date selection failed. Selected: " + selectedDate);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }

	    private static void selectDate(LocalDate date, WebDriver driver, WebDriverWait wait) {
	        // Navigate to the correct month and year
	        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
	        String targetMonthYear = date.format(monthYearFormatter);
	        
	        while (true) {
	            WebElement currentMonthYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker__current-month")));
	            if (currentMonthYear.getText().equals(targetMonthYear)) {
	                break;
	            }
	            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("react-datepicker__navigation--next")));
	            nextButton.click();
	        }

	        // Select the day
	        int day = date.getDayOfMonth();
	        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + day + "']")));
	        dayElement.click();
	    }
	}



