package hr.adapters;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.models.RoleEntity;

@Repository
@Transactional
public class RoleAdapter extends BaseEntityAdapter<RoleEntity> {
	
}
