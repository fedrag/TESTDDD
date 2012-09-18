package org.eternity.product;

import java.util.List;

import org.eternity.common.Registrar;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HybernateProductRepository extends HibernateDaoSupport implements ProductRepository{
	private SessionFactory sessionFactory;

	public HybernateProductRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; 
	}
	
	@SuppressWarnings("unchecked")
	public Product find(String name) {
		List<Product> result = (List<Product>)getHibernateTemplate().find("from Product where name=?", name);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}   
		return null;
	}

	public void save(Product product) {
		getHibernateTemplate().save(product);       
	}

}
