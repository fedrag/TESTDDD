package org.eternity.product;

import org.eternity.customer.HybernateCustomer;
import org.eternity.customer.Money;
import org.eternity.order.Order;

public class HybernateProduct implements Product{
	private Long id;
	private String name;
	private Money price;

	public HybernateProduct(String name, long price) {
		this.price = new Money(price);
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public HybernateProduct(String name, Money price) {
		this.price = price;
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
	
	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }

	    if (!(object instanceof Order)) {
	      return false;
	    }

	    final HybernateProduct other = (HybernateProduct)object;
	    return this.name.equals(other.getName());
	}

	public int hashCode() {
	    return this.name.hashCode();
	}
	

}
