package Tests;

import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Utility.BaseClass;
import Utility.FrameWorkFunction;

public class MainPageTests extends BaseClass {
	public static FrameWorkFunction frameFunction;
	
	public static List<String> testCaseNameList;
	public static int sizeofList;
 @BeforeClass
 public void initialize() throws Throwable {
	 frameFunction=new FrameWorkFunction(driver);

 }
	@Test(priority=1)
	public static void verifyMainPage() throws Throwable {
		frameFunction.autoStepFunction();
	}
	
}
