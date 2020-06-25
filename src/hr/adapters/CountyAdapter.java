package hr.adapters;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.models.CountyEntity;

@Repository
@Transactional
public class CountyAdapter extends BaseEntityAdapter<CountyEntity> {
	
}
