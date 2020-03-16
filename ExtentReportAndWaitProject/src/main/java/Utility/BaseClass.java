package Utility;

import DriverFactory.DriverManager;
import DriverFactory.DriverManagerFactory;
import DriverFactory.DriverManagerFactory.DriverType;

import org.testng.annotations.BeforeSuite;

import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	DriverManager driverManager;
	public static WebDriver driver;
	public WebDriver getDriver() {
        return driver;
    }
  @BeforeSuite
  public void beforeSuite() {
	  //enum changes with parameter
	  driverManager=DriverManagerFactory.getDriverManager(DriverType.FIREFOX);
	  driver=driverManager.getWebDriver();
	  driver.manage().window().maximize();
  }

  @AfterSuite
  public void afterSuite() {
	  driverManager.quitWebDriver();
  }
  public void waitForPageLoad() {
	    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
	    wait.until(new Function<WebDriver, Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            System.out.println("Current Window State       : "
	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
	            return String
	                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
	                .equals("complete");
	        }
	    });
	}
}
