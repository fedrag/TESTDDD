package org.eternity.order;

import org.eternity.customer.Money;
import org.eternity.product.Product;
import org.eternity.product.ProductRepository;
import org.eternity.product.ProductRepositoryBeanFactory;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(value="orderLineItem",preConstruction=true)
public class OrderLineItem {
	private Product product;
	private int quantity;
	
	private ProductRepository productRepository;
	private ProductRepositoryBeanFactory productRepositoryBeanFactory;
	
	public void setProductRepositoryBeanFactory(ProductRepositoryBeanFactory productRepositoryBeanFactory) {
		this.productRepositoryBeanFactory = productRepositoryBeanFactory;
		this.productRepository = (ProductRepository)this.productRepositoryBeanFactory.createRepository();
	}

	public OrderLineItem(String productName, int quantity) {
		this.product = productRepository.find(productName);
		this.quantity = quantity;
	}

	public Money getPrice() {
		return product.getPrice().multiply(quantity);
	}

	public Product getProduct() {
		return product;
	}

	public boolean isProductEqual(OrderLineItem lineItem) {
		return (product.getName().equals(lineItem.getProduct().getName()));
	}

	public OrderLineItem merge(OrderLineItem lineItem) {
		// TODO Auto-generated method stub
		this.quantity += lineItem.quantity;
		return this;
	}
	
	int getQuantity() {
	    return quantity;
	  }
	 
	public boolean equals(Object object) {
	    if (object == this) {
	      return true;
	    }
	 
	    if (!(object instanceof OrderLineItem)) {
	      return false;
	    }
	 
	    final OrderLineItem other = (OrderLineItem)object;
	    return this.product.equals(other.getProduct())
	      && this.quantity == other.getQuantity();
	}
	 
	public int hashCode() {
	    int result = 17;
	    result = 37*result + product.hashCode();
	    result = 37*result + quantity;            
	    return result;
	}


}
