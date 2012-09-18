package org.eternity.product;


import java.util.Map;

import org.eternity.common.BeanFactory;
import org.eternity.customer.Money;

public class ProductBeanFactory implements BeanFactory{
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
		String name = (String)this.params.get("name");
    	Money price = (Money)this.params.get("price");
        if (this.repoType==REPO_TYPE_COLLECTION) {
            return new CollectionProduct(name, price);
        } else{
            return null;
        }
    }

}
