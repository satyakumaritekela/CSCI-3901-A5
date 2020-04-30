/**** Problem 1 ****/

use csci3901;

/** Question 1 **/
/** query that generates the customers who are in the different city other than sales representative's city **/
/** join customers and employee based on employee number and further join the resulted table to office to get city details **/

select ce.*, o.city
	from ( 
		select customerNumber, customerName, city, salesRepEmployeeNumber, concat(LastName, ' ', firstName) as EmployeeName, officeCode
		from customers c join employees e 
		on c.salesRepEmployeeNumber = e.employeeNumber    
    ) ce join offices as o using (officeCode)
    where ce.city != o.city;

/** Question 2 **/
/** query that generates the orders included sales that are below the manufacturer’s suggested retail price (MSRP) **/
/** join orders and orderdetails based on ordernuumber and further join the resulted table to products based on product code **/
  
select orderNumber
	from products join orderdetails using (productCode)
    where priceEach < MSRP
    group by orderNumber;

/** Question 3 **/
/** query that generates the top 5 products for 2004 with the highest average mark-up percentage per order **/
/** join orders and orderdetails based on ordernuumber and further join the resulted table to products based on product code **/
  
select distinct p.productCode, p.productName, avg((oo.priceEach - p.buyPrice) / p.buyPrice) * 100 as avgMarkUp
	from products p join (
		select *
			from orders join orderdetails using (orderNumber)
			where extract(year from orderDate) = 2004    
    ) oo using (productCode)
    group by productCode
    order by avgMarkUp desc limit 5;
 
/** Question 4 **/
/** query that generates top 3 employees with the greatest average diversity of products in their orders **/
/** join orders and orderdetails based on ordernuumber and further join the resulted table to customers based on customer number **/

select e.employeeNumber, concat(e.firstName, ' ', e.lastName) as employeeName, cood.avgDiversityOfProducts
	from employees e right join (
		select salesRepEmployeeNumber, avg(numberOfProducts) as avgDiversityOfProducts
			from (
				select c.salesRepEmployeeNumber, o.orderNumber
				from customers c join orders o using (customerNumber)
			) co join (
				select od.orderNumber, count(*) as numberOfProducts
				from orders o join orderdetails od using (orderNumber)
				group by od.orderNumber
			) ood using (orderNumber)
			group by co.salesRepEmployeeNumber 
			order by avgDiversityOfProducts desc limit 3
    ) cood on e.employeeNumber = cood.salesRepEmployeeNumber;              
                
/** Question 5 **/
/** query that generates the average time needed to ship orders from each office in 2005, relative to the order date **/
/** join orders that are done in 2005 and employee offices based on customer number **/
    
select officeCode, city, ecco.averageTimeInDays
	from (
		select ec.officeCode, avg(oo.numberOfDays) as averageTimeInDays
			from (
				select e.officeCode, c.*
					from employees e join customers c on e.employeeNumber = c.salesRepEmployeeNumber
			) ec join (
				select *, DATEDIFF(o.shippedDate, o.orderDate) as numberOfDays
					from orders o
					where extract(year from o.orderDate) = 2005
			) oo using (customerNumber)
			group by ec.officeCode
    ) ecco join offices o using (officeCode);