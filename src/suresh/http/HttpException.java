package suresh.http;

/**
 * 
 * @author suresh
 *
 */
public class HttpException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -315215163294101434L;

	
	public HttpException(Throwable th) {
		super(th);
	}


	public HttpException(String message) {
		super(message);
	}
}
