
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/** Class that holds the Product List Details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "product_list")	// product list as root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
@XmlSeeAlso({ProductSet.class})		// loads other class of product set tags
public class ProductList {
	
	@XmlElement(name = "product_set")		// loads product set tags
	private ArrayList<ProductSet> productSets;

	/** getters and setters for product sets **/
	
	public ArrayList<ProductSet> getProductSets() {
		return productSets;
	}

	public void setProductSets(ArrayList<ProductSet> productSets) {
		this.productSets = productSets;
	}
	
}
