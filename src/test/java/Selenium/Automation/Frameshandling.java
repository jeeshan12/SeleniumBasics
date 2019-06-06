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

public class Frameshandling {

	static WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/tinymce");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println(driver.getCurrentUrl());
	}

	@Test
	public void testFrames() {
		// switching to the editor's frame
		driver.switchTo().frame("mce_0_ifr");
		WebElement defaultTextElement = driver.findElement(By.id("tinymce"));
		String defaultText = defaultTextElement.getText();
		// Clearing the text from editor
		defaultTextElement.clear();
		// Entering the text in editor
		defaultTextElement.sendKeys("I am in frame !!!!!");
		// Asserting the text
		Assert.assertNotEquals(defaultText, defaultTextElement.getText());
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
