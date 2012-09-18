package org.eternity.order;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.eternity.common.BeanFactory;
import org.eternity.common.Registrar;
import org.eternity.common.RepositoryBeanFactory;
import org.eternity.customer.Customer;
import org.eternity.customer.Money;
import org.eternity.product.Product;
import org.eternity.product.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class OrderTest extends AbstractTransactionalSpringContextTests{
	private Customer customer;
	private BeanFactory beanFactory;
	private OrderRepository orderRepository;
	private ProductRepository productRepository;


	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "/spring/config/beans-web.xml" };
	}
	public void onSetUpInTransaction() throws Exception {
		((Registrar)applicationContext.getBean("registrar")).init();
		
		
		
		RepositoryBeanFactory repositoryBeanFactory1 = (RepositoryBeanFactory)applicationContext.getBean("orderRepositoryBeanFactory");
		orderRepository = (OrderRepository) repositoryBeanFactory1.createRepository();
		
		RepositoryBeanFactory repositoryBeanFactory2 = (RepositoryBeanFactory)applicationContext.getBean("productRepositoryBeanFactory");
		productRepository = (ProductRepository) repositoryBeanFactory2.createRepository();
		
		beanFactory = (BeanFactory)applicationContext.getBean("productBeanFactory");

		Map params = new HashMap<Object,Object>();
		params.put("name", "상품1");
		params.put("price", new Money(1000));
		beanFactory.setParams(params);
		Product product1 = (Product) beanFactory.createBean();
		productRepository.save(product1); 
		
		params.put("name", "상품2");
		params.put("price", new Money(5000));
		beanFactory.setParams(params);
		Product product2 = (Product) beanFactory.createBean();
		productRepository.save(product2); 
		
		
		BeanFactory customerBeanFactory = (BeanFactory)applicationContext.getBean("customerBeanFactory");
		params.put("customerNumber", "CUST-01");
		params.put("name", "홍길동");
		params.put("address", "경기도 안양시");
		params.put("limitPrice", new Long(200000));
		customerBeanFactory.setParams(params);
		customer = (Customer)customerBeanFactory.createBean();
		
		
		
		
	}


//	@Test
//	public void testOrder() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testOrder1() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testWith() {
//		fail("Not yet implemented"); // TODO
//	}
	
	@Test
	public void testOrderLimitExceed() {
		try {
		       customer.newOrder("CUST-01-ORDER-01")
		       			.with("상품1", 20)
		       			.with("상품2", 50);
		       fail();
		} catch(OrderLimitExceededException ex) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testOrderPrice() throws Exception {
		  Order order = customer.newOrder("CUST-01-ORDER-01")
		                 .with("상품1", 10)
		                 .with("상품2", 20);
		  orderRepository.save(order);
		  assertEquals(new Money(110000), order.getPrice());
	}
	
	@Test
	public void testOrderWithEqualProductsPrice() throws Exception{
		Order order = customer.newOrder("CUST-01-ORDER-01")
		.with("상품1", 5)
		.with("상품2", 20)
		.with("상품1", 5);
		orderRepository.save(order);
		assertEquals(new Money(110000), order.getPrice());
	}
	
	@Test
	public void testOrdreLineItems() throws Exception {
		Order order = customer.newOrder("CUST-01-ORDER-01")
		.with("상품1", 5)
		.with("상품2", 20)
		.with("상품1", 5);
		orderRepository.save(order);
		assertEquals(2,order.getOrderLineItemSize());
	}
	
	@Test
	public void testOrderIdentical() throws Exception {
		Order order = customer.newOrder("CUST-01-ORDER-01")
		                      .with("상품1", 10)
		                      .with("상품2", 20);
		orderRepository.save(order);
		            
		Order anotherOrder = orderRepository.find("CUST-01-ORDER-01");
		assertEquals(order, anotherOrder);
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		orderRepository.save(customer.newOrder("CUST-01-ORDER-01")
		                        .with("상품1", 5)
		                        .with("상품2", 20));
		Order order = orderRepository.find("CUST-01-ORDER-01");
		 
		orderRepository.delete(order);
		 
		assertNull(orderRepository.find("CUST-01-ORDER-01"));
		assertNotNull(order);
	}


}
