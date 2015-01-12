package exception;
 
/**
 * 
 * @author linoy
 *  exception to be thrown when a stock already exists 
 */
public class StockAlreadyExistsException extends Exception {

	public StockAlreadyExistsException(String symbol) {
		super("Stock " +symbol+ " is already exists");
		
	}

}
