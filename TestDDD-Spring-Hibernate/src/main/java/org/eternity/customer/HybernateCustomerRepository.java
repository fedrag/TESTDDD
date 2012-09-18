package org.eternity.customer;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HybernateCustomerRepository extends HibernateDaoSupport implements CustomerRepository{

	public HybernateCustomerRepository(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public Customer find(String customerNumber) {
		List<Customer> result = (List<Customer>)getHibernateTemplate().find("from Customer where customer_number=?", customerNumber);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}   
		return null;
	}

	public void save(Customer customer) {
		getHibernateTemplate().save(customer);       
	}


}
