package suresh.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import suresh.http.HttpConstant.HttpMethod;
import suresh.http.dataobject.HttpConnectorResponse;
import suresh.http.dataobject.HttpContent;

/**
 * Protocol connector for http communication.
 * @author suresh
 *
 */
public class HttpConnector implements AbstractHttpConnector {

	private HttpClient httpClient = null;

	private int connectionTimeOut = 30*1000;

	private int connectionPerHost = 30; 

	public HttpConnector() {
		init();
	}
	/**
	 * initialize http connection parameter
	 */
	public void init(){
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setSoTimeout(getConnectionTimeOut());
		params.setDefaultMaxConnectionsPerHost(getConnectionPerHost());

		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.setParams(params);
		httpClient = new HttpClient(connectionManager);
	}

	@Override
	public HttpConnectorResponse get(String url, HttpContent content) throws HttpException  {
		return buildAndExecuteRequest(content, new GetMethod(url));
	}
	@Override
	public HttpConnectorResponse post(String url, HttpContent content) throws HttpException {
		return buildAndExecuteRequest(content, new PostMethod(url));
	}
	@Override
	public HttpConnectorResponse delete(String url, HttpContent content) throws HttpException {
		return buildAndExecuteRequest(content, new DeleteMethod(url));
	}
	@Override
	public HttpConnectorResponse put(String url, HttpContent content) throws HttpException {
		return buildAndExecuteRequest(content, new PutMethod(url));

	}
	/**
	 * 
	 * @param content
	 * @param method
	 * @return
	 * @throws HttpException
	 */
	private HttpConnectorResponse buildAndExecuteRequest(HttpContent content, HttpMethodBase method ) throws HttpException {
		HttpRequest	request = new HttpRequest(httpClient, method);
		request.setContent(content);
		addHeader(request, content.getHeader());
		return request.execute();
	}
	/**
	 * add header 
	 * @param request
	 * @param headerMap
	 */
	private void addHeader(HttpRequest request , Map<String, String> headerMap) {
		if (headerMap != null && !headerMap.isEmpty()) {
			for (String key : headerMap.keySet()) {
				request.addHeader(key, headerMap.get(key));
			}
		}
	}

	public HttpConnector setConnectionTimeOut(int connectionTimeOut) {
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(connectionTimeOut);
		return this;
	}
	public int getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public HttpConnector setConnectionPerHost(int connectionPerHost) {
		httpClient.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(connectionPerHost);
		return this;
	}
	public int getConnectionPerHost() {
		return connectionPerHost;
	}

	private class HttpRequest {

		private HttpClient httpClient;

		private HttpMethodBase method;

		/**
		 * 
		 * @param httpClient
		 * @param method
		 */
		public HttpRequest(HttpClient httpClient, HttpMethodBase method) {
			this.httpClient = httpClient;
			this.method = method;
		}
		/**
		 * 
		 * @param name
		 * @param value
		 */
		public void addHeader(String name, String value) {
			method.addRequestHeader(name, value);
		}
		/**
		 * 
		 * @return
		 * @throws HttpException 
		 * @throws IOException
		 */
		public HttpConnectorResponse execute() throws HttpException {
			try {
				int statusCode = httpClient.executeMethod(method);				
				String responseBody =  method.getResponseBodyAsString();
				HttpConnectorResponse resp = new HttpConnectorResponse(statusCode, responseBody);
				resp.setStatusMessage(method.getStatusLine().getReasonPhrase());
				return resp;
			} catch (IOException e) {
				throw new HttpException(e);
			} catch (Exception e) {
				throw new HttpException(e);
			}finally {
				method.releaseConnection();
			}
		}
		/**
		 * set content type, charcter set and request parameter
		 * @param content
		 * @throws HttpException 
		 * @throws UnsupportedEncodingException 
		 */
		public void setContent(HttpContent content) throws HttpException {
			try {
				if (content == null)
					return; 
				if(content.getUsername() !=null && content.getUsername() != null) {
					Credentials defaultcreds = new UsernamePasswordCredentials(content.getUsername(), content.getPassword());
					httpClient.getState().setCredentials(AuthScope.ANY, defaultcreds);	
				}
				if (content.getContentType() != null)
					method.addRequestHeader(HttpConstant.CONTENT_TYPE_PARAM, content.getContentType());
				Map<String, String> dataMap = content.getRequestDataMap();
				List<NameValuePair> param = new ArrayList<NameValuePair>();
				if (dataMap != null && !dataMap.isEmpty()) {
					Iterator<String> itr = dataMap.keySet().iterator();
					while (itr.hasNext()) {
						String key = itr.next();
						param.add(new NameValuePair(key, dataMap.get(key)));
					}
				}
				if (method.getName().equals(HttpMethod.POST.name())) {
					PostMethod post = (PostMethod) method;
					if(content.getRequestEntityData()!=null)
						post.setRequestEntity(new StringRequestEntity(content.getRequestEntityData(), content.getContentType(), content.getCharSet()));
					if(param!=null && !param.isEmpty())
						post.setQueryString(param.toArray(new NameValuePair[param.size()]));
				} else if(method.getName().equals(HttpMethod.DELETE.name())){
					DeleteMethod delete = (DeleteMethod) method;
					if(param!=null && !param.isEmpty())
						delete.setQueryString(param.toArray(new NameValuePair[param.size()]));
				} else if(method.getName().equals(HttpMethod.PUT.name())){
					PutMethod put = (PutMethod) method;
					if(content.getRequestEntityData()!=null)
						put.setRequestEntity(new StringRequestEntity(content.getRequestEntityData(), content.getContentType(), content.getCharSet()));
					if(param!=null && !param.isEmpty())
						put.setQueryString(param.toArray(new NameValuePair[param.size()]));
				} else 
					method.setQueryString(param.toArray(new NameValuePair[param.size()]));
			} catch (UnsupportedEncodingException e) {
				throw new HttpException(e);
			}
		}
	}

}
