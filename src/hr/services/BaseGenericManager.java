package hr.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinFragment;
import org.springframework.beans.factory.annotation.Autowired;

import hr.adapters.BaseEntityAdapter;
import hr.models.BaseEntity;




public abstract class BaseGenericManager<TAdapter extends BaseEntityAdapter<TEntity>, TEntity extends BaseEntity> extends BaseManager{
	
	//@SuppressWarnings("unused")
	//private Class<BaseEntityAdapter<TEntity>> persistentAdapter;
	//@SuppressWarnings("unchecked")
    //public BaseGenericManager() 
	//{
	//	this.persistentAdapter = (Class<BaseEntityAdapter<TEntity>>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	//}
	 
	public abstract TEntity getUpdatedEntity(TEntity entity);
	
	private BaseEntityAdapter<TEntity> dataAdapter;

	//@Autowired //bug in Spring framework
	protected void setDataAdapter(BaseEntityAdapter<TEntity> dataAdapter)
	{
		this.dataAdapter = dataAdapter;
	}
	 	
    protected BaseEntityAdapter<TEntity> getDataAdapter()
    {
        return dataAdapter;
    }    
     
    public TEntity addEntity(TEntity entity) 
    {
    	return (TEntity) getDataAdapter().addEntity(entity);
 	}
 	
 	public void deleteEntity(TEntity entity) 
 	{
 		getDataAdapter().deleteEntity(entity);
 	}

 	public void updateEntity(TEntity entity) 
 	{
 		getDataAdapter().updateEntity(getUpdatedEntity(entity));
 	}
 	
 	public TEntity getEntity(Integer iD) 
 	{
 		return (TEntity) getDataAdapter().getEntity(iD);
 	}
 	
	public TEntity getEntity(List<String> includes,Integer iD) {	
 		return getDataAdapter().getEntity(includes, iD);
	}
	
 	public List<TEntity> getEntities(List<Criterion> criterions) 
 	{
 		return getDataAdapter().getEntities(criterions);
 	}
 	
	public List<TEntity> getEntities(List<String> includes, List<Criterion> criterions) {	
 		return getDataAdapter().getEntities(includes, criterions);
	}

}
