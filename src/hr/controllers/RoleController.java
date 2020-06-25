package hr.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import hr.adapters.RoleAdapter;
import hr.models.RoleEntity;
import hr.services.RoleManager;
import hr.util.models.Columns;
import hr.util.models.GenericListModal;
import hr.util.models.OrderBy;
import hr.util.models.OrderEnum;


	@Controller
	@RequestMapping("/roles")
	public class RoleController extends BaseController<RoleManager, RoleAdapter, RoleEntity>{
	 
		@Override
		public List<Columns> getColumns()
		{			
                List<Columns> columns = new ArrayList<Columns>();
                columns.add(new Columns("ID","ID", 100));
                columns.add(new Columns("Description","Description",300 ));
                return columns;
        }
		
		@Autowired
		protected void setMgr(RoleManager mgr)
		{
			super.setMgr(mgr);
		}
		
		@Override
		@RequestMapping(value="", method=RequestMethod.GET) 
		public ModelAndView handleRequest(HttpServletRequest arg0,				
			HttpServletResponse arg1) throws Exception {
	 
			 GenericListModal<RoleEntity> model = new GenericListModal<RoleEntity>();                
	         model.Columns = getColumns();
	         model.CurrentPage = 1;
	         model.PageSize = 10;
	         model.OrderBy = new OrderBy ("ID", OrderEnum.Desc);
	         model.setSearch(GetSearchEntity());
	         return getSearch(model,"");
	         
			/*ModelAndView modelAndView = new ModelAndView(getListView());
			modelAndView.addObject("columns",getColumns());
			modelAndView.addObject("entities",getEntities());
	 
			return modelAndView;*/
		}
		
		@Override
		@RequestMapping(value="", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView Filter(@ModelAttribute("entity")  GenericListModal<RoleEntity> entity, BindingResult result, SessionStatus status) throws InstantiationException, IllegalAccessException
		{
			return super.Filter(entity, result, status);
	         
		}
	}