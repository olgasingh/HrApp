package hr.util.models.search;

import java.lang.reflect.Method;

public class ReflectionMethod {

	public static Method getMethod(Object object, String method)
	{
		Method tmpMethod = null;
		Class current = object.getClass();        			
		while (current != Object.class) {
	     try {
	    	 tmpMethod = current.getDeclaredMethod(method, null);
	          break;
	     } catch (NoSuchMethodException ex) {
	          current = current.getSuperclass();
	     }
		}
		return tmpMethod;
	}
}
