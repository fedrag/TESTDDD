package org.eternity.order;

import org.eternity.customer.Customer;
import org.eternity.customer.Money;

public interface Order{
	public Order with(String productName, int quantity) throws OrderLimitExceededException;
	public Money getPrice();
	public int getOrderLineItemSize();
	public boolean isOrderedBy(Customer customer);

}
