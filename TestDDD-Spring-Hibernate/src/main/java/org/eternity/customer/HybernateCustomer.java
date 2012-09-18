package org.eternity.customer;

import org.eternity.common.EntryPoint;
import org.eternity.common.Registrar;
import org.eternity.order.CollectionOrder;
import org.eternity.order.HybernateOrder;
import org.eternity.order.Order;

public class HybernateCustomer implements Customer{
	private Long id;
	private String customerNumber;
	private String name;
	private String address;
	private long mileage;
	private Money limitPrice;

	public HybernateCustomer(String customerNumber, String name, String address) {
		this.customerNumber = customerNumber;
		this.name = name;
		this.address = address;
		this.limitPrice = new Money(Long.MAX_VALUE);
	}
	
	public HybernateCustomer(String customerNumber, String name, String address, long limitPrice) {
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
	
	public Long getId() {
		return id;
	}
	

	public String getCustomerNumber() {
		return customerNumber;
	}

	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }

	    if (!(object instanceof Order)) {
	      return false;
	    }

	    final HybernateCustomer other = (HybernateCustomer)object;
	    return this.customerNumber.equals(other.getCustomerNumber());
	}

	public int hashCode() {
	    return this.customerNumber.hashCode();
	}
	
//	public static Customer find(String customerName) {
//		 return (Customer)Registrar.get(Customer.class, customerName);
//	}
//	
//	public Customer persist() {
//		  return (Customer)super.persist();
//	}

}
