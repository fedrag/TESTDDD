package org.eternity.order;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;

import org.apache.naming.factory.BeanFactory;
import org.eternity.common.Registrar;
import org.eternity.common.RepositoryBeanFactory;
import org.eternity.product.CollectionProductRepository;
import org.hibernate.SessionFactory;

public class OrderRepositoryBeanFactory implements RepositoryBeanFactory{
	
	private int repoType;
	private Registrar registrar;
	private SessionFactory sessionFactory;

	public void setRepoType(int repoType) {
		this.repoType = repoType;
	}

	public void setRegistrar(Registrar registrar) {
		this.registrar = registrar;            
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Object createRepository(){
		OrderRepository orderRepository= null;
        if (this.repoType==REPO_TYPE_COLLECTION) {
        	orderRepository =  new CollectionOrderRepository(this.registrar);
        }else{
        	orderRepository =  new HybernateOrderRepository(this.sessionFactory);
        }
        return orderRepository;
    }

}
