import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LWMGUI extends JFrame implements ActionListener {
	private Wine wine;
	private CustomerAccount custAcct;
	private JButton psale, preturn;
	private JLabel litemName, litemQuantity, litemPrice, ltransAmount, lcurrBalance, lpurchWine, lwineName;
	private JTextField titemName, titemQuantity, titemPrice, ttransAmount, tcurrBalance;

	public LWMGUI(Wine w, CustomerAccount c) {
		wine = w;
		custAcct = c;

		// Creating Dialog box to get customer's name
		String custName = JOptionPane.showInputDialog("Please enter the customer name: ");
		if (custName.isEmpty() || custName == null) {
			System.exit(0);
		}

		// Creating dialog box to get the current balance for the given customer
		String currBalanceTxt;
		while (true) {
			currBalanceTxt = JOptionPane.showInputDialog("Please enter the current balance for this customer: ");

			try {
				Double currBalance = Double.parseDouble(currBalanceTxt);
				break;

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Please enter a numeric value!", "Non-numeric value error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		Double currBalance = Double.parseDouble(currBalanceTxt);

		// Setting customer account values
		c.setAccountName(custName);
		c.setCurrBalance(currBalance);
		
		
		
		// Creating the GUI
		setTitle("Lilybank Wine Merchants: " + custAcct.getAccountName());
		setSize(560, 160);
		setLocation(200, 200);
	
		createTop();
		centerPane();
		createBottom();
		
		// Setting the current balance value and removing the negative sign by replacing it with CR instead
		if (c.getCurrBalance() < 0){
			tcurrBalance.setText("£" + c.getCurrBalance()*(-1) + " CR");
		}
		else {
			tcurrBalance.setText("£" + c.getCurrBalance());
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void createTop() {
		// Method that creates the top part of the GUI window. 
		litemName = new JLabel("Name:");
		litemQuantity = new JLabel("Quantity:");
		litemPrice = new JLabel("Price: £");

		titemName = new JTextField(15);
		titemName.addActionListener(this);
		titemQuantity = new JTextField(6);
		titemQuantity.addActionListener(this);
		titemPrice = new JTextField(7);
		titemPrice.addActionListener(this);

		JPanel topPan = new JPanel();
		topPan.add(litemName); topPan.add(titemName);
		topPan.add(litemQuantity); topPan.add(titemQuantity);
		topPan.add(litemPrice); topPan.add(titemPrice);
		add(topPan, "North");
	}
	
	private void centerPane() {
		// Method that creates the center part of the GUI window.
		psale = new JButton("Process Sale");
		preturn = new JButton("Process Return");
		psale.addActionListener(this);
		preturn.addActionListener(this);
		lpurchWine = new JLabel("");
		lwineName = new JLabel("");

		JPanel buttonPan = new JPanel();
		buttonPan.add(psale); buttonPan.add(preturn);
		buttonPan.add(lpurchWine); buttonPan.add(lwineName);
		add(buttonPan, "Center");
	}

	private void createBottom() {
		// Method that creates the bottom part of the GUI window.
		ltransAmount = new JLabel("Amount of Transaction:");
		lcurrBalance = new JLabel("Current balance:");
		ttransAmount = new JTextField(6);
		tcurrBalance = new JTextField(6);
		ttransAmount.setEnabled(false);
		tcurrBalance.setEnabled(false);
		JPanel lowerPan = new JPanel();
		lowerPan.add(ltransAmount); lowerPan.add(ttransAmount);
		lowerPan.add(lcurrBalance); lowerPan.add(tcurrBalance);
		add(lowerPan, "South");
	}

	public void clear() {
		titemName.setText("");
		titemQuantity.setText("");
		titemPrice.setText("");
	}

	public void saleAction() {
		Wine w = wine;
		CustomerAccount ct = custAcct;

		String wName = titemName.getText();

		// Implementing sanity checks
		if (wName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a product name!", "No product name found",
					JOptionPane.ERROR_MESSAGE);
			clear();
		}
		
		try {
			int wQnty = Integer.parseInt(titemQuantity.getText());

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Please enter a numeric value!", "Non-numeric value error",
					JOptionPane.ERROR_MESSAGE);
		}
		int wQnty = Integer.parseInt(titemQuantity.getText());

		if (wQnty < 0) {
			JOptionPane.showMessageDialog(null, "Please enter a positive value for quantity!", "Negative value error",
					JOptionPane.ERROR_MESSAGE);
			clear();
		}
	

		Double wPrice = Double.parseDouble(titemPrice.getText());
		if (wPrice < 0) {
			JOptionPane.showMessageDialog(null, "Please enter a positive value for price!", "Negative value error",
					JOptionPane.ERROR_MESSAGE);
			clear();
		}
		
		// Setting the value according to the ones provided by the user
		w.setWineName(wName);
		w.setWineQnty(wQnty);
		w.setWinePrice(wPrice);

		Double saleAmount = ct.sale(wQnty, wPrice);
		lwineName.setText(w.getWineName());
		ttransAmount.setText("£" + saleAmount);
		if (ct.getCurrBalance() < 0){
			tcurrBalance.setText("£" + ct.getCurrBalance()*(-1) + " CR");
		}
		else {
			tcurrBalance.setText("£" + ct.getCurrBalance());
		}
		lpurchWine.setText("Item Purchased: ");
		
		clear();
	}

	public void returnAction() {
		Wine w = wine;
		CustomerAccount ct = custAcct;

		// Implementing sanity checks
		String wName = titemName.getText();
		if (wName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a product name!", "No product name found",
					JOptionPane.ERROR_MESSAGE);
			clear();
		}
		int wQnty = Integer.parseInt(titemQuantity.getText());

		if (wQnty < 0) {
			JOptionPane.showMessageDialog(null, "Please enter a positive value for quantity!", "Negative value error",
					JOptionPane.ERROR_MESSAGE);
			clear();
		}

		Double wPrice = Double.parseDouble(titemPrice.getText());
		if (wPrice < 0) {
			JOptionPane.showMessageDialog(null, "Please enter a positive value for price!", "Negative value error",
					JOptionPane.ERROR_MESSAGE);
			clear();
		}

		// Setting the value according to the ones provided by the user
		w.setWineName(wName);
		w.setWineQnty(wQnty);
		w.setWinePrice(wPrice);

		Double returnAmount = ct.returnBottle(wQnty, wPrice);
		lwineName.setText(w.getWineName());
		ttransAmount.setText("£" + returnAmount);
		
		if (ct.getCurrBalance() < 0){
			tcurrBalance.setText("£" + ct.getCurrBalance()*(-1) + " CR");
		}
		else {
			tcurrBalance.setText("£" + ct.getCurrBalance());
		}
		lpurchWine.setText("Item returned: ");
		clear();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == psale) {
			saleAction();
		} else if (e.getSource() == preturn) {
			returnAction();
		}
	}
}
