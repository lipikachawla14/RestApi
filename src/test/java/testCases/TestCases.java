package testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestCases extends BaseClass
{
	@Test(priority=1)
	public void get_list() {
		createRequest("getalllists");
		createResponse("getAllLists");
		logger.info("get_all_lists");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
		}
	
	@Test(priority=2)
	public void get_specificList() {
		createRequest("getSpecificList");
		createResponse("getSpecificList");
		logger.info("specific_lists");
		
		}
	
	@Test(priority=3)
	public void post_List() {
		createRequest("createList");
		createResponse("createList");
		logger.info("create_lists");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
		}
	
	@Test(priority=4)
	public void post_Task() {
		createRequest("createTask");
		createResponse("createTask");
		logger.info("create_task");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.get("lists");
		
		}
	
	@Test(priority=5)
	public void put_List() {
		createRequest("putList");
		createResponse("putList");
		logger.info("put_list");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
		}
	
	@Test(priority=6)
	public void patch_list() {
		createRequest("patchList");
		createResponse("patchList");
		logger.info("patch_list");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
		}
	
	@Test(priority=7)
	public void put_task() {
		createRequest("putTask");
		createResponse("putTask");
		logger.info("patch_list");
	}
	
	@Test(priority=8)
	public void patch_task() {
		createRequest("patchTask");
		createResponse("patchTask");
		logger.info("patch_list");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
	}
	
	@Test(priority=9)
	public void delete_list() {
		createRequest("deleteList");
		createResponse("deleteList");
		logger.info("delete_list");
		RestAssured.baseURI="https://developer.wunderlist.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/lists");
		
		}
	
	@Test(priority=10)
	public void delete_task() {
		createRequest("deleteTask");
		createResponse("deleteTask");
		logger.info("delete_task");
		
	}
	
	@AfterClass
	public void refresh() {
		if (!(report == null))
		{
			report.flush();
			
		}

	}
}
