package Selenium.Automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingMultipleFrames {

	static WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/nested_frames");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println(driver.getCurrentUrl());
	}

	@Test
	public void testFrames() {
		//switching to the top frame
		driver.switchTo().frame("frame-top");
		//switching to the middle frame
		driver.switchTo().frame("frame-middle");
		//getting the text from middle box 
		WebElement element = driver.findElement(By.id("content"));
		String text = element.getText();
		//Asserting the text 
		Assert.assertEquals("MIDDLE", text);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
