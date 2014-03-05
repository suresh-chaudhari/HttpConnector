package suresh.http.dataobject;

import java.util.Map;
/**
 * Stores information about request like character set ,content type , header info and request data map
 * @author suresh
 *
 */
public class HttpContent {

	private String contentType;
	private String charSet;
	private Map<String, String> header; 
	private Map<String, String> requestDataMap; //set parameter value
	private String requestEntityData; //set Request body data
	private String username; 
	private String password;
	
	public HttpContent() {
		
	}

	public HttpContent(String contentType, String charSet) {
		this.contentType = contentType;
		this.charSet = charSet;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	
	public Map<String, String> getRequestDataMap() {
		return requestDataMap;
	}

	public void setRequestDataMap(Map<String, String> requestDataMap) {
		this.requestDataMap = requestDataMap;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public String getRequestEntityData() {
		return requestEntityData;
	}

	public void setRequestEntityData(String requestEntityData) {
		this.requestEntityData = requestEntityData;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}