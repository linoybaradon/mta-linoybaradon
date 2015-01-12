package exception;

/**
 * exception to be thrown when a stock doesn’t exist.
 * @author linoy
 *
 */

public class StockNotExistException extends Exception {

	public StockNotExistException() {
		super("Sorry this stock is not exist in your portfolio");
	}

}
