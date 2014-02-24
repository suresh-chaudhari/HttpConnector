package suresh.http;

import suresh.http.dataobject.HttpConnectorResponse;
import suresh.http.dataobject.HttpContent;


/**
 * AbstractHttpConnector defines the interfaces for communicating http and https 
 * with a managed method.
 * @author suresh
 *
 */
public interface AbstractHttpConnector {

	public HttpConnectorResponse get(String url, HttpContent content) throws HttpException;

	public HttpConnectorResponse post(String url, HttpContent content) throws HttpException;
	
	public HttpConnectorResponse delete(String url, HttpContent content) throws HttpException;
	
	public HttpConnectorResponse put(String url, HttpContent content) throws HttpException;
	
}
