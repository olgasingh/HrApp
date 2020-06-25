package hr.controllers;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.adapters.CountyAdapter;
import hr.models.CountyEntity;
import hr.services.CountyManager;
import hr.util.models.Columns;


	@Controller
	@RequestMapping("/counties")
	public class CountyController extends BaseController<CountyManager, CountyAdapter, CountyEntity>{
	 
		@Override
		public List<Columns> getColumns()
		{			
                List<Columns> columns = new ArrayList<Columns>();

                columns.add(new Columns("ID","ID", 100));
                columns.add(new Columns("Code","Code",300));
                columns.add(new Columns("Description","Description",300 ));
                columns.add(new Columns("StateProvince.Description","StateProvince",300));
                return columns;
        }
		
		@Autowired
		protected void setMgr(CountyManager mgr)
		{
			super.setMgr(mgr);
		}
		@Override
		protected List<CountyEntity> getEntities()
		{
			CountyManager mgr = (CountyManager) getMgr();
			
			List<String>includes = new ArrayList<String>();			
			includes.add("stateProvince");				
			return mgr.getEntities(includes, new ArrayList<Criterion>());
		}
		@Override
		protected CountyEntity getEntity(Integer iD)
		{
			CountyManager mgr = (CountyManager) getMgr();
			List<String>includes = new ArrayList<String>();			
			includes.add("stateProvince");				
			return mgr.getEntity(includes, iD);			
		}
	}