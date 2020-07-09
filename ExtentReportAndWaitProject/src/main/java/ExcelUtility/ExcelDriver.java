package ExcelUtility;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Utility.Constants;

public class ExcelDriver {
	public Sheet getExcelDriver(String filePath,String fileName,String sheetName) throws IOException {
		try {
		File file=new File(filePath+"\\"+fileName);
		FileInputStream inputStream=new FileInputStream(file);
		Workbook mainWorkbook=null;
		String fileExtensionName=fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")) {
			mainWorkbook=new XSSFWorkbook(inputStream);	
		}
		else if(fileExtensionName.equals(".xls")) {
			mainWorkbook=new HSSFWorkbook(inputStream);
		}
		
		Sheet mainSheet=mainWorkbook.getSheet(sheetName);
		return mainSheet;
		}
		catch (IOException ex) {
			return  null;
		}
	}
	public void getData(Sheet mainSheet) {
		if(mainSheet!=null) {
			  int rowCount=mainSheet.getLastRowNum()-mainSheet.getFirstRowNum();
			  Constants.TestDataWorkbook=new String[rowCount];
			  Constants.ScenarioExecutionSheet=new String[rowCount];
			  Constants.StepsFormatSheetName=new String[rowCount];
			  Constants.AutoTestSteps=new String[rowCount];
			  Constants.TestDataSheet=new String[rowCount];
			  Constants.Driver=new String[rowCount];
			  Constants.Iteration=new String[rowCount];
			  Constants.countMainSheet=rowCount;
			  for(int i=1;i<rowCount+1;i++) {
				  Row row=mainSheet.getRow(i);
					  try {
						Constants.TestDataWorkbook[i-1]=row.getCell(columnName("TestDataWorkbook",mainSheet)).getStringCellValue();
						Constants.ScenarioExecutionSheet[i-1]=row.getCell(columnName("TestScenarioSheet",mainSheet)).getStringCellValue();
						Constants.TestDataSheet[i-1]=row.getCell(columnName("TestDataSheet",mainSheet)).getStringCellValue();
						Constants.Driver[i-1]=row.getCell(columnName("Driver",mainSheet)).getStringCellValue();
						Constants.Iteration[i-1]=row.getCell(columnName("Iteration",mainSheet)).getStringCellValue();
						Constants.AutoTestSteps[i-1]=row.getCell(columnName("AutoTestSteps",mainSheet)).getStringCellValue();
						Constants.StepsFormatSheetName[i-1]=row.getCell(columnName("StepsFormatSheetName",mainSheet)).getStringCellValue();
						
						
					} catch (EncryptedDocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
			  }	  
			  	  
		  }
		  else {
			  
		  }
	}
	public void getDataScenario(Sheet mainSheet) {
		if(mainSheet!=null) {
			  int rowCount=mainSheet.getLastRowNum()-mainSheet.getFirstRowNum();
			  int countYesItems=0;
			  for(int i=1;i<rowCount+1;i++) {
				  Row row=mainSheet.getRow(i);
					  try {
						  System.out.println(row.getCell(columnName("ScenarioExecution",mainSheet)).getStringCellValue()+"||"+row.getCell(columnName("TestCaseExecution",mainSheet)).getStringCellValue());
						if("Y".equals(row.getCell(columnName("ScenarioExecution",mainSheet)).getStringCellValue())&&"Y".equals(row.getCell(columnName("TestCaseExecution",mainSheet)).getStringCellValue())) {
							countYesItems++;
						}
					} catch (EncryptedDocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
			  }
			  Constants.ScenarioNumber=new String[countYesItems];
			  Constants.ScenarioExecution=new String[countYesItems];
			  Constants.ScenarioTestCaseName=new String[countYesItems];
			  Constants.IterationScenarioTestCase=new String[countYesItems];
			  Constants.TestCaseExecution=new String[countYesItems];
			  Constants.countScenarioSheet=countYesItems;
			  int countTemp=0;
			  for(int i=1;i<rowCount+1;i++) {
				  
				  Row row=mainSheet.getRow(i);
					  try {
						if("Y".equals(row.getCell(columnName("ScenarioExecution",mainSheet)).getStringCellValue())&&"Y".equals(row.getCell(columnName("TestCaseExecution",mainSheet)).getStringCellValue())) {
						Constants.ScenarioNumber[countTemp]=row.getCell(columnName("Scenario",mainSheet)).getStringCellValue();
						Constants.ScenarioExecution[countTemp]=row.getCell(columnName("ScenarioExecution",mainSheet)).getStringCellValue();
						Constants.ScenarioTestCaseName[countTemp]=row.getCell(columnName("TestCaseName",mainSheet)).getStringCellValue();
						Constants.IterationScenarioTestCase[countTemp]=row.getCell(columnName("Iteration",mainSheet)).getStringCellValue();
						Constants.TestCaseExecution[countTemp]=row.getCell(columnName("TestCaseExecution",mainSheet)).getStringCellValue();
						countTemp++;
						}
						
						
					} catch (EncryptedDocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
			  }	  
			  	  
		  }
		  else {
			  
		  }
	}
	public void getDataTestCases(Sheet mainSheet) {
		if(mainSheet!=null) {
			  int rowCount=mainSheet.getLastRowNum()-mainSheet.getFirstRowNum();
			  Constants.TestStepsCounter=new int[Constants.countScenarioSheet];
			  int tempcount=0;
			  for(int i=0;i<Constants.countScenarioSheet;i++) {
				  for(int j=1;j<rowCount+1;j++) {
					  Row row=mainSheet.getRow(j);
						  try {
							  if(Constants.ScenarioTestCaseName[i].equals(row.getCell(columnName("TestCaseName",mainSheet)).getStringCellValue())&&"Y".equals(row.getCell(columnName("Execution",mainSheet)).getStringCellValue())) {
								  tempcount++;
							  }
							  
						} catch (EncryptedDocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  
				  }
				  Constants.TestStepsCounter[i]=tempcount;
				  tempcount=0;
			  }
			  for(int i=0;i<Constants.countScenarioSheet;i++) {
				  tempcount=0;
				  Constants.TestDataTestCaseName=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataSteps=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataStepToPerform=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataObjectType=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataObject=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataWait=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataExecution=new String[Constants.TestStepsCounter[i]];
				  Constants.TestDataDV=new String[Constants.TestStepsCounter[i]];
				  for(int j=1;j<rowCount+1;j++) {
					  Row row=mainSheet.getRow(j);
						  try {
							  if(Constants.ScenarioTestCaseName[i].equals(row.getCell(columnName("TestCaseName",mainSheet)).getStringCellValue())&&"Y".equals(row.getCell(columnName("Execution",mainSheet)).getStringCellValue())) {
								  Constants.TestDataTestCaseName[tempcount]=valueOFExcelCell(row.getCell(columnName("TestCaseName",mainSheet)));
								  Constants.TestDataSteps[tempcount]=valueOFExcelCell(row.getCell(columnName("Steps",mainSheet)));
								  Constants.TestDataStepToPerform[tempcount]=valueOFExcelCell(row.getCell(columnName("StepToPerform",mainSheet)));
								  Constants.TestDataObjectType[tempcount]=valueOFExcelCell(row.getCell(columnName("ObjectType",mainSheet)));
								  Constants.TestDataObject[tempcount]=valueOFExcelCell(row.getCell(columnName("Object",mainSheet)));
								  Constants.TestDataWait[tempcount]=valueOFExcelCell(row.getCell(columnName("Wait",mainSheet)));
								  Constants.TestDataExecution[tempcount]=valueOFExcelCell(row.getCell(columnName("Execution",mainSheet)));
								  Constants.TestDataDV[tempcount]=valueOFExcelCell(row.getCell(columnName(Constants.IterationScenarioTestCase[i],mainSheet)));
								  tempcount++;
							  }
							  
						} catch (EncryptedDocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  
				  }
				  Constants.TestDataStepsHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataSteps);
				  Constants.TestDataStepToPerformHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataStepToPerform);
				  Constants.TestDataObjectTypeHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataObjectType);
				  Constants.TestDataObjectHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataObject);
				  Constants.TestDataWaitHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataWait);
				  Constants.TestDataExecutionHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataExecution);
				  Constants.TestDataDVHash.put("T"+i+","+Constants.ScenarioTestCaseName[i], Constants.TestDataDV);
				  
				  Constants.TestDataTestCaseName=null;
				  Constants.TestDataSteps=null;
				  Constants.TestDataStepToPerform=null;
				  Constants.TestDataObjectType=null;
				  Constants.TestDataObject=null;
				  Constants.TestDataWait=null;
				  Constants.TestDataExecution=null;
				  Constants.TestDataDV=null;
			  }
			 /* TreeMap<String, String[]> sorted = new TreeMap<>(); 
				  sorted.putAll(Constants.TestDataStepsHash); 
				  
			        // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataStepsHash.clear();
			        Constants.TestDataStepsHash.putAll(sorted);
			        sorted.clear();
			        sorted.putAll(Constants.TestDataStepToPerformHash); 
					  
			        // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataStepToPerformHash.clear();
			        Constants.TestDataStepToPerformHash.putAll(sorted);
			        sorted.clear();
			        sorted.putAll(Constants.TestDataObjectTypeHash); 
					  
			        // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataObjectTypeHash.clear();
			        Constants.TestDataObjectTypeHash.putAll(sorted);
			        sorted.clear();
			        sorted.putAll(Constants.TestDataObjectHash); 
					  
			        // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataObjectHash.clear();
			        Constants.TestDataObjectHash.putAll(sorted);
			        sorted.clear();
			        sorted.putAll(Constants.TestDataExecutionHash); 
					  
			        // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataExecutionHash.clear();
			        Constants.TestDataExecutionHash.putAll(sorted);
			        sorted.clear();
			        sorted.putAll(Constants.TestDataDVHash); 
					  
			        // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataDVHash.clear();
			        Constants.TestDataDVHash.putAll(sorted);
			        sorted.clear();
			        
			        sorted.putAll(Constants.TestDataWaitHash); 
			     // Display the TreeMap which is naturally sorted 
			        for (Map.Entry<String, String[]> entry : sorted.entrySet()) { 
			            System.out.println("Key = " + entry.getKey() +  
			                         ", Value = " + entry.getValue());}
			        Constants.TestDataWaitHash.clear();
			        Constants.TestDataWaitHash.putAll(sorted);
			        sorted.clear();*/
			        
		  }
		  else {
			  
		  }
	}
	public String valueOFExcelCell(Cell cell) {
		String value="";
		if(cell==null)
		{
	       value="";
		}
		else {
		if(cell.getCellType()==CellType.BLANK) {
			value="";
		}
		else if(cell.getCellType()==CellType.STRING) {
			value=cell.getStringCellValue();
		}
		else if(cell.getCellType()==CellType.NUMERIC) {
			double temp=cell.getNumericCellValue();
			value=String.valueOf(temp);
		}
		else if(cell.getCellType()==CellType.BOOLEAN) {
			value=String.valueOf(cell.getBooleanCellValue());
		}
		else {
			value="";
		}
		}
		return value;
	}
	public static int columnName(String a,Sheet sh) throws EncryptedDocumentException, IOException {

	    int coefficient = 0;
	    Row row = sh.getRow(0);
	    int cellNum = row.getPhysicalNumberOfCells();
	    for (int i = 0; i < cellNum; i++) {
	        if ((row.getCell(i).toString()).equals(a)) {
	            coefficient = i;
	        }
	    }

	    return coefficient;
	}
}
