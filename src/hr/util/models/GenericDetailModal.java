package hr.util.models;

import hr.models.BaseEntity;

public class GenericDetailModal<TEntity extends BaseEntity> extends BaseModel {
	public TEntity entity;
}
