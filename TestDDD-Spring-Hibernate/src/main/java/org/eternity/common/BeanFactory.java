package org.eternity.common;

import java.util.Map;

public interface BeanFactory {
	final static int REPO_TYPE_COLLECTION = 0;
	final static int REPO_TYPE_HYBERNATE = 1;
	public void setParams(Map params);
	public Object createBean();
	
}
