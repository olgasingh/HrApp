package hr.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
	 
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
	 
import hr.adapters.BaseEntityAdapter;
import hr.models.BaseEntity;
import hr.services.BaseGenericManager;
import hr.tags.MyTag;
import hr.util.models.Columns;
import hr.util.models.GenericListModal;
import hr.util.models.OrderBy;
import hr.util.models.OrderEnum;
import hr.util.models.search.Filters;
import hr.util.models.search.GrFilter;
import hr.util.models.search.GrFilterCollection;
import hr.util.models.search.ReflectionValue;

import org.hibernate.criterion.Criterion;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

	@SuppressWarnings("rawtypes")
	public abstract class BaseController<TMgr extends BaseGenericManager, TAdapter extends BaseEntityAdapter<TEntity>, TEntity extends BaseEntity> {
	 		
		private Class<TEntity> entityClass;
		public abstract List<Columns> getColumns();
		
		private String getListView()
		{
			String viewName = this.getClass().getAnnotation(RequestMapping.class).value()[0];			
			return viewName.substring(1);
		}
		
		private String GetSingleView()
		{
			String viewName = getListView();			
			return viewName.substring(0,viewName.length()-1);
		}
		
		protected TEntity GetSearchEntity() throws InstantiationException, IllegalAccessException
	    {
			return (TEntity) entityClass.newInstance();
	    }
		
		@SuppressWarnings("unchecked")
		public BaseController() 
		 {
			 try
			 {
		        // FIXME : I don't like magic number in the code, is there any way to fix 0 to something dynamic?
				 //Class<TMgr> persistentClass = (Class<TMgr>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
				 //mgr = persistentClass.newInstance();
				 this.entityClass = (Class<TEntity>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[2];
			 }
			 catch(Exception ex)
			 {}
		 }
		
		
		private BaseGenericManager<TAdapter,TEntity> mgr;
		
		//@Autowired //bug in spring framework
		protected void setMgr(BaseGenericManager<TAdapter,TEntity> mgr)
		{
			this.mgr= mgr;
		}
		
		protected BaseGenericManager<TAdapter,TEntity> getMgr()
		{
			return this.mgr;
		}
		
		
		protected List<TEntity> getEntities()
		{
			return mgr.getEntities(new ArrayList<Criterion>());
		}
		
		protected TEntity getEntity(Integer iD)
		{
			return mgr.getEntity(iD);
		}
		
		@RequestMapping(value="", method=RequestMethod.GET) 
		public ModelAndView handleRequest(HttpServletRequest arg0,				
			HttpServletResponse arg1) throws Exception {
	 
			 GenericListModal<TEntity> model = new GenericListModal<TEntity>();                
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
		
		@RequestMapping(value="", method=RequestMethod.POST)
		@ResponseBody
		public ModelAndView Filter(@ModelAttribute("entity")  GenericListModal<TEntity> entity, BindingResult result, SessionStatus status) throws InstantiationException, IllegalAccessException
		{
			//MyTag tag = new MyTag();
			entity.Columns = getColumns();
			entity.CurrentPage = 1;
			entity.PageSize = 10;
			entity.OrderBy = new OrderBy ("ID", OrderEnum.Desc);
			entity.setSearch( GetSearchEntity());
			return getSearch(entity,"_");
	         
		}
		
		 public ModelAndView getSearch(GenericListModal<TEntity> model,String prefix) throws InstantiationException, IllegalAccessException
	        {
	            //if (1==1)//(PrGetModulePermission.R.HasValue && PrGetModulePermission.R.Value == 1)
	            //{
	                GrFilterCollection gfc = new GrFilterCollection();
	                GrFilter gf = new GrFilter();
	                gfc.add(gf);
	                List<Filters> rules = new ArrayList<Filters>();
	                gf.rules = rules;
	                List<Columns> columns= getColumns();
	                for (int i = 0; i < columns.size(); i++)
	                {
	                    String format = "";
	                    Object val = ReflectionValue.GetObjectVal(model.getSearch(), columns.get(i).column, format);
	                    if (val != null)
	                    {
	                        Filters rul = new Filters();
	                        rul.data = val;
	                        rul.field = columns.get(i).column;
	                        rul.op= model.Operation.get(i).SelectedVal;
	                        rules.add(rul);
	                    }
	                }

	                int f = model.PageSize;
	                model.Entities = getEntities();//.GetEntities(gfc, (model.CurrentPage * f) - f, ref f, model.OrderBy.OrderByString);
	                model.Columns = columns;
	                model.TotalRecord = f;                
	                //model.PrGetModulePermission = this.PrGetModulePermission;
	                
	                
	               // if (AjaxUtils.isAjaxRequest(request))
	                   // return PartialView(String.format("{0}_table", "PartialFolderName"), model);
	                ModelAndView modelAndView = new ModelAndView(prefix+getListView());
	    			modelAndView.addObject("entity",model);
	    			return modelAndView;
	            //}
	            //else
	            //{
	             //   //if (Request.IsAjaxRequest())
	            //        //return PartialView(string.Format("NoPermission/Index"));
	            //    //else
	            //        return new ModelAndView("redirect:NoPermission/Index");
	            //}
	        }             
		
		@RequestMapping(value="/edit{ID}", method=RequestMethod.GET)
		public ModelAndView edit(@RequestParam("ID")Integer id)
		{
			ModelAndView modelAndView = new ModelAndView(GetSingleView());
			modelAndView.addObject("entity",getEntity(id));
			return modelAndView;
		}
		
		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit() throws InstantiationException, IllegalAccessException
		{
			ModelAndView modelAndView = new ModelAndView(GetSingleView());
			modelAndView.addObject("entity",GetSearchEntity());
			return modelAndView;
		}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value="/edit", method=RequestMethod.POST)
		public String update(@ModelAttribute("entity") TEntity entity, BindingResult result, SessionStatus status)
		{
			//validator.validate(contact, result);
			//if (result.hasErrors()) {
			//	return "editContact";
			//}
			mgr.updateEntity(entity);
			status.setComplete();
			return "redirect:users.html";
		}
	}