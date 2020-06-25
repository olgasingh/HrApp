package hr.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import hr.adapters.UserAdapter;
import hr.models.UserEntity;

@Repository
public class UserManager extends BaseGenericManager<UserAdapter, UserEntity> {

	@Autowired
	public void setDataAdapter(UserAdapter dataAdapter) {
		super.setDataAdapter(dataAdapter);
	}
	
	@Override
	public UserEntity getUpdatedEntity(UserEntity entity)
	{
		UserEntity dbEntity = getEntity(entity.getID());
		dbEntity.setEmail(entity.getEmail());
		//
		//
		return dbEntity;
	}
}
