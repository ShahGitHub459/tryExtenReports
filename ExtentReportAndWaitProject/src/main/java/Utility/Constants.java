package Utility;

import java.util.HashMap;


public class Constants {
	//public static WebDriver driver;
	public static String[] TestDataWorkbook=null;
	public static String[] ScenarioExecutionSheet=null;
	public static String[] TestDataSheet=null;
	public static String[] Driver=null;
	public static String[] Iteration=null;
	public static String[] AutoTestSteps=null;
	public static String[] StepsFormatSheetName=null;
	public static int countMainSheet;
	
	public static String[] ScenarioTestCaseName=null;
	public static String[] IterationScenarioTestCase=null;
	public static String[] ScenarioExecution=null;
	public static String[] ScenarioNumber=null;
	public static String[] TestCaseExecution=null;
	public static int countScenarioSheet;
	
	public static String[] TestDataTestCaseName;
	public static String[] TestDataSteps;
	public static String[] TestDataStepToPerform;
	public static String[] TestDataObjectType;
	public static String[] TestDataObject;
	public static String[] TestDataWait;
	public static String[] TestDataExecution;
	public static String[] TestDataDV;
	public static int[] TestStepsCounter;
	
	public static HashMap<String,String[]> TestDataStepsHash=new HashMap<String,String[]>();
	public static HashMap<String,String[]> TestDataStepToPerformHash=new HashMap<String,String[]>();
	public static HashMap<String,String[]> TestDataObjectTypeHash=new HashMap<String,String[]>();
	public static HashMap<String,String[]> TestDataObjectHash=new HashMap<String,String[]>();
	public static HashMap<String,String[]> TestDataWaitHash=new HashMap<String,String[]>();
	public static HashMap<String,String[]> TestDataExecutionHash=new HashMap<String,String[]>();
	public static HashMap<String,String[]> TestDataDVHash=new HashMap<String,String[]>();

}
