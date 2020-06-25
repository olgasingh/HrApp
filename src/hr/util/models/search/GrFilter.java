package hr.util.models.search;

import java.util.List;

public class GrFilter {
	
	public GrFilter()
	{
		
	}

        public String groupOp;


        public String getGroupOpF()
        {
        	return String.format(" {0}", OpDictionary.getOp(groupOp));
        }
        

        public List<Filters> rules;

    }

