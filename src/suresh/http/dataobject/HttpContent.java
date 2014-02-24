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
	private Map<String, String> requestDataMap;
	private String requestEntityData;
	private Map<String, String> credentials;
	
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

	public Map<String, String> getCredentials() {
		return credentials;
	}

	public void setCredentials(Map<String, String> credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		return "HttpContent [contentType=" + contentType + ", charSet="
				+ charSet + ", header=" + header + ", requestDataMap="
				+ requestDataMap + ", requestEntityData=" + requestEntityData
				+ ", credentials=" + credentials + "]";
	}
	

}