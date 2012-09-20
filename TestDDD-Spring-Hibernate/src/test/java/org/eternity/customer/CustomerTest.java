package org.eternity.customer;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.TableGenerator;
import javax.sql.DataSource;

import org.eternity.common.BeanFactory;
import org.eternity.common.DBManager;
import org.eternity.common.Registrar;
import org.eternity.common.RepositoryBeanFactory;
import org.eternity.order.OrderRepository;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class CustomerTest extends AbstractTransactionalSpringContextTests {
	private SessionFactory sessionFactory;
	private Connection connection;
	private Customer customer; 
	private CustomerRepository customerRepository;
	
	private DataSource dataSource; 
	
	@Required
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	@Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "/spring/config/beans-web.xml" };
	}
	
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		//start db
		DBManager.getInstance().startHsqlDb();
	}
	@Override
	protected void onTearDownAfterTransaction(){
		//shutdown db
		DBManager.getInstance().stopHsqlDb();
	}
	
	@Override
	protected void onSetUpInTransaction() throws Exception {
		connection = dataSource.getConnection();
//		connection.setAutoCommit(false);
		DBManager.getInstance().createTables(connection);

		((Registrar)applicationContext.getBean("registrar")).init();
		
	       RepositoryBeanFactory repositoryBeanFactory1 = (RepositoryBeanFactory)applicationContext.getBean("customerRepositoryBeanFactory");
	       customerRepository = (CustomerRepository) repositoryBeanFactory1.createRepository();

	       BeanFactory customerBeanFactory = (BeanFactory)applicationContext.getBean("customerBeanFactory");
	       Map params = new HashMap<Object,Object>();
	       params.put("customerNumber", "CUST-01");
	       params.put("name", "홍길동");
	       params.put("address", "경기도 안양시");
	       customerBeanFactory.setParams(params);
	       customer = (Customer)customerBeanFactory.createBean();
	      
	}
	
	protected void onTearDownInTransaction(){
		try {
			DBManager.getInstance().dropTables(connection);
//			connection.rollback();
//			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

//	@Test
//	public void testCustomer() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testPurchase() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testIsPossibleToPayWithMileage() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testPayWithMileage() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testGetMileage() {
//		fail("Not yet implemented"); // TODO
//	}
	
	@Test
	public void testAliasing(){
		Customer anotherCustomer = customer;
		long price = 1000;
		customer.purchase(price);		
		assertEquals(price*0.01, anotherCustomer.getMileage(), 0.1);
//		assertEquals(0, anotherCustomer.getMileage());
	}
	
//	@Test
//	public void testCustomerIdentical() {
	@Test
	public void testCustomerIdentical() {
		customerRepository.save(customer);
		Customer anotherCustomer = customerRepository.find("CUST-01");
		assertSame(customer, anotherCustomer);           
	}

}
