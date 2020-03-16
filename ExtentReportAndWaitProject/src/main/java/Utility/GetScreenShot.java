package Utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenShot {
	public static String capture(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationPath=System.getProperty("user.dir")+"/TestReport/screenshots/"+screenShotName+".png";
		File destination=new File(destinationPath);
		FileHandler.copy(source, destination);
		return destinationPath;
	}
}
