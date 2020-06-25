package hr.tags;

import hr.controllers.AjaxUtils;
import hr.util.models.Columns;
import hr.util.models.GenericListModal;
import hr.util.models.search.ReflectionValue;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.request.WebRequest;


public class MyTag extends SimpleTagSupport {
   

    private String getSelectList()
    {
        return "<Select />";
    }
   
    private GenericListModal<?> entity;
   
    public void setEntity(GenericListModal<?> entity)
    {
        this.entity = entity;
    }
   
   
    private String controllerPath;
   
    public void setControllerPath(String controllerPath)
    {
        this.controllerPath = controllerPath;
    }
   
    private String method;
    public void setMethod(String method)
    {
        this.method = method;
    }
   
    private String buildTable(List<Columns> columns, HttpServletRequest request) throws InstantiationException, IllegalAccessException
    {
    	StringBuilder sb = new StringBuilder();
    	String formId = UUID.randomUUID().toString();
    	if (!AjaxUtils.isAjaxRequest(request))
    	{
    		        
            sb.append(String.format("<Form id='frm_%s' Action='http://localhost:8080/HrApp/roles'><table border='1' id='tbl_%s' width='100%%'><thead><tr><th style='width:40px;'></th>", formId, controllerPath, method,formId));
            for(Columns column :columns )
            {
                sb.append(String.format("<th style='width:%dpx;'></th>", column.colWidth));
            }
            sb.append(String.format("</tr></thead><tbody>%s</tbody></table></Form>", buildTableData(formId,columns)));
    	}
    	else
    	{
            sb.append(String.format("<tbody>%s</tbody>", buildTableData(formId,columns)));
    	} 
        return sb.toString();
    }
    
    private String buildTableData(String formId,List<Columns> columns) throws InstantiationException, IllegalAccessException
    {
        StringBuilder sb = new StringBuilder();
       
        //Add fake Headers
        sb.append(String.format("<tr><th><a href='%s/edit' class='ui-icon ui-widget ui-state-default ui-corner-all ui-icon-circle-plus blue' title='Add' addEditDialogId='addDialog' data_dialog_title='Add' onclick='return AddEdit(this);'>Add</a></th>",controllerPath));
       
        for(Columns column :columns )
        {
            String format = null;
            Object tmp = ReflectionValue.GetObjectVal(entity.getSearch(), column.column, format);
            if (column.column != entity.OrderBy.column)
                sb.append(String.format("<th style='width:%dpx;'><a href='%s'>%s</a></th>",column.colWidth, "dd", column.display));
            else

                sb.append(String.format("<th style='width:%dpx;'><div style='display: table;'>" +
                        "<a href='%s'>%s</>" +
                        "<span style='display:table-cell' class='ui-icon ui-icon-arrowthick-1-{4}'/></div></th>"
                    , column.colWidth,"dsfds",column.display));
        }
        sb.append("</tr><tr>");
        //End Fake Headers
                 

         //Add Search Button           
         sb.append(
                 String.format("<td><div style='display: table;' valign='middle'>" +
                         "<a href='%s' class='ui-icon ui-widget ui-state-default ui-corner-all ui-icon-print blue' title='Print' onclick='AddEdit(this); return false;'>Print</a>" +
                         "<span class='clsFilterChild'>" +
                         "<a href='http://localhost:8080/HrApp/roles' class='ui-icon ui-widget ui-state-default ui-corner-all ui-icon-circle-zoomin blue' title='Search' onclick='Search(this);return false;'>Search</a>" +
                         "</span></div></td>","dsfds",controllerPath)
                 );

        
       //Add search boxes
         String operators = getSelectList();
         for (int i = 0; i < columns.size(); i++)
         {
             String col = ((Columns) entity.Columns.get(i)).column;
             String format = null;
             Object tmp = ReflectionValue.GetObjectVal(entity.getSearch(), col,format);

             sb.append(String.format("<td><div class='clsFilterParent'><div class='clsFilterOperation'>%s</div><span class='clsFilterChild'>%s</span></div></td>",
                     operators,
                     String.format("<input type='text' name='Search.%s'  value='%s' style='width:100%%' />", col, "ddd")));
         }
         sb.append("</tr>");
        
        
       //Add Data
         int rows = entity.Entities.size();
         for (int i = 0; i < rows; i++)
         {
             int Id = (int) ReflectionValue.GetObjectVal(entity.Entities.get(i), "ID","");
             //Edit delete Buttons
             sb.append(String.format("<tr class='%s'><td><div style='display: table;' valign='middle'>" +
                     "%s" +
                     "<span class='clsFilterChild'>" +
                     "%s" +
                     "</span></div></td>", (i % 2 != 0 ? "pRowClass" : "oRowClass"),
                    
                     String.format("<a deleteDialogId='confirmDialog' title='Delete' onclick='return Del(this);' class='ui-icon ui-widget ui-state-default ui-corner-all ui-icon-circle-minus red' href='/Delete?ID=%s'>Delete</a>", Id),
                       String.format("<a addEditDialogId='addEditDialog' title='Edit' onclick='return AddEdit(this);' class='ui-icon ui-widget ui-state-default ui-corner-all ui-icon-pencil blue' href='/Edit?ID=%s'>Delete</a>", Id)                            
                     ));

             for(Columns column: columns)
             {
                 String format = "";
                 String value =  "";
                 Object o = ReflectionValue.GetObjectVal(entity.Entities.get(i), column.column, format);
                 //if (o is DateTime?)
                 //    value = (o as DateTime?).Value.ToString(format);
                 //else
                 value = o.toString();
                 sb.append(String.format("<td>%s</td>",value));
             }
             sb.append("</tr>");
         }
        
        
        return sb.toString();
    }
   
