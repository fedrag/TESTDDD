package org.eternity.common;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;

import org.apache.naming.factory.BeanFactory;

public interface RepositoryBeanFactory{
	final static int REPO_TYPE_COLLECTION = 0;
	final static int REPO_TYPE_HYBERNATE = 1;
	public Object createRepository();

}
