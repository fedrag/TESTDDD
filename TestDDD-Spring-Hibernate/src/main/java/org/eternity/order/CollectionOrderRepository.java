package org.eternity.order;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eternity.common.EntryPoint;
import org.eternity.common.Registrar;
import org.eternity.customer.Customer;

public class CollectionOrderRepository implements OrderRepository{

	private Registrar registrar;
	
	public CollectionOrderRepository(Registrar registrar) {
		this.registrar = registrar;
	}

	public void save(Order order) {
		registrar.add(CollectionOrder.class, (CollectionOrder)order);
	}
	 
	public Order find(String identity) {
	       return (Order)registrar.get(CollectionOrder.class, identity);
	}

	public Set<Order> findByCustomer(Customer customer) {
		Set<Order> results = new HashSet<Order>();
		            
		for(Order order : findAll()) {
		if (order.isOrderedBy(customer)) {
		           results.add(order);
		       }
		}
		            
		return results;
	}
	
	public Set<Order> findAll() {
		return new HashSet<Order>((Collection<Order>)registrar.getAll(CollectionOrder.class));
	}
	
	public Order delete(Order order) {
		return (Order)registrar.delete(CollectionOrder.class, ((EntryPoint)order).getIdentity());
	}
	

}
