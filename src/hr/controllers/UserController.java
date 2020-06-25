package hr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.adapters.UserAdapter;
import hr.models.UserEntity;
import hr.services.UserManager;
import hr.util.models.Columns;


	@Controller
	@RequestMapping("/users")
	public class UserController extends BaseController<UserManager, UserAdapter, UserEntity>{
	 
		@Override
		public List<Columns> getColumns()
		{			
                List<Columns> columns = new ArrayList<Columns>();
                columns.add(new Columns("ID","ID", 100));
                columns.add(new Columns("Email","Email",300 ));
                return columns;
        }

		
		@Autowired
		protected void setMgr(UserManager mgr)
		{
			super.setMgr(mgr);
		}
	}