package net.bcglex.gproto.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import net.bcglex.gproto.model.AdInfo;
import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Employee;
import net.bcglex.gproto.service.AdmUserService;
import net.bcglex.gproto.service.AssetService;
import net.bcglex.gproto.service.EmployeeService;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.RequestMap;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ApiController extends Controller {

	private static String getEmployeeArrayJson(String department, String section, String subsection){
	    String json=EmployeeService.employeeJson(EmployeeService.getEmployees(department, section, subsection));
	    return json;
	}

    @Override
    public Navigation run() throws Exception {
//    	Map<String, Object> input=new RequestMap(request);
////    	setNeedToShow
//    	String pms="";
//    	Enumeration en = request.getParameterNames();
//    	while(en.hasMoreElements()){
//    		pms+=en.nextElement()+",";
//    	}
//    	System.out.println(pms);
    	String email="";
    	UserService userService = UserServiceFactory.getUserService();

//    	System.out.println("test1 super admin "+email);	//test

    	//ユーザー確認
        if (request.getUserPrincipal() != null) {
//            email=request.getUserPrincipal().getName();
        	email = userService.getCurrentUser().getEmail();
        	AssetService.setAssets(email);
//        	System.out.println("super admin "+email);	//test
            if(AssetService.isValidSuperAdmin(email)){
//            	System.out.println("super admin "+email);
//            	NamespaceManager.set("root");
            }
        	if(AssetService.isValidUser(email)){
        		//OK
        		AdmUser user=AdmUserService.getUserByEmail(email);
        		if(user==null){
        			//userがemployeeのリストに存在しない場合 no locationのリストを表示
        			EmployeeService.setTargetLocation("no location");
        		} else {
                    EmployeeService.setTargetLocation(user.getLocation());
        		}
        	}
//        	else if(AssetService.isValidSuperAdmin(email)==false){
//        		//googleアカウントのドメインが違う
//        		return null;
//        	}
        } else {
        	//googleアカウントにログインしていない
            return null;
        }

//    	System.out.println("test super admin "+email);	//test
		response.setContentType("text/html; charset=UTF-8");
    	if(param("setNeedToShow") != null){
    		Key key=null;
    		NamespaceManager.set(AssetService.getDomain());
    		if(param("key")!=null){
        		key=Datastore.stringToKey((String) param("key"));
    		} else if(param("id")!=null){
    			System.out.println(param("id"));
    			try{
        			Integer numId=Integer.valueOf(param("id"));
        			key=Datastore.createKey(Employee.class, numId);
    			} catch(Exception e) {
    				key=Datastore.createKey(Employee.class, param("id"));
    			}
    		}
        	EmployeeService.setNeedToShow(key, Boolean.valueOf((String)param("setNeedToShow")));
        	response.getWriter().println("good");
    	} else if(param("getEmpArray")!=null){

    	} else if(param("getEmpByInitial")!=null){
    		String pattern=param("getEmpByInitial");
    		if(pattern!=null){
    			String res=EmployeeService.fetchEmployeeByInitials(pattern);
//    			System.out.println(res);
    			response.getWriter().println(res);
    		}
    	}
/*<!-- ***************************************************************************************************************************************************************************************** -->*/
/*<!-- ***************************************************************************************************************************************************************************************** -->*/
		else if (param("getEmpByInitial_department") != null)
		{
    		String pattern = param("getEmpByInitial_department");

    		if (pattern != null)
    		{
    			String res = EmployeeService.fetchEmployeeByInitials_department(pattern);
//        		System.out.println(res);
    			response.getWriter().println(res);
    		}
/*<!-- ***************************************************************************************************************************************************************************************** -->*/
/*<!-- ***************************************************************************************************************************************************************************************** -->*/
//    	} else if(param("getDepartments")!=null){
//    		TreeSet<String> deps=EmployeeService.getDepartments(null);
//    		ObjectMapper mapper=new ObjectMapper();
//    		ArrayNode node=mapper.valueToTree(deps);
//    		response.getWriter().println(node.toString());
    	} else if(param("getSections")!=null){
    		String dept=param("getSections");
    		List<Employee> elist=new ArrayList<Employee>();
    		TreeSet<String> secs=EmployeeService.fetchSections(dept, elist);
    		ObjectMapper mapper=new ObjectMapper();
    		ArrayNode node=mapper.valueToTree(secs);
    		node.add(mapper.valueToTree(elist));
    		response.getWriter().println(node.toString());
    	} else if(param("getSubsections")!=null){
    		String sect=param("getSubsections");
    		String dept=param("dept");
    		List<Employee> elist=new ArrayList<Employee>();
    		TreeSet<String> subs=EmployeeService.fetchSubSections(dept, sect, elist);
    		ObjectMapper mapper=new ObjectMapper();
    		ArrayNode node=mapper.valueToTree(subs);
    		node.add(mapper.valueToTree(elist));
    		response.getWriter().println(node.toString());
    	} else if(param("getEmpList")!=null){
    		String subsect=param("getEmpList");
    		String sect=param("sect");
    		String dept=param("dept");
    		List<Employee> emps=EmployeeService.fetchEmployees(dept, sect, subsect);
    		ObjectMapper mapper=new ObjectMapper();
    		ArrayNode node=mapper.valueToTree(emps);
    		response.getWriter().println(node.toString());
    	// user追加処理
    	} else if(param("adduser")!=null){
    		if(AdmUserService.addUser(param("adduser"))){
        		response.getWriter().println(1);
    		} else {
        		response.getWriter().println(0);
    		}
    	// user削除処理
    	} else if(param("deleteUser")!=null){
    		AdmUserService.deleteUser(param("deleteUser"));
    		response.getWriter().println(1);
    	} else if(param("setMessageText")!=null){
    		System.out.println(param("setMessageText"));
    		AssetService.setMessage(param("setMessageText"));
    	// domain追加処理
    	} else if(param("emailfield")!=null && param("password")!=null ){
    		System.out.println(param("emailfield")+" "+param("password"));
    		if(AssetService.addDomain(param("emailfield"), param("password"))==true){
        		response.getWriter().println(1);
    		} else {
        		response.getWriter().println(0);
    		}
    	// domain削除処理
    	} else if(param("deleteDomain")!=null){
    		AssetService.deleteDomain(param("deleteDomain"));
    	// location更新処理
    	} else if(param("location")!=null){
    		System.out.println("api location update in");
    		AdmUserService.updateLocation(param("location"));
    		requestScope("location",param("location"));
    	}
        return null;
    }
}
