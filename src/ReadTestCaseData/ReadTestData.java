package ReadTestCaseData;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestData {
	
	
	@SuppressWarnings("resource")
	public static Object[][] ReadData(String TestCaseName) throws IOException
	{
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\facebook\\resource\\TestCaseData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(ip);
		XSSFSheet sh =wb.getSheet(TestCaseName);
		
		//total no of rows
		int totalRows =sh.getPhysicalNumberOfRows();
		int totalColumns= sh.getRow(0).getPhysicalNumberOfCells();
		
		System.out.println("total no of rows "+ totalRows + " : : " + "total no of columns "+totalColumns );
		
		//lets create the Object array
		Object data[][]= new Object[totalRows-1][totalColumns];
		
		for(int i=1;i<totalRows;i++)
		{
			for(int j=0;j<totalColumns;j++)
			{ 
				data[i-1][j]=sh.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(sh.getRow(i).getCell(j).getStringCellValue());
			}
		}
		return data;
		
		
	
			}


}
