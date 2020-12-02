import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Calendar;
import com.mysql.jdbc.PreparedStatement;

public class StorageObjects {

	// user name

	private String userName;

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// user password
	private String userPassword;

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String password) {
		userPassword = password;
	}

	// search for chosen item that the user want to buy.

	// ** user Choice Id number
	private int userChoiceId;

	public int getUserChoiceId() {
		return userChoiceId;
	}

	public void setUserChoiceId(int itemChoiceId) {
		userChoiceId = itemChoiceId;
	}

	// ** how many items of the user Choice is in stock
	private int itemToBuyInStockQuantity;

	public int getItemToBuyInStockQuantity() {
		return itemToBuyInStockQuantity;
	}

	public void setItemToBuyInStockQuantity(int Quantity) {
		itemToBuyInStockQuantity = Quantity;
	}

	// ** user Choice item price
	double itemToBuyPrice = 0.00d;

	public double getItemToBuyPrice() {
		return this.itemToBuyPrice;
	}

	public void setItemToBuyPrice(double itemPrice) {
		this.itemToBuyPrice = itemPrice;
	}

	// ** user Choice item name
	String itemToBuyName;

	public void setItemToBuyName(String itemName) {
		this.itemToBuyName = itemName;
	}

	public String getItemToBuyName() {
		return this.itemToBuyName;
	}

	// have many user vant to bay
	private int haveManyToBay;

	public int getHaveManyToBay() {
		return haveManyToBay;
	}

	public void setHaveManyToBay(int Quantity) {
		haveManyToBay = Quantity;
	}

}

class ClassObjects extends StorageObjects {

	// this method, will check and make sure that the user will give a int not
	// becker then maxInt
	public int inputInt(int maxInt, String statInto, String ifWrong) {
		int idToBuy = 0;
		boolean NoOff = true;

		while (NoOff) {
			Scanner input = new Scanner(System.in);
			System.out.print(statInto);
			if (input.hasNextInt()) {
				idToBuy = input.nextInt();

				// is the item on the list
				if (maxInt >= idToBuy) {
					NoOff = false;

				} else {
					System.out.println(ifWrong);
				}

			}

		}
		return idToBuy;
	}

	public boolean yOrN(char inputChar) {
		boolean resul;
		if (inputChar == 'Y' || inputChar == 'y')
			resul = true;
		else
			resul = false;

		return resul;

	}
}

//////////////////////////////////////////////////////////////

class InStoskClass {

// givse user overview of items in stock	

	public int getStockOverview() {
		return StockOverviewPrivate();
	}

	private int StockOverviewPrivate() {
		int maxItems = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_shop", "Chrih",
					"Juliasen2!FF");

			// **** here sonoo is database name, root is username and password

			Statement stmt = con.createStatement();

			// header text
			System.out.println("ID \tItem \t\t\t\t\tQuantity Price");

			// Ading line
			String ALine = "";
			for (int i = 0; i < 65; i++)
				ALine += "-";
			System.out.println(ALine);

			ResultSet rs = stmt.executeQuery("select * from items");
			while (rs.next()) {

				// adding distance between rolls
				String taps = "";
				for (int i = 40; i > rs.getString(2).length() + 1; i -= 8)
					taps += "\t";
				maxItems = rs.getInt(1);

				// All items

				System.out.println(
						rs.getInt(1) + "\t" + rs.getString(2) + taps + rs.getString(3) + "\t " + rs.getString(4));
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return maxItems;
	}
}

/////////////////////////////////////////////////////////////////

class Choose extends StorageObjects {

	public void setChoiceOfIteme(int choice) {
		choiceOfIteme(choice);
	}

	private void choiceOfIteme(int needItem) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cong = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_shop", "Chrih",
					"Juliasen2!FF");
			Statement stmtt = cong.createStatement();

			String strForMysql = "select * from items WHERE id=" + needItem;

			ResultSet rss = stmtt.executeQuery(strForMysql);
			rss.next();

			// Stor user choice
			String n = rss.getString(2);
			setItemToBuyName(n);

			Double priceIn = rss.getDouble(4);
			setItemToBuyPrice(priceIn);

			setItemToBuyInStockQuantity(rss.getInt(3));

			System.out.print("You would like to buy : " + rss.getString(2) + " for "
					+ String.format("%.2f", rss.getDouble(4)) + ".\nWe have " + getItemToBuyInStockQuantity()
					+ " in Stock. How many do you whant to bay? :");

			cong.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean dosUserWhant(int BuyQuantity) {
		Double priceOfQuantity = BuyQuantity * getItemToBuyPrice();

		System.out.println("\nIt will cost : " + String.format("%.2f", priceOfQuantity) + " EUR.");

		// user input
		char yesOrNo = 'r';
		boolean ferstTime = true;
		boolean ofNo = false;
		while (!ofNo) {

			if (ferstTime) {
				ferstTime = false;
			} else {
				System.out.print("\nSory, I dont understand. ");
			}
			System.out.print("\nWould you like to bay " + BuyQuantity + " " + itemToBuyName + " for a total some of "
					+ String.format("%.2f", priceOfQuantity) + " EUR N/Y? ");

			Scanner inputTwo = new Scanner(System.in);
			yesOrNo = inputTwo.next().charAt(0);
			ofNo = yesOrNo == 'y' || yesOrNo == 'Y' || yesOrNo == 'N' || yesOrNo == 'n';

		}

		ClassObjects object = new ClassObjects();
		return object.yOrN(yesOrNo);
	}

	public void createOrder() {
		createOrderPrivet();
	}

	private void createOrderPrivet() {

		/**
		 * A Java MySQL PreparedStatement INSERT example. Demonstrates the use of a SQL
		 * INSERT statement against a MySQL database, called from a Java program, using
		 * a Java PreparedStatement.
		 * 
		 * Created by Alvin Alexander, http://alvinalexander.com
		 */

		try {
			// create a mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/test_shop";
			;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "Chrih", "Juliasen2!FF");

			Calendar calendar = Calendar.getInstance();

			// the mysql insert statement
			String query = " insert into `order_costermer_log` (`Costermer`, `Item_id`,`item_name`, `quantity`, `price_pr_pc`, `total_price`)"
					+ " values (?, ?, ?, ?, ?,?)";

			System.out
					.println("end total " + String.format("%.2f", getHaveManyToBay() * getItemToBuyPrice()) + " EUR.");

			// the mysql insert preparedstatement
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setString(1, getUserName());
			preparedStmt.setInt(2, getUserChoiceId());
			preparedStmt.setString(3, getItemToBuyName());
			preparedStmt.setInt(4, getHaveManyToBay());
			preparedStmt.setDouble(5, getItemToBuyPrice());
			preparedStmt.setDouble(6, getHaveManyToBay() * getItemToBuyPrice());

			//String for the SQL
			String sqlUpdate = "UPDATE `items` " + "SET `quantity` = `quantity` - ? " + "WHERE id = ?;";

			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sqlUpdate);
			pstmt.setInt(1, getHaveManyToBay());
			pstmt.setInt(2, getUserChoiceId());

			// execute the preparedstatement and make the customer order
			preparedStmt.execute();
			pstmt.executeUpdate();

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}

}
