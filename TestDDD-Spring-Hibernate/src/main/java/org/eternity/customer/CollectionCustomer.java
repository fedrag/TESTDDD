package org.eternity.customer;

import org.eternity.common.EntryPoint;
import org.eternity.common.Registrar;
import org.eternity.order.CollectionOrder;
import org.eternity.order.Order;

public class CollectionCustomer extends EntryPoint implements Customer{
	private String customerNumber;
	private String name;
	private String address;
	private long mileage;
	private Money limitPrice;

	public CollectionCustomer(String customerNumber, String name, String address) {
		super(customerNumber);
		this.customerNumber = customerNumber;
		this.name = name;
		this.address = address;
		this.limitPrice = new Money(Long.MAX_VALUE);
	}
	
	public CollectionCustomer(String customerNumber, String name, String address, long limitPrice) {
		super(customerNumber);
		this.customerNumber = customerNumber;
		this.name = name;
		this.address = address;
		this.limitPrice = new Money(limitPrice);
	}
	public Order newOrder(String orderId){
		return CollectionOrder.order(orderId, this);		
	}
	
	public boolean isExceedLimitPrice(Money money){
		return money.isGreaterThan(limitPrice);
	}


	public void purchase(long price) {
		mileage += price * 0.01;
	}

	public boolean isPossibleToPayWithMileage(long price) {
		return mileage > price;
	}

	public boolean payWithMileage(long price) {
		if (!isPossibleToPayWithMileage(price)) {
			return false;
		}

		mileage -= price;
		return true;
	}

	public long getMileage() {
		return mileage;
	}
	
//	public static Customer find(String customerName) {
//		 return (Customer)Registrar.get(Customer.class, customerName);
//	}
//	
//	public Customer persist() {
//		  return (Customer)super.persist();
//	}

}
