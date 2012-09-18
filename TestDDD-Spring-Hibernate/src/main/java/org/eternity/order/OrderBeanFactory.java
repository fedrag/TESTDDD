package org.eternity.order;


import java.util.Map;

import org.eternity.common.BeanFactory;
import org.eternity.customer.Customer;
import org.eternity.customer.Money;

public class OrderBeanFactory implements BeanFactory{
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
		String orderId = (String)this.params.get("orderId");
    	Customer customer = (Customer)this.params.get("customer");
        if (this.repoType==REPO_TYPE_COLLECTION) {
            return new CollectionOrder(orderId, customer);
        } else{
            return null;
        }
    }

}
