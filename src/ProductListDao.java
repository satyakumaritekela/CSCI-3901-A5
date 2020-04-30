
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductListDao {

	/** To set start date and end date **/	
	private String startDate;
	private String endDate;

	/** constructor for initializing start date and end date **/
	public ProductListDao(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/** method that helps to get details of all products from the data base and returns the array list of product sets **/
	public ArrayList<ProductSet> getAllProductSets() {
		/** get data base connection from the class created **/
		Connection connection = DbConnection.getConnection();
        try {
        	/** create statement for executing data base query **/
            Statement stmt = connection.createStatement();
            /** use the database csci3901 for getting employee details **/
            stmt.execute("use csci3901;"); 

            /** execute query to get the product details from orders, orderdetails, products tables **/
            /** first get the orders from the specified date get the products and get the product details **/
            
            ResultSet rs = stmt.executeQuery("select *\r\n" + 
            		"	from (\r\n" + 
            		"		select *, sum(quantityOrdered) as unitsSold, sum(quantityOrdered * priceEach) as totalValue\r\n" + 
            		"			from (select orderNumber \r\n" + 
            		"					from orders \r\n" + 
            		"					where status !='Cancelled' and orderDate between '"+ this.startDate +"' and '"+ this.endDate +"'\r\n" + 
            		"			) as o join orderdetails using (orderNumber)\r\n" + 
            		"			group by productCode\r\n" + 
            		"    ) as oo join products using (productCode)\r\n" + 
            		"    order by productLine;");

            /** create the array list of productset lists **/
            ArrayList<ProductSet> productSetList = new ArrayList<ProductSet>();
            
            /** create the array list of products **/
            ArrayList<Product> productList = null;
            
            ProductSet productSet = new ProductSet();
            
            String productLine = "";

            /** get the details of products by checking each row using next() **/
            while(rs.next()) {            	

            	/** create a product set object for storing product details **/
            	if(productLine == "" || !productLine.equalsIgnoreCase(rs.getString("productLine"))) {     
                    if(productLine != "" && !productLine.equalsIgnoreCase(rs.getString("productLine"))) {
                    	productSet.setProducts(productList);
                    	productSetList.add(productSet);
                    }           	                	
                    productSet = new ProductSet();
                    productSet.setProductLineName(rs.getString("productLine")); 
                    productList = new ArrayList<Product>();
            	}            	

            	/** create a product for storing product details **/
            	Product product = new Product();
            	
            	product.setProductName(rs.getString("productName"));
            	product.setProductVendor(rs.getString("productVendor"));
            	product.setUnitsSold(rs.getInt("unitsSold"));
            	product.setTotalSales(rs.getDouble("totalValue"));

            	/** add each product to the product list **/
            	productList.add(product);
            	
            	productLine = rs.getString("productLine");
            }
                productSet.setProducts(productList);
                productSetList.add(productSet);
                
            return productSetList; 
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;		
	}
	
}
