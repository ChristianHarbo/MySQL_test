///*** Plan

// user put in User Name and Password

// due you whillig whant the item.
// have may is in shock 
// have many do you want?
// have may is left to by
// do you want more?

// Closed order:

//		remuver purchase items from stock 
// 		Dad to order list in MyQSL
// 		make inv. 


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class MySQLtest {
	
	public static void main(String[] args) {

		// Seting up user / Password
		Choose choose = new Choose();
		InStoskClass inStock = new InStoskClass();
		choose.setUserName("Chrih");
		
		choose.setUserPassword("Juliasen2!FF");
		
		

		// calls InStoskClass
		

		// show stock overview and will also give a maximum of lines
		int maxItems = inStock.getStockOverview();

		// Stat text
		System.out.println("\n\nHi\nWhat would you like to purchase ?");

		// ** user need to choose item to buy

		// input String
		String startInto = "plieas give the ID number for the iteme that you would like to purchase : ";
		String ifWrong = "There is no item with this number.";

		// user need to choose item to buy
		ClassObjects object = new ClassObjects();
		choose.setUserChoiceId(object.inputInt(maxItems, startInto, ifWrong));

		// asking user have many to bay
		choose.setChoiceOfIteme(choose.getUserChoiceId());

		// user will tell, have many to bay
		String startIntoTO = "";
		String ifWrongTO = "We bo not have that many in stock";
		
		
		choose.setHaveManyToBay(object.inputInt(choose.getItemToBuyInStockQuantity(), startIntoTO, ifWrongTO));
		
		
		// give the user a price and ask if they whant to bay
		
		boolean whantProduct = choose.dosUserWhant(choose.getHaveManyToBay());

		
		// if user whant to bay "YES"
		if (whantProduct)
			choose.createOrder();

		System.out.println("Ok, test End");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_shop", "Chrih",
					"Juliasen2!FF");
		      

			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
			
			// stmt.executeUpdate("UPDATE items SET quantity =quantity+10 WHERE id=1");
			String kk = "select * from items";
			ResultSet rs = stmt.executeQuery(kk);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
