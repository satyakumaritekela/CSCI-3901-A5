
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** Class that holds the Customer Address Details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "address")		// address as a root element
@XmlAccessorType(XmlAccessType.FIELD)   // used by reflection by jaxb at runtime
public class CustomerAddress {

	@XmlElement(name = "street_address")   // loads street address tag
	private String addressLine1;
	
	@XmlElement(name = "city")		// loads city tag
	private String city;
	
	@XmlElement(name = "postal_code")		// loads postal code tag
	private String postalCode;
	
	@XmlElement(name = "country")		// loads country tag
	private String country;
	
	/** getters and setters for customer address details **/
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
