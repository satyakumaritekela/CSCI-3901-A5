
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

/** main function that helps to process the input and prints the output to the specified file format **/
public class XMLSummary {
	
	public static void main(String[] args) throws FileNotFoundException, JAXBException, ParseException {
		
		/** creates the scanner object that takes System.in  **/
		String startDate = null;
		String endDate = null;
		String outputFile = null;

		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		
		File fileName = null;	
		
			try {
				
				/** takes the values start date, end date, output file **/
				System.out.println("Please enter start date");		
				startDate = sc.next();
				
				System.out.println("Please enter end date");		
				endDate = sc.next();
						
				System.out.println("Please enter output file name");
				
				outputFile = sc.next();
				
				/** check the date formatter **/
				
				DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(startDate);
				DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(endDate);
				
				/** create a new file as given from the input **/
				fileName = new File(outputFile);
				
				/** check if exists or not **/
				if(!fileName.exists()) {
					throw new FileNotFoundException("File :" + fileName.getName()+ " not found");
				}

				/** create a object of xml convert and pass the respective values **/
				XMLConvert xc = new XMLConvert();
				
				xc.XMLConverter(startDate, endDate, outputFile);
				flag = true;
				
			}
			/** Date Parse Exception to be handled **/
			catch(DateTimeParseException de) {
				System.out.println("Please enter correct format of date");
				flag = false;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				flag = false;
			}
			/** close the connection and print the desired output **/
			finally {
				sc.close();
				System.out.println("Output returned :"+ flag);
			}
		
	}
}
