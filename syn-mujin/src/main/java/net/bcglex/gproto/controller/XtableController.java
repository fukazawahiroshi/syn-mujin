package net.bcglex.gproto.controller;

import java.util.HashMap;
import java.util.List;

import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Domain;
import net.bcglex.gproto.model.Employee;
import net.bcglex.gproto.service.AdmUserService;
import net.bcglex.gproto.service.AssetService;
import net.bcglex.gproto.service.EmployeeService;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class XtableController extends Controller {

//	public Navigation setUp() {
//    	String pms="";
//    	Enumeration en = request.getParameterNames();
//    	while(en.hasMoreElements()){
//    		pms+=en.nextElement()+",";
//    	}
//    	System.out.println("api "+pms);
//		return null;
//	}

    @Override
    public Navigation run() throws Exception {
    	//ユーザー確認
    	UserService userService = UserServiceFactory.getUserService();
        response.setContentType("text/html; charset=utf-8");
        if (request.getUserPrincipal() != null) {
        	//googleアカウントにログインしている
//        	String userEmail = request.getUserPrincipal().getName();
        	String userEmail = userService.getCurrentUser().getEmail();
			AssetService.setAssets(userEmail);
//            if(AssetService.isValidSuperAdmin(userEmail)){
//            	NamespaceManager.set("root");
//            }
//    		if(AssetService.isValidAdmin(userEmail) || AssetService.isValidSuperAdmin(userEmail)){
//    		} else {
//    			//管理者のアカウントでない
//                response.getWriter().println("");
//                return null;
//    		}
        } else {
            response.getWriter().println("");
            return null;
        }
        //共通パラメータ取得
    	String orderTypeStr=param("x-table[order_type]");
    	String recordPerPageStr=param("x-table[record_per_page]");
    	String pageIndexStr=param("x-table[page_index]");
    	String orderColumn=param("x-table[order_column]");	//possibly null
        String table=param("table");

        if(table!=null){
        	if(table.equals("user")){
        		System.out.println("user_table");
        		NamespaceManager.set("root");

            	Integer recordPerPage=(recordPerPageStr==null)? 1:Integer.valueOf(recordPerPageStr);
            	Integer pageIndex=(pageIndexStr==null)? 1:Integer.valueOf(pageIndexStr);
        		List<AdmUser> users=AdmUserService.getUsers();
                int start=(pageIndex-1)*recordPerPage;
                int end=(start+recordPerPage<users.size())? start+recordPerPage:users.size();
                //指定範囲取得
                List<AdmUser> emp=users.subList(start, end);
                int offset=(pageIndex-1)*recordPerPage+1;
        		response.setContentType("text/html; charset=UTF-8");
        		HashMap<String, Object>info=new HashMap<String, Object>();
        		info.put("rows", emp);
        		info.put("total_page", (users.size()+recordPerPage-1)/recordPerPage);
        		info.put("total_row", users.size());
        		info.put("page_index",pageIndex);
        		info.put("offset", offset);
        		info.put("row_per_page", recordPerPage);

        		//JSON変換
        		ObjectMapper mapper=new ObjectMapper();
        		ObjectNode subNode=mapper.valueToTree(info);
        		//can access first name of index at i by rootNode[i]["fname"]...
        		String json=subNode.toString();
        		//System.out.println(json);
        		response.getWriter().println(json);
        		return null;
        	} else if (table.equals("domain")){
        		NamespaceManager.set("root");
            	Integer recordPerPage=(recordPerPageStr==null)? 1:Integer.valueOf(recordPerPageStr);
            	Integer pageIndex=(pageIndexStr==null)? 1:Integer.valueOf(pageIndexStr);
        		List<Domain> domains=AdmUserService.getDomains();
                int start=(pageIndex-1)*recordPerPage;
                int end=(start+recordPerPage<domains.size())? start+recordPerPage:domains.size();
                //指定範囲取得
                List<Domain> dom=domains.subList(start, end);
                int offset=(pageIndex-1)*recordPerPage+1;
        		response.setContentType("text/html; charset=UTF-8");
        		HashMap<String, Object>info=new HashMap<String, Object>();
        		info.put("rows", dom);
        		info.put("total_page", (domains.size()+recordPerPage-1)/recordPerPage);
        		info.put("total_row", domains.size());
        		info.put("page_index",pageIndex);
        		info.put("offset", offset);
        		info.put("row_per_page", recordPerPage);

        		//JSON変換
        		ObjectMapper mapper=new ObjectMapper();
        		ObjectNode subNode=mapper.valueToTree(info);
        		//can access domain of index at i by rootNode[i]["fname"]...
        		String json=subNode.toString();
        		//System.out.println(json);
        		response.getWriter().println(json);
        		return null;
        	}
        }
        NamespaceManager.set(AssetService.getDomain());
        //パラメータ取得
    	String deptFilter=param("x-table[conditions][department]");
    	String sectFilter=param("x-table[conditions][section]");
    	String subsectFilter=param("x-table[conditions][subsection]");
    	String snameFilter=param("x-table[conditions][sname]");
    	String fnameFilter=param("x-table[conditions][fname]");
    	orderTypeStr=(orderTypeStr==null)? "ASC":orderTypeStr;
    	deptFilter=(deptFilter==null)? null:(deptFilter.isEmpty())? null:deptFilter;
    	sectFilter=(sectFilter==null)? null:(sectFilter.isEmpty())? null:sectFilter;
    	subsectFilter=(subsectFilter==null)? null:(subsectFilter.isEmpty())? null:subsectFilter;
    	snameFilter=(snameFilter==null)? null:(snameFilter.isEmpty())? null:snameFilter;
    	fnameFilter=(fnameFilter==null)? null:(fnameFilter.isEmpty())? null:fnameFilter;
    	Integer recordPerPage=(recordPerPageStr==null)? 1:Integer.valueOf(recordPerPageStr);
    	Integer pageIndex=(pageIndexStr==null)? 1:Integer.valueOf(pageIndexStr);

    	List<Employee>tmp;
    	//名前検索か、部署検索か
    	if(snameFilter!=null || fnameFilter!=null){
    		tmp=EmployeeService.getEmployeesByName(snameFilter, fnameFilter);
    	} else {
    		tmp=EmployeeService.getEmployeesSorted(deptFilter, sectFilter, subsectFilter, orderColumn, orderTypeStr);
    	}
        int start=(pageIndex-1)*recordPerPage;
        int end=(start+recordPerPage<tmp.size())? start+recordPerPage:tmp.size();
        //指定範囲取得
        List<Employee> emp=tmp.subList(start, end);
        int offset=(pageIndex-1)*recordPerPage+1;
		response.setContentType("text/html; charset=UTF-8");
		HashMap<String, Object>info=new HashMap<String, Object>();
		info.put("rows", emp);
		info.put("total_page", (tmp.size()+recordPerPage-1)/recordPerPage);
		info.put("total_row", tmp.size());
		info.put("page_index",pageIndex);
		info.put("offset", offset);
		info.put("row_per_page", recordPerPage);

		//JSON変換
		ObjectMapper mapper=new ObjectMapper();
		ObjectNode subNode=mapper.valueToTree(info);
		//can access first name of index at i by rootNode[i]["fname"]...
		String json=subNode.toString();
		//System.out.println(json);
		response.getWriter().println(json);
		return null;
    }
}
