package Tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utility.BaseClass;

public class MainPageTests extends BaseClass {
 @BeforeClass
 public void initialize() {
	 driver.get("https://nextbridge.com/");
	 waitForPageLoad();
 }
	@Test(priority=1)
	public void verifyMainPage() {
		driver.findElement(By.xpath("//*[@id=\"slide-8-layer-9\"]"));
	}
	@Test(priority=2)
	public void verifyFailTestCase() {
		driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div[1]/div/div[1]/div/div/div/div/div[3]"));
	}
	@Test(priority=3)
	public void verifySuccessCase() {
		driver.findElement(By.xpath("//*[@id=\"slide-8-layer-9\"]")).click();
	}
	
}
