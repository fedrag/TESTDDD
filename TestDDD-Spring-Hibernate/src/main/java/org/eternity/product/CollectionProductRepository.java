package org.eternity.product;

import org.eternity.common.Registrar;

public class CollectionProductRepository implements ProductRepository{
	private Registrar registrar;

	public CollectionProductRepository(Registrar registrar) {
		this.registrar = registrar; 
	}
	public void save(Product product) {
		registrar.add(CollectionProduct.class, (CollectionProduct)product);
	}
	 
	public Product find(String identity) {
	    return (Product)registrar.get(CollectionProduct.class, identity);
	}

}
