package org.eternity.customer;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.eternity.common.BeanFactory;
import org.eternity.common.DBManager;
import org.eternity.common.Registrar;
import org.eternity.common.RepositoryBeanFactory;
import org.eternity.order.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class CustomerTest extends AbstractTransactionalSpringContextTests {
	private Customer customer; 
	private CustomerRepository customerRepository;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "/spring/config/beans-web.xml" };
	}
	
	
	public void onSetUpBeforeTransaction() throws Exception {
		//start db
		DBManager.startHsqlDb();
	}
	
	public void onSetUpInTransaction() throws Exception {
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
//		Customer customer =  new Customer("CUST-01", "홍길동", "경기도 안양시").persist();
//		Customer anotherCustomer = Customer.find("CUST-01");
//		assertSame(customer, anotherCustomer);           
//	}
	
	@Test
	public void testCustomerIdentical() {
		customerRepository.save(customer);
		Customer anotherCustomer = customerRepository.find("CUST-01");
		assertSame(customer, anotherCustomer);           
	}

}
