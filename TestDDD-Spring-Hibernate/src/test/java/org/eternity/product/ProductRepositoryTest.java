package org.eternity.product;

import java.util.HashMap;
import java.util.Map;

import org.eternity.common.BeanFactory;
import org.eternity.common.Registrar;
import org.eternity.common.RepositoryBeanFactory;
import org.eternity.customer.Money;
import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.AbstractTransactionalSpringContextTests;

@SuppressWarnings("deprecation")
public class ProductRepositoryTest extends AbstractTransactionalSpringContextTests {
	
	

	
	private ProductRepository productRepository;
	private BeanFactory beanFactory;

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "/spring/config/beans-web.xml" };
	}
	 
	public void onSetUpInTransaction() throws Exception {
		((Registrar)applicationContext.getBean("registrar")).init();
		RepositoryBeanFactory repositoryBeanFactory = (RepositoryBeanFactory)applicationContext.getBean("productRepositoryBeanFactory");
		productRepository = (ProductRepository) repositoryBeanFactory.createRepository();
		
		beanFactory = (BeanFactory)applicationContext.getBean("productBeanFactory");

	    //productRepository = (ProductRepository)applicationContext.getBean("productRepository");
	}

//	@Test
//	public void testSave() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public void testFind() {
//		fail("Not yet implemented"); // TODO
//	}
	
	@Test
	public void testProductSave() throws Exception {
		Map params = new HashMap<Object,Object>();
		params.put("name", "상품1");
		params.put("price", new Money(1000));
		beanFactory.setParams(params);
		Product saveProduct = (Product) beanFactory.createBean();
		productRepository.save(saveProduct);            
		assertSame(saveProduct, productRepository.find("상품1"));
	} 

}
