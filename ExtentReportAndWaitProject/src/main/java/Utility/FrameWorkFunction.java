package Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import Reports.ExtentManager;
import Reports.ExtentTestManager;

public class FrameWorkFunction {
	public static WebDriver driver;
	public FrameWorkFunction(WebDriver driver) {
		FrameWorkFunction.driver=driver;
	}
	public static List<String> getRuntimeData(int i, HashMap<String, String[]> testDataStepsHash,List<String> testCaseNameList) {
		List<String> testCaseNameStepList=new ArrayList<String>();
			 for(String testcaseStep:testDataStepsHash.get(testCaseNameList.get(i)))
			 {
					 testCaseNameStepList.add(testcaseStep);
				
			 } 
			 return testCaseNameStepList;
	}
	public static boolean perform(String operation,String objectType,String Object,String value,String waitfor) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,Integer.parseInt(waitfor));
		WebElement ele = null;
		boolean foundElement=false;
		switch (operation.toUpperCase()) {
		case "CLICK":
			try {
			ele =wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(objectType,Object)));
			driver.findElement(getObject(objectType,Object)).click();
			foundElement=true;
			}
			catch(Exception e){
				foundElement=false;
			}
			break;
		case "SETTEXT":
			try {
				ele =wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(objectType,Object)));
			driver.findElement(getObject(objectType,Object)).sendKeys(value);
			foundElement=true;
			}
			catch(Exception e){
				foundElement=false;
			}
			break;
		case "GOTOURL":
			try {
			driver.get(value);
			waitForPageLoad();
			foundElement=true;
			}
			catch(Exception e){
				foundElement=false;
			}
			break;
		case "GETTEXT":
			try {
				ele =wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(objectType,Object)));
			String valueofElement=driver.findElement(getObject(objectType,Object)).getText();
			System.out.println("**************************"+valueofElement+"*********************************");
			foundElement=true;
			}
			catch(Exception e){
				foundElement=false;
			}
			break;
		case "ASSERTVALUE":
			try {
				ele =wait.until(ExpectedConditions.visibilityOfElementLocated(getObject(objectType,Object)));
			String actual=driver.findElement(getObject(objectType,Object)).getText();
			Assert.assertEquals(actual, value);
			foundElement=true;
				}
				catch(Exception e){
					foundElement=false;
				}
			break;
		default:
			foundElement=false;
			break;
			
		}
		return foundElement;
	}
	private static By getObject(String objectType,String Object) throws Throwable {
		if(objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(Object);
		}
		else if(objectType.equalsIgnoreCase("CLASSNAME")) {
			return By.className(Object);
		}
		else if(objectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(Object);
		}
		else if(objectType.equalsIgnoreCase("NAME")) {
			return By.name(Object);
		}
		else if(objectType.equalsIgnoreCase("ID")) {
			return By.id(Object);
		}
		else { throw new Exception("Wrong object type");}
	}
	public void autoStepFunction() throws Throwable {
		List<String> testCaseNameList;
		int sizeofList;
		List<String> testCaseStepListGeneric=new ArrayList<String>();
		List<String> testCaseStepToPerformListGeneric=new ArrayList<String>();
		List<String> testCaseObjectListGeneric=new ArrayList<String>();
		List<String> testCaseObjectTypeListGeneric=new ArrayList<String>();
		List<String> testCaseWaitListGeneric=new ArrayList<String>();
		List<String> testCaseExecutionListGeneric=new ArrayList<String>();
		List<String> testCaseDVListGeneric=new ArrayList<String>();
		testCaseNameList=new ArrayList<String>();
		TreeMap<String, String[]> sorted = new TreeMap<>(); 
		  sorted.putAll(Constants.TestDataStepsHash); 
		  /*Constants.TestDataStepsHash.clear();
	        // Display the TreeMap which is naturally sorted 
	        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
	        	Constants.TestDataStepsHash.put(entry.getKey(),entry.getValue());}*/
		 for(Entry<String,String[]>testCaseName:sorted.entrySet()) {
			 testCaseNameList.add(testCaseName.getKey());
		 }
		 sizeofList=testCaseNameList.size();
		 int sizeofstep=0;
		 for(int i=0;i<sizeofList;i++) {
			 testCaseStepListGeneric.addAll(getRuntimeData(i,Constants.TestDataStepsHash,testCaseNameList));
			 testCaseStepToPerformListGeneric.addAll(getRuntimeData(i,Constants.TestDataStepToPerformHash,testCaseNameList));
			 testCaseObjectListGeneric.addAll(getRuntimeData(i,Constants.TestDataObjectHash,testCaseNameList));
			 testCaseObjectTypeListGeneric.addAll(getRuntimeData(i,Constants.TestDataObjectTypeHash,testCaseNameList));
			 testCaseWaitListGeneric.addAll(getRuntimeData(i,Constants.TestDataWaitHash,testCaseNameList));
			 testCaseExecutionListGeneric.addAll(getRuntimeData(i,Constants.TestDataExecutionHash,testCaseNameList));
			 testCaseDVListGeneric.addAll(getRuntimeData(i,Constants.TestDataDVHash,testCaseNameList));
			 sizeofstep=testCaseStepListGeneric.size();
			 //Reporting Start
			 ExtentTestManager.startTest(testCaseNameList.get(i));
			 for(int j=0;j<sizeofstep;j++) {
				 System.out.println(testCaseStepListGeneric.get(j)+"||"+testCaseStepToPerformListGeneric.get(j)+"||"+testCaseObjectListGeneric.get(j)+"||"+testCaseObjectTypeListGeneric.get(j)+"||"+testCaseWaitListGeneric.get(j)+"||"+testCaseExecutionListGeneric.get(j)+"||"+testCaseDVListGeneric.get(j));
				 boolean flag=perform(testCaseStepToPerformListGeneric.get(j).toString(), testCaseObjectTypeListGeneric.get(j).toString(), testCaseObjectListGeneric.get(j).toString(), testCaseDVListGeneric.get(j).toString(),testCaseWaitListGeneric.get(j));
				 if(flag==true) {
				 ExtentTestManager.getTest().log(Status.PASS, testCaseStepToPerformListGeneric.get(j)+"  "+testCaseDVListGeneric.get(j));
				 }
				 else {
					 ExtentTestManager.getTest().log(Status.FAIL, testCaseStepToPerformListGeneric.get(j)+"  "+testCaseDVListGeneric.get(j));
				 }
			 }
			 ExtentTestManager.endTest();
			 ExtentManager.getInstance().flush();
			 testCaseStepListGeneric.clear();
			 testCaseStepToPerformListGeneric.clear();
			 testCaseObjectListGeneric.clear();
			 testCaseObjectTypeListGeneric.clear();
			 testCaseWaitListGeneric.clear();
			 testCaseExecutionListGeneric.clear();
			 testCaseDVListGeneric.clear();
			 sizeofstep=0;
		 }
	}
 public static void waitForPageLoad() {
		    Wait<WebDriver> wait = new WebDriverWait(driver, 60);
		    wait.until(new Function<WebDriver, Boolean>() {
		        public Boolean apply(WebDriver driver) {
		            //System.out.println("Current Window State       : "
		              //  + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
		            return String
		                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
		                .equals("complete");
		        }
		    });
		}
}
