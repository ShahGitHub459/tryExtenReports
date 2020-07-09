package Utility;

import DriverFactory.DriverManager;
import DriverFactory.DriverManagerFactory;
import ExcelUtility.ExcelDriver;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	DriverManager driverManager;
	public static WebDriver driver;
	public static ExcelDriver driverExcel;
	public WebDriver getDriver() {
        return driver;
    }
  @BeforeSuite
  public void beforeSuite() throws IOException {
	  //enum changes with parameter
	  driverExcel=new ExcelDriver();
	  String path=System.getProperty("user.dir")+"\\src\\main\\resources\\";
	  Sheet mainSheet=driverExcel.getExcelDriver(path, "RequirementSheet.xlsx", "Requirement");
	  driverExcel.getData(mainSheet);
	  Sheet scenarioSheet=driverExcel.getExcelDriver(path, Constants.TestDataWorkbook[0], Constants.ScenarioExecutionSheet[0]);
	  driverExcel.getDataScenario(scenarioSheet);
	  Sheet testSheet=driverExcel.getExcelDriver(path, Constants.TestDataWorkbook[0], Constants.TestDataSheet[0]);
	  driverExcel.getDataTestCases(testSheet);
	  driverManager=DriverManagerFactory.getDriverManager(Constants.Driver[0]);
	  driver=driverManager.getWebDriver();
	  driver.manage().window().maximize();	
  }

  @AfterSuite
  public void afterSuite() {
	  driverManager.quitWebDriver();
  }
}
