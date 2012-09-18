package org.eternity.customer;


import org.eternity.common.Registrar;
import org.eternity.common.RepositoryBeanFactory;
import org.hibernate.SessionFactory;

public class CustomerRepositoryBeanFactory implements RepositoryBeanFactory{
	
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
		CustomerRepository customerRepository= null;
        if (this.repoType==REPO_TYPE_COLLECTION) {
        	customerRepository =  new CollectionCustomerRepository(this.registrar);
        }else if (this.repoType==REPO_TYPE_COLLECTION) {
        	customerRepository =  new HybernateCustomerRepository(this.sessionFactory);
        }
        return customerRepository;
    }

}
