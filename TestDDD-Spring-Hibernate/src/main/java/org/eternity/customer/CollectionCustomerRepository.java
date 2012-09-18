package org.eternity.customer;

import org.eternity.common.Registrar;

public class CollectionCustomerRepository implements CustomerRepository{
	private Registrar registrar;

	public CollectionCustomerRepository(Registrar registrar) {
		this.registrar = registrar;
	}

	public void save(Customer customer) {
		registrar.add(CollectionCustomer.class, (CollectionCustomer)customer);
	}
	 
	public Customer find(String identity) {
	    return (Customer)registrar.get(CollectionCustomer.class, identity);
	}

}
