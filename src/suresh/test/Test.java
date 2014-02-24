package suresh.test;

import java.util.HashMap;
import java.util.Map;

import suresh.http.HttpConnector;
import suresh.http.HttpException;
import suresh.http.dataobject.HttpConnectorResponse;
import suresh.http.dataobject.HttpContent;

public class Test {

	private static String baseUrl = "http://{serverIp}:8080/"; 

	public static void main(String[] args) {
		try {
			HttpConnectorResponse response = getResponse();
			System.out.println(response);
			System.out.println(response.getBody());
		} catch (HttpException e) {
			e.printStackTrace();
		}
	}

	private static HttpConnectorResponse getResponse() throws HttpException {
		String testUrl = baseUrl + "/test/";
		HttpConnector httpConnector = new HttpConnector();
		HttpContent content = new HttpContent("application/json", "UTF-8"); //set content Type and charset
		
		/**
		 * send Reqquest body data as input stream, you can send json,xml based on your requirement to server accept 
		 */
		content.setRequestEntityData("Send Request Body data as stream"); //
		
		/**
		 * You can send data either as also query param using below commented method
		 * Note: set any one data either queryparmadata or request entity data
		 */
//		Map<String, String> queryParamData = new HashMap<String, String>();
//		queryParamData.put("req", "test");
//		content.setRequestDataMap(queryParamData);
		/**
		 * If url needs credential to authenticate, provide credential as below format
		 * If it does not needs you  don't need to be set
		 * you can comment this below method....
		 * //content.setCredentials(credentials);
		 */
		Map<String, String> credentials = new HashMap<String, String>();
		credentials.put("admins", "password@123");
		content.setCredentials(credentials);

		/**
		 * execute httpconnector method here
		 * httpconnector has post,get,put,delete method
		 */
		HttpConnectorResponse response = httpConnector.post(testUrl, content);
		return response;
	}	
}


