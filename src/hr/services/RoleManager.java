package hr.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hr.adapters.RoleAdapter;
import hr.models.RoleEntity;
import hr.models.UserEntity;

@Repository
public class RoleManager extends BaseGenericManager<RoleAdapter, RoleEntity> {

	@Autowired
	public void setDataAdapter(RoleAdapter dataAdapter) {
		super.setDataAdapter(dataAdapter);
	}
	
	@Override
	public RoleEntity getUpdatedEntity(RoleEntity entity)
	{
		RoleEntity dbEntity = getEntity(entity.getID());
		dbEntity.setDescription(entity.getDescription());
		//
		//
		return dbEntity;
	}
	 
}
