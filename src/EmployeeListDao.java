
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** class that helps to get employee list details from the database **/
public class EmployeeListDao {

	/** To set start date and end date **/
	private String startDate;
	private String endDate;

	/** constructor for initializing start date and end date **/
	public EmployeeListDao(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/** method that helps to get details of all employees from the data base and returns the array list of employees **/
	public ArrayList<Employee> getAllEmployees() {
		/** get data base connection from the class created **/
		Connection connection = DbConnection.getConnection();
        try {
        	/** create statement for executing data base query **/
            Statement stmt = connection.createStatement();
            /** use the database csci3901 for getting employee details **/
            stmt.execute("use csci3901;"); 
            
            /** execute query to get the employee details from orders, orderdetails, customers, offices, employee tables **/
            /** first get the orders from the specified date get the customers and get the employee details of the respective customers **/
            
            ResultSet rs = stmt.executeQuery("select ceo.firstName, ceo.lastName, ceo.city, cood.numberOfActiveCustomers, cood.totalSales\r\n" + 
            		"	from (\r\n" + 
            		"		select ce.employeeNumber, ce.firstName, ce.lastName, o.city, ce.officeCode\r\n" + 
            		"			from offices o join (\r\n" + 
            		"				select e.employeeNumber, e.lastName, e.firstName, e.officeCode\r\n" + 
            		"					from customers c inner join employees e on c.salesRepEmployeeNumber = e.employeeNumber\r\n" + 
            		"					group by e.employeeNumber\r\n" + 
            		"			)ce using (officeCode)\r\n" + 
            		"    ) ceo inner join (\r\n" + 
            		"		select cood.salesRepEmployeeNumber, sum(cood.totalOrderValue) as totalSales, cood.numberOfActiveCustomers\r\n" + 
            		"		from (\r\n" + 
            		"			select *, count(*) as numberOfActiveCustomers, sum(totalValue) as totalOrderValue\r\n" + 
            		"				from (\r\n" + 
            		"					select *, sum(quantityOrdered * priceEach) as totalValue\r\n" + 
            		"						from (select orderNumber, customerNumber \r\n" + 
            		"								from orders \r\n" + 
            		"								where status !='Cancelled' and orderDate between '"+ this.startDate +"' and '"+ this.endDate +"'\r\n" + 
            		"						) as o join orderdetails using (orderNumber)\r\n" + 
            		"						group by orderNumber\r\n" + 
            		"				) as oo join customers using (customerNumber)\r\n" + 
            		"				group by customerNumber\r\n" + 
            		"			) cood group by cood.salesRepEmployeeNumber\r\n" + 
            		"    ) cood on ceo.employeeNumber = cood.salesRepEmployeeNumber;");
            
            /** create the array list of employees **/
            ArrayList<Employee> employeeList = new ArrayList<Employee>();

            /** get the details of employees by checking each row using next() **/
            while(rs.next()) {   

            	/** create a employee object for storing employee details**/
            	Employee employee = new Employee();
            	
            	employee.setFirstName(rs.getString("firstName"));
            	employee.setLastName(rs.getString("lastName"));
            	employee.setCity(rs.getString("city"));
            	employee.setNumberOfActiveCustomers(rs.getInt("numberOfActiveCustomers"));
            	employee.setTotalSales(rs.getDouble("totalSales"));

            	/** add each employee to the employee list **/
            	employeeList.add(employee);
            }
            return employeeList; 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;		
	}
	
}
