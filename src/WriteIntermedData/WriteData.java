package WriteIntermedData;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class WriteData {

	public static Object[][] ReadData(String TestCaseName) throws IOException
	{
		FileOutputStream ip = new FileOutputStream(System.getProperty("user.dir")+"\\src\\com\\avaloq\\crep\\resource\\TestCaseData.xlsx");
		//XSSFWorkbook wb = new XSSFWorkbook(ip);
		//XSSFSheet sh =wb.getSheet(TestCaseName);
		// http://www.codejava.net/coding/java-example-to-update-existing-excel-files-using-apache-poi
		
		
		return null;
		
	}
	
	
}
