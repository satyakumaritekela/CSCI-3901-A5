
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** class that loads the year details **/
/** XML Annotations for xml tags generation **/

@XmlRootElement(name = "year")		// laoads year as a root element
@XmlAccessorType(XmlAccessType.FIELD)	// used by reflection by jaxb at runtime
public class Year {
	
	@XmlElement(name = "start_date")	// loads start date tag
	private String startDate;

	@XmlElement(name = "end_date")		// loads end date tag
	private String EndDate;
	
	/** getters and setters for year details **/
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	
}
