package resources;
import io.restassured.RestAssured;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class BaseClass {
    public static void createBaseUri(String endPoint){
    RestAssured.baseURI=endPoint;
    return;
    }
    public static XSSFSheet readingExcel(String sheetName,String filePath ) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet(sheetName);
        return sheet;
    }
}
