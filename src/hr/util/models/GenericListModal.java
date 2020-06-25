package hr.util.models;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import hr.models.BaseEntity;

public class GenericListModal<TEntity> extends BaseModel{
	
	Class<TEntity> entityClass;
	public GenericListModal()
	{
		try
		 {
	        
			 this.entityClass = (Class<TEntity>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		 }
		 catch(Exception ex)
		 {}

	}
	
	 public List<TEntity> Entities;
	 
	 public TEntity getSearch() throws InstantiationException, IllegalAccessException
	 {
		 if (search == null)
			search = entityClass.newInstance();
		 return search;
	 }
	 public void setSearch(TEntity search)
	 {
		 this.search = search;
	 }
     private TEntity search;
     public List<Columns> Columns;
     public List<OperatorWithSelected<String>> Operation;
     public int PageSize;
     public int CurrentPage;
     public int TotalRecord;
     public OrderBy OrderBy;
 }
