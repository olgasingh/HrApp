package hr.services;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hr.adapters.CountyAdapter;
import hr.models.CountyEntity;
import hr.models.RoleEntity;

@Repository
public class CountyManager extends BaseGenericManager<CountyAdapter, CountyEntity> {

	@Autowired
	public void setDataAdapter(CountyAdapter dataAdapter) {
		super.setDataAdapter(dataAdapter);
	}

	@Override
	public CountyEntity getUpdatedEntity(CountyEntity entity)
	{
		CountyEntity dbEntity = getEntity(entity.getID());
		dbEntity.setDescription(entity.getDescription());
		//
		//
		return dbEntity;
	}
}
