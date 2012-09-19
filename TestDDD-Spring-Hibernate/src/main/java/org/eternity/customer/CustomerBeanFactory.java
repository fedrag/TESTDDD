package org.eternity.customer;


import java.util.Map;

import org.eternity.common.BeanFactory;
import org.eternity.customer.Customer;
import org.eternity.customer.Money;

public class CustomerBeanFactory implements BeanFactory{
	private Map params;
	
	private int repoType;

	public void setParams(Map params) {
		this.params = params;
	}

	public void setRepoType(int repoType) {
		this.repoType = repoType;
	}

	@Override
	public Object createBean(){
		Customer customer = null;
		String customerNumber = (String)this.params.get("customerNumber");
    	String name = (String)this.params.get("name");        	
    	String address = (String)this.params.get("address");
    	Object limitPrice = this.params.get("limitPrice");
        if (this.repoType==REPO_TYPE_COLLECTION) {	
        	if(limitPrice!=null){
        		customer =  new CollectionCustomer(customerNumber, name, address, (Long)limitPrice);
        	}else{
        		customer =  new CollectionCustomer(customerNumber, name, address);
        	}
        } else{
        	if(limitPrice!=null){
        		customer =  new HybernateCustomer(customerNumber, name, address, (Long)limitPrice);
        	}else{
        		customer =  new HybernateCustomer(customerNumber, name, address);
        	}
        }
        return customer;
    }

}
