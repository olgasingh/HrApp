package hr.adapters;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.models.UserEntity;

@Repository
@Transactional
public class UserAdapter extends BaseEntityAdapter<UserEntity> {
	
}
