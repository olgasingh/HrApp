package hr.util.models.search;

import java.util.Dictionary;
import java.util.Hashtable;

public class OpDictionary {
	 private static Dictionary<String,String> dictionary=new Hashtable<String,String>();

	 static
     {
         dictionary.put("eq", " == @{0}");
         dictionary.put("ne", " <> @{0}");
         dictionary.put("cn", ".contains(@{0})");
         dictionary.put("nc", "not like");            
         dictionary.put("gt", " > @{0}");
         dictionary.put("lt", " < @{0}");
         dictionary.put("ge", " >= @{0}");
         dictionary.put("le", " <= @{0}");
         //dictionary.Add("AND", " AND ");
         //dictionary.Add("OR", " OR ");
     }
	 
     public static String getOp(String op)
     {
         return dictionary.get(op);
     }
     public static Dictionary<String,String> getOperators()
     {
    	 return dictionary;
     }
}
