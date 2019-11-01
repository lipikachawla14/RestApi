package businessLogic;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utills.Reports.ExtentReportManager;
import commonUtilities.CommonUtills;
import dataSource.LoadProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BusinessLogic {
	public static RequestSpecification httpReq;
	public static Response resp;
	protected static int Revision;
	public static int list_id;
	public String taskid;
	public ExtentTest test;
	public static ExtentReports report= ExtentReportManager.getInstance();
	public static int getRevision() {
		int rev = Revision;
		Revision = rev+1;
		return rev;
	}
	public void createRequest(String req) {
		test = report.startTest("List");
		test.log(LogStatus.INFO, "Starting Test");
		RestAssured.baseURI= LoadProperties.getvariable("BaseUrl", "config");
		httpReq = RestAssured.given();
		if(req.equalsIgnoreCase("getalllists")) {
			test.log(LogStatus.INFO, "Get All Lists");
			httpReq= CommonUtills.addHeader(httpReq);
			httpReq.log().all();
		}
		else if (req.equalsIgnoreCase("getSpecificList")) {
			test.log(LogStatus.INFO, "Get Specific List");
			httpReq.pathParam("id", LoadProperties.getvariable("GetListID", "config"));
			httpReq = CommonUtills.addHeader(httpReq);
		}
		else if (req.equalsIgnoreCase("createList"))
		{	test.log(LogStatus.INFO, "Created List");
			httpReq = CommonUtills.addHeader(httpReq);
			httpReq.body(CommonUtills.createBody(req));
		}
		else if (req.equalsIgnoreCase("patchList"))
		{   test.log(LogStatus.INFO, "Patch List");
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",Integer.toString(list_id), httpReq);
			httpReq.body(CommonUtills.createBody(req));
			httpReq.log().all();
		}
		else if(req.equalsIgnoreCase("putList"))
		{   test.log(LogStatus.INFO, "Put List");
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",Integer.toString(list_id), httpReq);
			httpReq.body(CommonUtills.createBody(req));
			httpReq.log().all();
			
		}
		else if(req.equalsIgnoreCase("patchTask")) {
			test.log(LogStatus.INFO, "Patch Task");
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",Integer.toString(list_id), httpReq);
			httpReq.body(CommonUtills.createBody(req));
			httpReq.log().all();
		}
		else if(req.equalsIgnoreCase("putTask")) {
			test.log(LogStatus.INFO, "Put Task");
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",Integer.toString(list_id), httpReq);
			httpReq.body(CommonUtills.createBody(req));
			httpReq.log().all();
		}
		else if(req.equalsIgnoreCase("deleteList"))
		{   test.log(LogStatus.INFO, "Delete List");
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",Integer.toString(list_id),httpReq);
			CommonUtills.addqueryParam("revision",Integer.toString(getRevision()), httpReq);
		}
		else if(req.equalsIgnoreCase("deleteTask"))
		{   test.log(LogStatus.INFO, "Delete Task");
			httpReq	.log().all();
			httpReq = CommonUtills.addHeader(httpReq);
			CommonUtills.addPathVariable("id",taskid, httpReq);
			CommonUtills.addqueryParam("revision",LoadProperties.getvariable("Revision", "config"), httpReq);
		}
		else if(req.equalsIgnoreCase("createTask"))
		{   test.log(LogStatus.INFO, "Create Task");
			httpReq =CommonUtills.addHeader(httpReq);
			httpReq.body(CommonUtills.createBody(req));
			
		}
		else {
			System.out.println("Request not found ");
		}
			
	}
	
	public void createResponse(String res) {
		if(res.equalsIgnoreCase("getAllLists")) {
			test.log(LogStatus.INFO, "Get Lists");
			resp = httpReq.when().get("/lists");
			resp.then().log().all().assertThat().statusCode(200);
		}
		else if(res.equalsIgnoreCase("getSpecificList")) {
			test.log(LogStatus.INFO, "Specific List");
			resp = httpReq.when().get("lists/{id}");
			resp.then().log().all().assertThat().statusCode(200);
		}
		else if(res.equalsIgnoreCase("createList")) {
			test.log(LogStatus.INFO, "Create Lists");
			resp=httpReq.when().post("lists");
			list_id=resp.jsonPath().get("id");
			resp.then().log().all().assertThat().statusCode(201);
			Revision = Integer.parseInt( resp.jsonPath().get("revision").toString());
		}
		else if(res.equalsIgnoreCase("patchList")) {
			test.log(LogStatus.INFO, "Patch Lists");
			resp = httpReq.when().patch("lists/{id}");
			resp.then().log().all().assertThat().statusCode(200);
		}
		else if(res.equalsIgnoreCase("putList")) {
			test.log(LogStatus.INFO, "Put Lists");
			resp = httpReq.when().put("lists/{id}");
			resp.then().log().all().statusCode(200);
		}	
		else if(res.equalsIgnoreCase("patchTask")) {
			test.log(LogStatus.INFO, "Patch Tasks");
			resp = httpReq.when().put("lists/{id}");
			resp.then().log().all().statusCode(200);
		}
		else if(res.equalsIgnoreCase("putTask")) {
			test.log(LogStatus.INFO, "Put Tasks");
			resp = httpReq.when().put("lists/{id}");
			resp.then().log().all().statusCode(200);
		}
	    else if(res.equalsIgnoreCase("deleteList")) {
	    	test.log(LogStatus.INFO, "Delete Lists");
			resp = httpReq.when().delete("lists/{id}");	
			resp.then().log().all().statusCode(204);
	    }
	    else if(res.equalsIgnoreCase("deleteTask")) {
	    	test.log(LogStatus.INFO, "Delete Tasks");
	    	httpReq.log().all();
			resp = httpReq.when().delete("tasks/{id}");
			resp.then().log().all().statusCode(204);
		}
		else if(res.equalsIgnoreCase("CreateTask")){
			test.log(LogStatus.INFO, "Create Tasks");
			resp = httpReq.when().post("tasks");
			resp.then().log().all().statusCode(201);
			taskid = resp.jsonPath().get("id").toString();
			
		}
		else {
			System.out.println("Response not found ");
		}

	}
}
