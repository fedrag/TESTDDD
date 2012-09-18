package org.eternity.product;

import org.eternity.common.EntryPoint;
import org.eternity.customer.Money;

public class CollectionProduct extends EntryPoint implements Product{
	private String name;
	private Money price;


	public CollectionProduct(String name, long price) {
		super(name);
		this.price = new Money(price);
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	public CollectionProduct(String name, Money price) {
		super(name);
		this.price = price;
		this.name = name;
	}

	public Money getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

}
