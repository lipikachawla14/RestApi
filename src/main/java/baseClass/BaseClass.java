package baseClass;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;

import Utills.Reports.ExtentReportManager;
import businessLogic.BusinessLogic;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass extends BusinessLogic{
	String req = null;
	public boolean getLists() {
		req = "getalllists";
		
		try {
		req = "getSpecificList";
        createRequest(req);
		createResponse(req);
		return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public static String timeStamp;
	public static String testReportFolderPath;
	public static ExtentReports report= ExtentReportManager.getInstance();
	
	public Logger logger;
	
	@BeforeClass
	public void start() {
		
		logger=Logger.getLogger("Wunderlist");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
		
	

}
