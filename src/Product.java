
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** Class that holds the Product Details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "product")		// product as a root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
public class Product {
	
	@XmlElement(name = "product_name")		// loads product name tag
	private String productName;
	
	@XmlElement(name = "product_vendor")	// loads product vendor tag
	private String productVendor;
	
	@XmlElement(name = "units_sold")		// loads units sold tag
	private int unitsSold;
	
	@XmlElement(name = "total_sales")		// loads total sales tag
	private double totalSales;
	
	/** getters and setters for product details **/
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public int getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

}
