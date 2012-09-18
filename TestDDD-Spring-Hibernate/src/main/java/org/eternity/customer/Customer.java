package org.eternity.customer;

import org.eternity.order.Order;

public interface Customer {

	public Order newOrder(String orderId);
	
	public boolean isExceedLimitPrice(Money money);

	public void purchase(long price);

	public boolean isPossibleToPayWithMileage(long price) ;

	public boolean payWithMileage(long price);

	public long getMileage();
	
//	public static Customer find(String customerName) {
//		 return (Customer)Registrar.get(Customer.class, customerName);
//	}
//	
//	public Customer persist() {
//		  return (Customer)super.persist();
//	}

}
