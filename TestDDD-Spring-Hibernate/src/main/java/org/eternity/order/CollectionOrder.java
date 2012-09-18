package org.eternity.order;

import java.util.HashSet;
import java.util.Set;

import org.eternity.common.EntryPoint;
import org.eternity.customer.Customer;
import org.eternity.customer.Money;

public class CollectionOrder  extends EntryPoint implements Order{
	private Set<OrderLineItem> lineItems = new HashSet<OrderLineItem>();
	private Customer customer;
	
	public static CollectionOrder order(String orderId, Customer customer) {
	    return new CollectionOrder(orderId, customer);
	}
	CollectionOrder(String orderId, Customer customer) {
	    super(orderId);
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

}
