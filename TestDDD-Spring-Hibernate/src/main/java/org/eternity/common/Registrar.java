package org.eternity.common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Registrar {
	
	public void init();

	public void add(Class<?> entryPointClass, EntryPoint newObject) ;

	public EntryPoint get(Class<?> entryPointClass, String objectName);

	public Collection<? extends EntryPoint> getAll(
			Class<?> entryPointClass);
	
	public EntryPoint delete(Class<?> entryPointClass, String objectName);
	
}
