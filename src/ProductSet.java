
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/** class that loads product set details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "product_set")	// product set as a root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
@XmlSeeAlso({Product.class})		// loads other classes of product class
public class ProductSet {

	@XmlElement(name = "product_line_name")		// loads product line name tag
	private String productLineName;

	@XmlElement(name = "product")	// loads product tag
	private ArrayList<Product> products = null;

	/** getters and setters for product set details **/
	
	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}
