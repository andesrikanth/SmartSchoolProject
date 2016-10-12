package com.smartSchool.dataTable;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.smartSchool.facade.SmartSchoolFacade;
import javax.servlet.ServletConfig;
public class JsfTableDataModel<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 200L;
	
	private List<T> data;
	private String selectQuery;
	private String countQuery;
	private String targetExecutableMethodName;
	
	private String selectedRowKey;
	
	
	public JsfTableDataModel(String selectQuery, String countQuery,String targetExecutableMethodName) {
	    	this.selectQuery=selectQuery;
	    	this.countQuery=countQuery;
	    	this.targetExecutableMethodName=targetExecutableMethodName;
	    	
	}
	
	
	@Override
    public T getRowData(String rowKey) {
        for(T t : data){
        	try {
        		if(rowKey != null){
        			this.setSelectedRowKey(rowKey);
        		}
        		else {
        			rowKey=this.getSelectedRowKey();
        		}
        		Class noparams[] = {};
            	Method m=t.getClass().getMethod("getKey", noparams);
            	Object noobjs[] = {};
				Long key = (Long)m.invoke(t, noobjs);
				if(key.toString().equals(rowKey)){
					return t;
				}
				
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        	
        }
        return null;
    }
 
    @Override
    public Object getRowKey(T obj) {
    	try {
    		Class noparams[] = {};
			Method m =obj.getClass().getMethod("getKey", noparams);
				Object noobjs[] = {};
				Long key = (Long)m.invoke(obj, noobjs);
				return key;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    	
	@Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		
		String localSelectQuery=selectQuery;
		String localCountQuery=countQuery;
		boolean filtersApplied=false;
		boolean sortApplied=false;
		
		if (filters != null && filters.size()>0) {
			if(localSelectQuery != null && (localSelectQuery.contains(" WHERE ") || localSelectQuery.contains(" where "))){
				localSelectQuery = localSelectQuery+" AND ";
			}
			else {
				localSelectQuery = localSelectQuery+" WHERE ";
			}
			
			if(localCountQuery != null && (localCountQuery.contains(" WHERE ") || localCountQuery.contains(" where "))){
				localCountQuery=localCountQuery+" AND ";
			}
			else {
				localCountQuery=localCountQuery+" WHERE ";
			}
			
			int c=0;
			filtersApplied=true;
            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    Object filterValue = filters.get(filterProperty);
                    if(c==0){
                    	localSelectQuery = localSelectQuery+filterProperty+" like '%"+filterValue+"%' ";
                    	localCountQuery = localCountQuery+filterProperty+" like '%"+filterValue+"%' ";
                    }
                    else {
                    	localSelectQuery = localSelectQuery+" AND "+filterProperty+" like '%"+filterValue+"%' ";
                    	localCountQuery = localCountQuery+" AND "+filterProperty+" like '%"+filterValue+"%' ";
                    }
                    c++;
                } catch(Exception e) {
                	e.printStackTrace();
                }
            }
        }
		
		if(sortOrder != null && sortField!=null){
			sortApplied=true;
			if(sortOrder.equals(SortOrder.ASCENDING)){
				localSelectQuery = localSelectQuery +" ORDER BY "+sortField+" ASC ";
			}
			else if (sortOrder.equals(SortOrder.DESCENDING)){
				localSelectQuery = localSelectQuery +" ORDER BY "+sortField+" DESC ";
			}
		}
		else {
			localSelectQuery = localSelectQuery +" ORDER BY 1 ";
		}
		
		System.out.println("generated select query in JsfTableDataModel "+localSelectQuery);
		System.out.println("generated count query in JsfTableDataModel "+localCountQuery);
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		if(!filtersApplied && sortApplied){
			//Do nothing as we don't need to do a database call for getting rowCount again as user did a sorting. Because sorting the rows won't change the number of rows returned previously.
		}
		else {
			int rowCount=smartSchoolFacade.getRowCountForDataTable(localCountQuery+";");
			this.setRowCount(rowCount);
		}
		
		Class noparams[] = {};
		Method m;
		try {
			m = SmartSchoolFacade.class.getMethod(targetExecutableMethodName, Integer.class , Integer.class, String.class);
			Object objs[] = new Object[]{first, pageSize,localSelectQuery};
			data = (List<T>)m.invoke(smartSchoolFacade, first, pageSize,localSelectQuery);
		
		    //data=(List<T>) smartSchoolFacade.getAvailableSubjectsListForDataTable(first, pageSize,localSelectQuery);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		   
      return data;
    }


	public String getTargetExecutableMethodName() {
		return targetExecutableMethodName;
	}


	public void setTargetExecutableMethodName(String targetExecutableMethodName) {
		this.targetExecutableMethodName = targetExecutableMethodName;
	}


	public List<T> getData() {
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}


	public String getSelectQuery() {
		return selectQuery;
	}


	public void setSelectQuery(String selectQuery) {
		this.selectQuery = selectQuery;
	}


	public String getCountQuery() {
		return countQuery;
	}


	public void setCountQuery(String countQuery) {
		this.countQuery = countQuery;
	}


	public String getSelectedRowKey() {
		return selectedRowKey;
	}


	public void setSelectedRowKey(String selectedRowKey) {
		this.selectedRowKey = selectedRowKey;
	}
	
	
	
}
