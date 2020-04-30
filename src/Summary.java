
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/** class that loads the summary details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "year_end_summary")		// year end summary as a root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
public class Summary {
	
	@XmlElement(name = "year")		// loads year tag
	private Year year;
	
	/** getters and setters for year tag **/
	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	@XmlElementWrapper(name = "customer_list")		// customer list as a wrapper tag for all customers
	@XmlElement(name = "customer")			// loads customer tag for all the customer lists
	private ArrayList<Customer> customerList;

	/** getters and setters for customer list details **/
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	
	@XmlElementWrapper(name = "product_list")		// product list as a wrapper tag for all product sets
	@XmlElement(name = "product_set")		// loads product set tag for all product sets
	private ArrayList<ProductSet> productSets;

	/** getters and setters for product set details **/
	public ArrayList<ProductSet> getProductSets() {
		return productSets;
	}

	public void setProductSets(ArrayList<ProductSet> productSets) {
		this.productSets = productSets;
	}
	
	@XmlElementWrapper(name = "staff_list")		// staff list as a wrapper tag for all employees
	@XmlElement(name = "employee")		// loads employee tag for all employee list
	private ArrayList<Employee> employeeList;

	/** getters and setters for employee lists **/
	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
