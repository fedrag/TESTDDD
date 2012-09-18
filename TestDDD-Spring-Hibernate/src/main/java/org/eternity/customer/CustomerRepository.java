package org.eternity.customer;

public interface CustomerRepository {
	
	public void save(Customer customer);
	 
	public Customer find(String identity);

}
