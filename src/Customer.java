
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/** Class that holds the Customer Details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "customer")		// customer as a root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
@XmlSeeAlso({CustomerAddress.class})	// loads other classes for address tags
public class Customer {

	@XmlElement(name = "customer_name")		// loads customer name tag
	private String customerName;
	
	@XmlElement(name = "address")			// loads customer address tag
	private CustomerAddress customerAddress;
	
	@XmlElement(name = "num_orders")		// loads number of orders tag
	private int numberOfOrders;
	
	@XmlElement(name = "order_value")		// loads total value tag
	private double totalValue;
	
	/** getters and setters for address details **/
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getNumberOfOrders() {
		return numberOfOrders;
	}
	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}
	
}
