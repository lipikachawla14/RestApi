package commonUtilities;

import java.util.HashMap;

import businessLogic.BusinessLogic;
import dataSource.LoadProperties;
import io.restassured.specification.RequestSpecification;

public class CommonUtills extends BusinessLogic {
	public static RequestSpecification addHeader(RequestSpecification httpReq) {
		httpReq.headers(CreateHeader());
		System.out.println(httpReq);
		httpReq.log().all();
		return httpReq;
	}
	public static RequestSpecification addqueryParam(String key,String value,RequestSpecification httpReq) {
		httpReq.queryParam(key, value);
		return httpReq;
	}
	public static RequestSpecification addPathVariable(String key,String value,RequestSpecification httpReq) {
		
		httpReq.pathParam(key, value);
		return httpReq;
	}
	public static HashMap<String, String> CreateHeader() {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", LoadProperties.getvariable("Content-Type","config"));
		headers.put("X-Client-ID", LoadProperties.getvariable("ClientID", "config"));
		headers.put("X-Access-Token",LoadProperties.getvariable("TokenID", "config"));
		return headers;
	}
	public static HashMap<String,Object> createBody(String req){
		HashMap<String, Object> body = new HashMap<String, Object>();
		if(req.equalsIgnoreCase("createList")) {
			body.put("title", LoadProperties.getvariable("Title", "config"));
			return body;
		}
		else if(req.equalsIgnoreCase("patchList")) {
			body.put("newTitle", LoadProperties.getvariable("Title","config"));
			body.put("revision", getRevision());
			body.put("listID", Integer.parseInt(LoadProperties.getvariable("GetListID", "config")));
			return body;
		}
		else if(req.equalsIgnoreCase("putList")) {
			body.put("listID", Integer.parseInt(LoadProperties.getvariable("GetListID", "config")));
			body.put("title", LoadProperties.getvariable("Title","config"));
			body.put("revision", getRevision());
			return body;
		}
		else if(req.equalsIgnoreCase("patchTask")) {
			body.put("newTitle", LoadProperties.getvariable("Title","config"));
			body.put("revision", getRevision());
			
			return body;
		}
		else if(req.equalsIgnoreCase("putTask")) {
			body.put("newTitle", LoadProperties.getvariable("Title","config"));
			body.put("revision", getRevision());
			
			return body;
		}
		else if(req.equalsIgnoreCase("createTask")) {
			body.put("list_id", Integer.parseInt(LoadProperties.getvariable("GetListID","config")));
			body.put("title", LoadProperties.getvariable("Title","config"));
		}
		return body;
		}
	}



