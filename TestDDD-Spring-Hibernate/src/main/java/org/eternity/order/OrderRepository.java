package org.eternity.order;

import java.util.Set;

import org.eternity.customer.Customer;

public interface OrderRepository {

	public void save(Order order) ;
	 
	public Order find(String identity);

	public Set<Order> findByCustomer(Customer customer);
	
	public Set<Order> findAll();
	
	public Order delete(Order order);

}
