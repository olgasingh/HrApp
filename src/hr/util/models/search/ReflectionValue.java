package hr.util.models.search;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionValue
{
    public static Object GetObjectVal(Object o, String column, String format)
    {
    	String[] splitedColumn = column.split("\\.");
    	Method method;
		try {
			method = ReflectionMethod.getMethod(o,"get" + splitedColumn[0]);
			o = method.invoke(o, null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (splitedColumn.length == 1)
        {
        	//Object[] formats = method.getAnnotation(DisplayFormatAttribute., true);
            //if (formats.Length > 0)
                //format = (formats[0] as DisplayFormatAttribute).DataFormatString;
            return o;
        }
        else
            return GetObjectVal(o, column.substring(splitedColumn[0].length() + 1), format);
    }
}