Generic-Http-Client-Connector
=============================

The Connector is designed as generic way using apache library. 
Using this HTTP Client Connector, You can communicate withing any URL or Restful API.
You can easily send the data as input stream and in the form of query param. 

// Copyright (c) 2013, Suresh Chaudhari
// All rights reserved.


HttpConnector Test Code:
test class is give a basic idea, how to use this generic http client connector for to access any URL and get data from that URL.

package suresh.test;

import java.util.HashMap;
import java.util.Map;

import suresh.http.HttpConnector;
import suresh.http.HttpConstant;
import suresh.http.HttpException;
import suresh.http.dataobject.HttpConnectorResponse;
import suresh.http.dataobject.HttpContent;

/**
*
* @author suresh
*
*/
public class Test {

private static String baseUrl = "http://localhost:8080/Servlet";
private static String testReq ="suresh";

public static void main(String[] args) {
try {
HttpConnectorResponse response = getResponse();
System.out.println(response);
} catch (HttpException e) {
e.printStackTrace();
}
}

private static HttpConnectorResponse getResponse() throws HttpException {
String testUrl = baseUrl + "/test";
HttpConnector httpConnector = new HttpConnector();
// application/x-www-form-urlencoded
HttpContent content = new HttpContent("application/json", HttpConstant.UTF_8); //set content Type and charset

/**
* send Reqquest body data as input stream, you can send json,xml string.
*uncomment the below code of setRequestEntityData entity if you want to send data in Request body
*/
// content.setRequestEntityData("Send Request Body data as stream"); //

/**
* You can send data either as also query param using below commented method
* Note: set any one data either queryparmadata or request entity data
*/
Map<String, String> queryParamData = new HashMap<String, String>();
queryParamData.put("req", testReq);
content.setRequestDataMap(queryParamData);
/**
* If url needs credential to authenticate, provide credential as below format
* If it does not needs you don't need to be set
* you don't need to set the username and password content Object....
*/
// content.setUsername("admins");
// content.setUsername("password@123");

/**
* execute httpconnector method here
* httpconnector has post,get,put,delete method
*/
HttpConnectorResponse response = httpConnector.post(testUrl, content);
return response;
}	
}


