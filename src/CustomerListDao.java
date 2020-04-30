
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** class that helps to get customer list details from the database **/
public class CustomerListDao {
	
	/** To set start date and end date **/
	private String startDate;
	private String endDate;
	
	/** constructor for initializing start date and end date **/
	public CustomerListDao(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/** method that helps to get details of all customers from the data base and returns the array list of customers **/
	public ArrayList<Customer> getAllCustomers() {
		/** get data base connection from the class created **/
		Connection connection = DbConnection.getConnection();
        try {
        	/** create statement for executing data base query **/
            Statement stmt = connection.createStatement();
            /** use the database csci3901 for getting customer details **/
            stmt.execute("use csci3901;"); 
            
            /** execute query to get the customer details from orders, order details, customers tables **/
            /** first get the details between the specified date and group by order number and join with customer table for customer details **/
            
            ResultSet rs = stmt.executeQuery("select *, count(*) as numberOfOrders, sum(totalValue) as totalOrderValue\r\n" + 
            		"	from (\r\n" + 
            		"		select *, sum(quantityOrdered * priceEach) as totalValue\r\n" + 
            		"			from (select orderNumber, customerNumber \r\n" + 
            		"					from orders \r\n" + 
            		"					where status !='Cancelled' and orderDate between '"+ this.startDate +"' and '"+ this.endDate +"'\r\n" + 
            		"			) as o join orderdetails using (orderNumber)\r\n" + 
            		"			group by orderNumber\r\n" + 
            		"    ) as oo join customers using (customerNumber)\r\n" + 
            		"    group by customerNumber;");
            
            /** create the array list of customers **/
            ArrayList<Customer> customerList = new ArrayList<Customer>();
                    
            /** get the details of customers by checking each row using next() **/
            while(rs.next()) {   
            	
            	/** create a customer object for storing customer details**/
            	Customer customer = new Customer();
            	
            	customer.setCustomerName(rs.getString("customerName"));
            	customer.setNumberOfOrders(rs.getInt("numberOfOrders"));
            	customer.setTotalValue(rs.getDouble("totalOrderValue"));
            	
            	CustomerAddress customerAddress = new CustomerAddress();
            	
            	customerAddress.setAddressLine1(rs.getString("addressLine1"));
            	customerAddress.setCity(rs.getString("city"));
            	customerAddress.setPostalCode(rs.getString("postalCode"));
            	customerAddress.setCountry(rs.getString("country"));
            	
            	customer.setCustomerAddress(customerAddress);
            	
            	/** add each customer to the customer list **/
            	customerList.add(customer);
            }
            return customerList; 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;		
	}
	
}
