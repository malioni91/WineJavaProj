
public class Wine {
	private String wineName;
	private double winePrice;
	private int wineQnty;
	
public Wine(String wineName, double winePrice, int wineQnty)
{
	this.wineName = wineName;
	this.winePrice = winePrice;
	this.wineQnty = wineQnty;
}

public Wine()
{
	this ("", 0, 0);
}


public String getWineName() {
	return wineName;
}

public void setWineName(String wineName) {
	this.wineName = wineName;
}

public double getWinePrice() {
	return winePrice;
}

public void setWinePrice(double winePrice) {
	this.winePrice = winePrice;
}

public int getWineQnty() {
	return wineQnty;
}

public void setWineQnty(int wineQnty) {
	this.wineQnty = wineQnty;
}

}
