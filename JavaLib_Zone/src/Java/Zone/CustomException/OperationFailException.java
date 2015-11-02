package Java.Zone.CustomException;
/**
 * @version 2015.7.15
 * @author Zone
 *
 */
public class OperationFailException extends RuntimeException{
	private static final long serialVersionUID = 4101921170259562709L;
	public OperationFailException() {
	}
	public OperationFailException(String detailMessage) {
		 super(detailMessage);
	}
}
