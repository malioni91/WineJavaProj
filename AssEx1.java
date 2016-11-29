import javax.swing.JFrame;

public class AssEx1 {
	
	public static void main(String[] arg)
	{
		Wine wine = new Wine();
		CustomerAccount cuAcct = new CustomerAccount();
		LWMGUI g = new LWMGUI(wine, cuAcct);
	}
}
