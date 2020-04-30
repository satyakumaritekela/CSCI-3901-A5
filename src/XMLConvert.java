
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/** class that helps to convert the data to xml format **/
public class XMLConvert {
	
	/** method that converts the data to xml using jaxb marshalling 
	 * @throws ParseException **/
	public void XMLConverter(String startDate, String endDate, String outputFile) throws JAXBException, ParseException {
		
			/** create the dao objects and get the details of all the summary lists **/
			ProductListDao pd = new ProductListDao(startDate, endDate);
			ArrayList<ProductSet> listOfProductSets = pd.getAllProductSets();
			
			CustomerListDao cd = new CustomerListDao(startDate, endDate);
			ArrayList<Customer> listOfCustomers = cd.getAllCustomers();
			
			EmployeeListDao ed = new EmployeeListDao(startDate, endDate);
			ArrayList<Employee> listOfEmployees = ed.getAllEmployees();
			
			Year y = new Year();
			y.setStartDate(startDate);
			y.setEndDate(endDate);
			
			/** create the summary object and set all the list of the orders and employees **/
			Summary year_end_summary = new Summary();
			year_end_summary.setEmployeeList(listOfEmployees);
			year_end_summary.setCustomerList(listOfCustomers);
			year_end_summary.setProductSets(listOfProductSets);
			year_end_summary.setYear(y);
			
			/** create a jaxb context for marshalling the object **/
		    
		    JAXBContext contextObj = JAXBContext.newInstance(Summary.class); 
		    
		    /** create a marshaller object from the context obj **/
		  
		    Marshaller marshallerObj = contextObj.createMarshaller();	    
		    
		    /** set the marshaller jaxb formatted output as true **/

		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);	   
		    
		    /** send the summary object as the marshal object for converting to xml **/
			marshallerObj.marshal(year_end_summary, new File(outputFile));
	}
}
