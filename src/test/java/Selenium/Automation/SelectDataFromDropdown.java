package Selenium.Automation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for SelectDataFromDropdown.
 */
public class SelectDataFromDropdown

{
	static WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.toolsqa.com/automation-practice-form/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println(driver.getCurrentUrl());
	}

	@Test
	public void test() {

		Select select = new Select(driver.findElement(By.id("continents")));
		List<WebElement> list = select.getOptions();
		WebElement element = list.stream().filter(x -> x.getText().equals("Europe")).findAny().orElse(null);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		if (!(element == null)) {
			select.selectByVisibleText(element.getText());
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Select multilist = new Select(driver.findElement(By.id("selenium_commands")));
		List<WebElement> listOptions = multilist.getOptions();
		System.out.println("Given list is multi list: " + multilist.isMultiple());

		List<String> lMultiple = Arrays.asList("Browser Commands", "Switch Commands", "WebElement Commands");

		Actions actions = new Actions(driver);
		for (WebElement web : listOptions) {
			for (String str : lMultiple) {
				if (web.getText().equals(str)) {
					actions.keyDown(Keys.CONTROL).click(web).keyUp(Keys.CONTROL).build().perform();
					;
				}
			}
		}

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
	}
}
