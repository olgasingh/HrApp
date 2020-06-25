package hr.adapters;

import java.util.List;
import java.lang.reflect.*;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;
import org.springframework.transaction.annotation.Transactional;

import hr.models.BaseEntity;

@Transactional
public abstract class BaseEntityAdapter<TEntity extends BaseEntity> extends BaseAdapter {
	
	private Class<TEntity> persistentClass;
	 
	 @SuppressWarnings("unchecked")
	public BaseEntityAdapter() 
	 {
		 try
		 {
	        // FIXME : I don't like magic number in the code, is there any way to fix 0 to something dynamic?
	        this.persistentClass = (Class<TEntity>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		 }
		 catch(Exception ex)
		 {}
	 }
	 
	 
	
	public TEntity addEntity(TEntity entity) {
		getSession().save(entity);
		return entity;
	}
	
	
	public void deleteEntity(TEntity entity) {
		getSession().delete(entity);
	}

	public void updateEntity(TEntity entity) {
		getSession().update(entity);
	}

	
	@SuppressWarnings("unchecked")
	public TEntity getEntity(Integer iD) {
		return (TEntity) getSession().get(persistentClass, iD);
	}	
	
	@SuppressWarnings("unchecked")
	public TEntity getEntity(List<String> includes,Integer iD) {
		Criteria crit = getSession().createCriteria(persistentClass);
		
		for(String include: includes)
		{
			crit.createCriteria(include, JoinFragment.LEFT_OUTER_JOIN);
			crit.setFetchMode(include, FetchMode.JOIN);
		}		
		crit.add(Restrictions.eq("ID",iD));
		List<TEntity> entities = crit.list();
		if (entities.isEmpty())
			return null;
		return (TEntity) entities.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<TEntity> getEntities(List<Criterion> criterions) {	
		Criteria crit = getSession().createCriteria(persistentClass);
	    for (Criterion c : criterions) {
	    	crit.add(c);
        	}
        return (List<TEntity>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TEntity> getEntities(List<String> includes, List<Criterion> criterions) {	
		Criteria crit = getSession().createCriteria(persistentClass);
		
		for(String include: includes)
		{
			crit.createCriteria(include, JoinFragment.LEFT_OUTER_JOIN);
			crit.setFetchMode(include, FetchMode.JOIN);
		}		
		
	    for (Criterion c : criterions) {
	    	crit.add(c);
        	}
        return (List<TEntity>) crit.list();
	}

}
