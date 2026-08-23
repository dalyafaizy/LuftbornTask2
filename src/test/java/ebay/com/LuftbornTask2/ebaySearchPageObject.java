package ebay.com.LuftbornTask2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ebaySearchPageObject {
	WebDriver driver;
	JsonNode testData;
	
	
	public ebaySearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	
		public void enterSearchItem() {
			WebElement search_field = driver.findElement(By.id("gh-ac"));
			search_field.clear();
			try {
	            ObjectMapper mapper = new ObjectMapper();
	            testData = mapper.readTree(new File("src/test/resources/testData.json"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			String getItem = testData.get("searchTerm").asText();
			search_field.sendKeys(getItem, Keys.ENTER);
	}
		
		public void selectTransmission() {
			WebElement manualCheckbox = driver.findElement(By.xpath("//input[@value='Manual']/ancestor::span[contains(@class,'checkbox')]"));
			if (manualCheckbox.isDisplayed()) {
				manualCheckbox.click();
			}
			else {
				WebElement transmissionArrow = driver.findElement(By.xpath("//span[text()='Transmission']"));
				transmissionArrow.click();
				manualCheckbox.click();
			}
					
		}
		
		public void searchResults() {
			WebElement resultCount = driver.findElement(By.cssSelector(("#srp-results-heading span.BOLD")));
			String count = resultCount.getText();
			System.out.println("The search result number is: " + count);
		}
		
		
		public void validateCorrectLink() {
			
			WebElement mainPageElement = driver.findElement(By.xpath("//span[text()='Electronics']"));
			if (mainPageElement.isDisplayed()==true) {
				System.out.println("Landed on the main page");
			}
			
			else {
				System.out.println("Landed on the wrong page");
			}
		}
		
		public void validateCorrectResults() {
			WebElement resultValidate = driver.findElement(By.xpath("//span[contains(@class, 'su-styled-text') and contains(text(), 'Mazda')]"));
			String result = resultValidate.getText().toLowerCase();
			if(result.contains("mazda mx")== true) {
				System.out.println("Correct results");
			}
			
			else {
				System.out.println("Wrong results");
			}
		}
	}

