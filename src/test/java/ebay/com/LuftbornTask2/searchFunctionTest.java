package ebay.com.LuftbornTask2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.asserts.*;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class searchFunctionTest {
	ChromeDriver driver;
	ebaySearchPageObject homePage;
	JsonNode testData;

	@BeforeTest
	public void environmentSetup() {
		try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

		driver = new ChromeDriver();
		String getURL = testData.get("url").asText();
		driver.navigate().to(getURL);
		driver.manage().window().maximize();
		
		homePage = new ebaySearchPageObject(driver);
	}
	
	
  @Test
  public void mainFunction() {
	  homePage.validateCorrectLink();
	  homePage.enterSearchItem();
	  homePage.searchResults();
	  homePage.validateCorrectResults();
	  homePage.selectTransmission();
	  homePage.searchResults();
	  
	  
	  }
  
  
  @AfterTest
  public void closeDriver() {
	  //driver.quit();
  }
  }

