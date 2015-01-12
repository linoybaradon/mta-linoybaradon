package exception;

/**
 * 
 * @author linoy
 *exception to be thrown when adding more stocks than
  allowed
 */

public class PortfolioFullException extends Exception {

	public  PortfolioFullException()    {
		super ("You have reached maximum portfolio size!");
	}

}
