package exception;

public class BalanceException extends Exception {

	/**
	 * exception to be thrown when the portfolio balance becomes
    negative
	 * @param balance
	 * @param purchaseAmount
	 */

	public BalanceException(float balance,float purchaseAmount) {
		super("You want to buy stocks with: " +purchaseAmount+ "$  you have only " +balance+ "$");
	}

}
