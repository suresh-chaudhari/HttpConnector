package suresh.http.dataobject;
/**
 * Http and Https Connector Response 
 * @author suresh
 *
 */
public class HttpConnectorResponse {

	private int statusCode;
	private String statusMessage;
	private String body;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public HttpConnectorResponse() {
	
	}
	public HttpConnectorResponse(int statusCode, String body) {
		this.statusCode = statusCode;
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "HttpResponse [statusCode=" + statusCode + ", statusMessage="
				+ statusMessage + ", body=" + body + "]";
	}
	
}
