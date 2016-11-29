
public class CustomerAccount {
	
	private String accountName;
	private double currBalance;
	public static Double returnChargefee = 0.20; 
	
	
public CustomerAccount (String accountName, double currBalance)
{
	this.accountName = accountName;
	this.currBalance = currBalance;
}

public CustomerAccount()
{
	this ("", 0.0);
}

//Getters (accessor methods) and setters(modifier methods) 
public String getAccountName() {
	return accountName;
}

public void setAccountName(String accountName) {
	this.accountName = accountName;
}

public double getCurrBalance() {
	return currBalance;
}

public void setCurrBalance(double currBalance) {
	this.currBalance = currBalance;
}

public double sale(int numBottles, Double costBottle)
{
	
	Double saleAmt = costBottle * numBottles;
	// Rounding to two decimals.
	// the Math.round method is explained on pg.44-45 (Chapter 2) of the textbook "Big Java Late Objects".
	saleAmt = (double)Math.round(saleAmt * 100d)/100d;  
	currBalance += saleAmt;
	currBalance = (double)Math.round(currBalance * 100d)/100d;
	return saleAmt;
}

public double returnBottle(int numBottles, Double costBottle)
{
	Double returnAmt = (costBottle * numBottles) * (1 - returnChargefee);
	// Rounding to two decimals.
	// the Math.round method is explained on pg.44-45 (Chapter 2) of the textbook "Big Java Late Objects".
	returnAmt = (double)Math.round(returnAmt * 100d)/100d;  
	currBalance -= returnAmt;
	currBalance = (double)Math.round(currBalance * 100d)/100d;
	return returnAmt;
}


}