    @SuppressWarnings("unchecked")
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer =  getJspContext().getOut();
        PageContext pageContext = (PageContext)getJspContext(); 
        HttpServletRequest  request = (HttpServletRequest)pageContext.getRequest();
       
        String url = request.getRequestURL().toString();
           url = url.replace(".jsp", "");
        url = url.replace("/views", "");
       
       
        //String url = request.getRequestURL().toString().replace(request.getRequestURI(),request.getContextPath());
               
   
        List<Columns> columns = (List<Columns>) entity.Columns;
        /*
        for(Columns column :columns )
        {
            sb.append(String.format("<th style='width:%dpx;'></th>", column.colWidth);
            sb.append("<td>");
            sb.append(column.column);
            sb.append("</td>");
        }
        builder.AppendFormat("</tr></thead><tbody>{0}</tbody></table></Form>", GetTableData<TEntity>(helper, formId));
       
        sb.append("</tr>");
        List<BaseEntity> entities = (List<BaseEntity>) entity.Entities;
        for(BaseEntity ientity : entities)
        {
            sb.append("<tr><td>");
            sb.append("<a href='"+ url + "/edit?ID=" + ientity.getID() + "'>" +  ientity.getID() + "</a>");
            sb.append("</td>");
           
            for(Columns column :columns )
            {
                sb.append("<td>");
               
                String[] tmpcolumns = column.column.split("\\.");  
               
                Object object = ientity;
                Method method = null;
                for(int i=0; i<tmpcolumns.length;i++)
                {
                    /*Class current = object.getClass();                   
                    while (current != Object.class) {
                         try {
                              method = current.getDeclaredMethod("get"+tmpcolumns[i], null);
                              break;
                         } catch (NoSuchMethodException ex) {
                              current = current.getSuperclass();
                         }
                    }
                    */
                    /*
                    method= ReflectionMethod.getMethod(object, "get"+tmpcolumns[i]);
                    try {
                        object = method.invoke(object, null);
                    } catch (IllegalAccessException | IllegalArgumentException
                            | InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }                   
                }
               
                sb.append(object.toString());
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");*/
        try {
			writer.write(buildTable(columns,request));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


