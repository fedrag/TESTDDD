package org.eternity.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.persist.HsqlProperties;

public class DBManager {
	
	private static DBManager dbManager;
	private org.hsqldb.Server server = null;
	
	
	public static DBManager getInstance() {

	        if (dbManager == null) {
	        	dbManager = new DBManager();
	        }
	        
	        return dbManager;
	}
	 
	public DBManager(){
		 
	}
	
	public void startHsqlDb(){
    	 try {
     		HsqlProperties props = new HsqlProperties();
     		if(server==null){
               props.setProperty("server.database.0", "file:C:\\Users\\Administrator\\git\\TestDDD\\TestDDD-Spring-Hibernate\\mydb\\hsqldb04;");
               props.setProperty("server.trace", "true");
               props.setProperty("server.silent", "false");
               props.setProperty("server.dbname.0", "hsqldb04");
               server = new org.hsqldb.Server();
               server.setProperties(props);
     		}
     		
     		//org.hsqldb.Server.main(new String[]{"-database.0", "file:mydb/hsqldb01","-dbname.0","hsqldb01"});
         } catch (Exception e) {
               return;
         }
    	 System.out.println(server.getState());
    	 if(server.getState()==16){
    		 server.start();
    	 }
         try {
             Thread.sleep(1000);
         } catch (InterruptedException ie) {
        	 
         }
	}
	
	public void stopHsqlDb(){
		server.shutdown();
	}
	
	
	public void createTables(Connection connection) throws SQLException {
	    try {
	    	 executeSql(connection,getDDL4Customers());
	    	 executeSql(connection,getDDL4Products());
	    	 executeSql(connection,getDDL4Orders());
	    	 executeSql(connection,getDDL4OrderLineItems());
	    } catch (SQLException se) {
	        se.printStackTrace();
	    }
	}
	
	public void dropTables(Connection connection) throws SQLException {
	    try {
	    	 executeSql(connection,"DROP TABLE Order_Line_Items;");
	    	 executeSql(connection,"DROP TABLE Orders;");
	    	 executeSql(connection,"DROP TABLE Products;");
	    	 executeSql(connection,"DROP TABLE Customers;");
	    } catch (SQLException se) {
	        se.printStackTrace();
	    }
	}

	
	private String getDDL4Customers(){
		return "create table customers"
                 + "( "
                 + "id INTEGER GENERATED BY DEFAULT AS IDENTITY , "                 
                 + "customer_number varchar(2000), "
                 + "name varchar(2000), "
                 + "address varchar(2000), "
                 + "mileage BIGINT, "
                 + "limit_price DECIMAL, "
                 + "constraint pk_customers_id primary key (id) "
                 + ")";
		
	}
	
	private String getDDL4Products(){
		return "create table products"
                 + "( "
                 + "id INTEGER GENERATED BY DEFAULT AS IDENTITY, "
                 + "name varchar(2000), "
                 + "price DECIMAL(10) , "
                 + "constraint pk_products_id primary key (id) "
                 + ")";
		
	}
	
	private String getDDL4Orders(){
		return "create table orders"
                 + "( "
                 + "id INTEGER GENERATED BY DEFAULT AS IDENTITY , "                 
                 + "order_id varchar(2000), "
                 + "customer_id numeric(38) , "
                 + "constraint pk_orders_id primary key (id) , "
                 + "constraint fk_orders_customer_id FOREIGN KEY (id) references customers(id) on delete cascade"
                 + ")";
		
	}
	
	private String getDDL4OrderLineItems(){
		return "create table order_line_items"
                + "( "
                + "id INTEGER GENERATED BY DEFAULT AS IDENTITY , "
                + "quantity numeric(38) , "
                + "order_id numeric(38) , "
                + "product_id numeric(38) , "
                + "constraint pk_order_line_items_id primary key (id) , "
                + "constraint fk_order_line_items_order_id FOREIGN KEY (id) references orders(id) on delete cascade , "
                + "constraint fk_order_line_items_product_id FOREIGN KEY (id) references products(id) on delete cascade"
                + ")";
	}
	 
	 
	public void executeSql(Connection connection, String sql) throws SQLException {
	    Statement statement = connection.createStatement();
	    statement.execute(sql);
	}

}
