package net.bcglex.gproto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Employee;
import net.bcglex.gproto.service.AdmUserService;
import net.bcglex.gproto.service.AssetService;
import net.bcglex.gproto.service.EmployeeService;

public class IndexController extends Controller {

    private static final Logger logger = Logger.getLogger(IndexController.class.getName());
    private String accessToken;
    private static String location;

	@Override
	protected Navigation run() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        String thisURL = request.getRequestURI();
		response.setContentType("text/html; charset=UTF-8");
        if (request.getUserPrincipal() != null) {
//            String email=request.getUserPrincipal().getName();
        	String email=userService.getCurrentUser().getEmail();
			AssetService.setAssets(email);
        	if(AssetService.isValidUser(email)){
        		//OK
        		AdmUser user=AdmUserService.getUserByEmail(email);

        		if(user==null){
        			//userがemployeeのリストに存在しない場合 no locationのリストを表示
        			EmployeeService.setTargetLocation("no location");
        		} else {
                    EmployeeService.setTargetLocation(user.getLocation());
        		}
        	} else {
        		//google アカウントが違う。強制ログアウトして、ログイン表示
//temp        		return redirect(userService.createLogoutURL(thisURL));
        	}
        } else {
        	//ログイン表示
            response.getWriter().println("<p>Google Appsのadmin/userアカウントで<a href=\"" +
            		userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");
            return null;
        }
        //department listとcompany staffリスト取得、スコープ変数に格納
        /*<!-- ***************************************************************************************************************************************************************************************** -->*/
        /*<!-- ***************************************************************************************************************************************************************************************** -->*/
                /*
            	List<Employee> companyStaff=new ArrayList<Employee>();
            	TreeSet<String> elist=EmployeeService.fetchDepartments(companyStaff);
            	System.out.println("returned "+elist.size()+" departments "+companyStaff.size()+" employees");
            	requestScope("departmentList", elist);
            	*/
        /*<!-- ***************************************************************************************************************************************************************************************** -->*/
        /*<!-- ***************************************************************************************************************************************************************************************** -->*/
                List<Employee>  companyStaff = new ArrayList<Employee>();
                List<String> locations=new ArrayList<String>();
            	TreeSet<String> elist=EmployeeService.fetchDepartments(companyStaff,locations);

            	System.out.println("returned " + elist.size() + " departments " + companyStaff.size() + " employees");
            	requestScope("departmentList", elist);
        /*<!-- ***************************************************************************************************************************************************************************************** -->*/
        /*<!-- ***************************************************************************************************************************************************************************************** -->*/

		ObjectMapper mapper=new ObjectMapper();
		ArrayNode node=mapper.valueToTree(companyStaff);
		//System.out.println(node.toString());
    	requestScope("companyStaffList", node.toString());
    	String message=AssetService.getMessage();
    	requestScope("AdMessage", message);
		return forward("index.jsp");
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		IndexController.location = location;
	}
}
