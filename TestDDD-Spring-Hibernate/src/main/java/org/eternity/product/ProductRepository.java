package org.eternity.product;

public interface ProductRepository {
	
	public void save(Product product);
	 
	public Product find(String identity);

}
