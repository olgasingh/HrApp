package hr.util.models.search;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class GrFilterCollection extends ArrayList<GrFilter>
{
        public boolean add(GrFilter grFilter)
        {
            return super.add(grFilter);
        }

        public void Remove(int index) throws Exception
        {
            if (index > super.size() - 1 || index < 0)
            {
                throw new Exception("Index out of bound");
            }
            else
            {
                super.remove(index);
            }
        }

        public GrFilter Item(int index)
        {
            return (GrFilter)super.get(index);
        }

        public String getFilterStrings()
        {
        		StringBuilder bld = new StringBuilder();
                int i =0;
                    for (GrFilter grItem : this)
                    {
                        for (Filters filter : grItem.rules)
                        {
                            if (bld.length()>0)
                                bld.append(" And ");
                            bld.append(filter.field);
                            bld.append(String.format(filter.getOp(),i));
                            i++;
                        }
                    }
                

                return bld.toString();
        }

        /*public object[] FilterValues1 
        {
            get
            {
                int i = 0;
                foreach (GrFilter grItem in this.List)
                {
                    foreach (Filters filter in grItem.rules)
                    {                        
                        i++;
                    }
                }

                object[] o = new object[i];

                i=0;
                foreach (GrFilter grItem in this.List)
                {
                    foreach (Filters filter in grItem.rules)
                    {
                        o[i] = filter.data;
                        i++;
                    }
                }

                return o;
            }
        }*/
    }