package ReadTestCaseData;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CheckRunMode {
	
	public static boolean isRunable(String TestCaseName) throws IOException
	{
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\facebook\\resource\\TestCaseData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(ip);
		XSSFSheet sh =wb.getSheet("TestCases");
		
		//total no of rows
		int totalRows =sh.getPhysicalNumberOfRows();
		int totalColumns= sh.getRow(0).getPhysicalNumberOfCells();
		
		System.out.println("total no of rows "+ totalRows + " : : " + "total no of columns "+totalColumns );
		
		for(int i=1,j=1;i<totalRows;i++) //data starts from 1st row 
		{
			if(sh.getRow(i).getCell(j).getStringCellValue().equals(TestCaseName))
			{
				sh.getRow(i).getCell(j+1).getStringCellValue();
				return true;
			}

		}
		return false;
	}

}
