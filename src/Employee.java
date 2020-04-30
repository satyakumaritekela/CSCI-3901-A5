
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** Class that holds the Employee Details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "employee")  	// employee as a root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
public class Employee {
	
	@XmlElement(name = "first_name")	// loads first name tag
	private String firstName;
	
	@XmlElement(name = "last_name")		// loads last name tag
	private String lastName;
	
	@XmlElement(name = "office_city")	// loads city tag
	private String city;
	
	@XmlElement(name = "active_customers")		// loads number of active customers tag
	private int numberOfActiveCustomers;
	
	@XmlElement(name = "total_sales")		// loads total sales tag
	private double totalSales;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNumberOfActiveCustomers() {
		return numberOfActiveCustomers;
	}
	public void setNumberOfActiveCustomers(int numberOfActiveCustomers) {
		this.numberOfActiveCustomers = numberOfActiveCustomers;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
}
