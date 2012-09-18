package org.eternity.order;

import java.util.HashSet;
import java.util.Set;

import org.eternity.common.EntryPoint;
import org.eternity.customer.Customer;
import org.eternity.customer.Money;

public class HybernateOrder implements Order{
	private Long id;
	private String orderId;
	private Set<OrderLineItem> lineItems = new HashSet<OrderLineItem>();
	private Customer customer;
	
	public static HybernateOrder order(String orderId, Customer customer) {
	    return new HybernateOrder(orderId, customer);
	}
	public HybernateOrder(String orderId, Customer customer) {
		this.orderId = orderId;
	    this.customer = customer;
	}
	
	public Order with(String productName, int quantity) throws OrderLimitExceededException {
		  return with(new OrderLineItem(productName, quantity));
	}

	private Order with(OrderLineItem lineItem)  throws OrderLimitExceededException {
	  if (isExceedLimit(customer, lineItem)) {
	    throw new OrderLimitExceededException(OrderLimitExceededException.Order_Limit_Exceeded);
	  }
	  
	  for(OrderLineItem item : lineItems) {
		  if (item.isProductEqual(lineItem)) {
			  item.merge(lineItem);
			  return this;
		  }
	  }

	  lineItems.add(lineItem);            
	  return this;
	}
	 
	private boolean isExceedLimit(Customer customer, OrderLineItem lineItem) {
	  return customer.isExceedLimitPrice(getPrice().add(lineItem.getPrice()));
	}
	
	public Money getPrice() {
		Money result = new Money(0);
		 
		for(OrderLineItem item : lineItems) {
			result = result.add(item.getPrice());
		}
		 
		return result;
	}

	public int getOrderLineItemSize() {		
		return lineItems.size();
	}
	
	public boolean isOrderedBy(Customer customer) {
		return this.customer == customer;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getOrderId() {
	    return orderId;
	}

	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }

	    if (!(object instanceof Order)) {
	      return false;
	    }

	    final HybernateOrder other = (HybernateOrder)object;
	    return this.orderId.equals(other.getOrderId());
	}

	public int hashCode() {
	    return this.orderId.hashCode();
	}

}
