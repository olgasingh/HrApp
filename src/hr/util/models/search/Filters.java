package hr.util.models.search;

public class Filters {

	public Filters()
	{
		
	}
        public String field;
        public String op;
        public String getOp()
        {
        	return String.format(" {0}", OpDictionary.getOp(op));
        }
        public Object data;
}