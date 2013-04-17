package net.bcglex.gproto.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;

import net.bcglex.gproto.model.Employee;
import net.bcglex.gproto.service.AdmUserService;
import net.bcglex.gproto.service.AdminService;
import net.bcglex.gproto.service.AssetService;
import net.bcglex.gproto.service.EmployeeService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.UserDefinedField;
import com.google.gdata.data.contacts.YomiName;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.Organization;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.Where;
import com.google.gdata.util.ServiceException;

public class AdminController extends Controller {
	static boolean gotData=false;

	public Navigation setUp() {
		String pms="";
		Enumeration en = request.getParameterNames();
		while(en.hasMoreElements()){
			pms+=en.nextElement()+",";
		}	System.out.println(pms);
		return null;
	}

	// 顧客管理ユーザ
//	private static final String VALIDDOMAIN = "4any.bc-glex.net";
	private static final String ADMINEMAIL = "syn-mujinadm@4any.bc-glex.net";

//	private static final String ADMINEMAIL = AdminService.getAdminEmail();

//	private static final String ADMINPASSWORD = "I2!!8Owk";
//	private static final String VALIDDOMAIN="g-proto.bc-glex.net";
//	private static final String ADMINEMAIL="syn-mujinadm@g-proto.bc-glex.net";
//	private static final String ADMINPASSWORD="hkcQmmS$71";

    @Override
    public Navigation run() throws Exception {
    	//access control

        UserService userService = UserServiceFactory.getUserService();
        String thisURL = request.getRequestURI();
        response.setContentType("text/html; charset=utf-8");
        String userEmail=null;
        String userDomain=null;

//        EmployeeService.updateUser()

//        AssetService.addDomain(ADMINEMAIL,VALIDDOMAIN);		//suemitsu
//        NamespaceManager.set(VALIDDOMAIN);
//        AdmUserService.addUser(ADMINEMAIL);		//suemitsu

        if (request.getUserPrincipal() != null) {
        	//googleアカウントにログインしている

        	userEmail = userService.getCurrentUser().getEmail();
//        	userEmail = request.getUserPrincipal().getName();
        	userDomain = AssetService.setAssets(userEmail);

        	requestScope("userDomain", userDomain);

//        	if(AssetService.isValidSuperAdmin(userEmail)==true){
////				requestScope("userRole", "superadmin");
//				requestScope("userRole", "admin");
////		        return forward("admin.jsp");
//        	}


//    		if(AssetService.isValidUser(userEmail)){
////    			if(AssetService.isValidAdmin(userEmail)){
////    				if(AdmUserService.getUserByEmail(userEmail)==null){
////    					AdmUserService.addUser(userEmail);
////    				}
////    				requestScope("adminEmail", userEmail);
////    				requestScope("userRole", "admin");
////    			} else {
////        			requestScope("userRole", "user");
////    			}
////add
//
//    		} else {
//    			//管理者/userのアカウントでない
//    			System.out.println("email didn't match admin email");
////    			//add
////    			requestScope("userRole", "admin");	//temp
//
////temp    			return redirect(userService.createLogoutURL(thisURL));	//強制的にログアウトさせ、ログインリンクを表示。
//    		}
			// 顧客管理ユーザであるか
			if(userEmail.equals(ADMINEMAIL)){
				requestScope("userRole", "admin");
				NamespaceManager.set("root");
			// 各社管理ユーザであるか
			}else if(AssetService.isValidUser(userEmail)){
				requestScope("userRole", "user");
				NamespaceManager.set(userDomain);
			}else{
				//管理者/userのアカウントでない
//    			System.out.println("email didn't match admin/user email");
////    			//add
////    			requestScope("userRole", "admin");	//temp
response.getWriter().println("<p>Please <a href=\"" +
                userService.createLoginURL(thisURL) + "\">sign in</a>.</br>ログインできるユーザではありません。</p>");
return null;
//				return redirect(userService.createLogoutURL(thisURL));	//強制的にログアウトさせ、ログインリンクを表示。
			}


        } else {
        	//googleアカウントにログインしていない
			//ログインリンクの表示
            response.getWriter().println("<p>Please <a href=\"" +
                                         userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");
            return null;
        }

        //main
    	String action=param("action");
    	action=(action==null)? "":action;
    	if (action.equals("init")){
//        	EmployeeService.deleteAll();
//
//        	ServletContext context =  ServletContextLocator.get();
//        	String fullPath = context.getRealPath("/WEB-INF/directory.csv");
//    		EmployeeService.initData(fullPath);
//
    	} else if(action.equals("initData")){
        	EmployeeService.deleteAll();
    		return forward("admin.jsp");
    	} else if(action.equals("initWithApps")){
    		//apps directoryで初期化

			//for test purpose
//			ContactsService service=new ContactsService("reception");
//			service.setUserToken(this.accessToken);	//アクセストークンでは通らず
//    	    service.setUserCredentials(userEmail, AssetService.getAdminpassword());
//			printAllProfiles(service);
//			if (true) return null;

    		ContactsService service=new ContactsService("syn-mujin");

//			service.setUserToken(this.accessToken);	//アクセストークンでは通らず
//    	    service.setUserCredentials(userEmail, AssetService.getAdminpassword());
    		System.out.println("email="+userEmail+" password="+param("password"));
    	    service.setUserCredentials(userEmail, param("password"));
//			printAllProfiles(service);
//			if (true) return null;
    		AdmUserService.syncUsers(service);
    	}
    	String department=(String) param("department");
    	if (department!=null){
    		String section=(String) param("section");
    		if (section!=null){
    			String subsection=(String) param("subsection");
    			if (subsection!=null){
    				requestScope("employeeList", EmployeeService.getEmployees(department, section, subsection));
    			}
    	    	List<Employee> sectionStaff=new ArrayList<Employee>();
    			requestScope("subsectionList", EmployeeService.getSubSections(department, section, sectionStaff));
    	    	requestScope("sectionStaffList", sectionStaff);
    		}
        	List<Employee> departmentStaff=new ArrayList<Employee>();
    		requestScope("sectionList", EmployeeService.getSections(department, departmentStaff));
        	requestScope("departmentStaffList", departmentStaff);
    	}
    	List<Employee> companyStaff=new ArrayList<Employee>();

    	// HiroshiFukasawa mod 拠点リスト取得処理を部署一覧取得に含めるため、変更
    	List<String> locationList = new ArrayList<String>();
    	TreeSet<String> deps=EmployeeService.getDepartments(companyStaff,locationList);
    	requestScope("locationList", locationList);
//    	System.out.println("locationlist"+locationList);

//    	if(deps.isEmpty()){
//    		requestScope("status", "nodata");
//    	} else {
    		String status=param("status");
    		if(status!=null){
    			if(status.equals("emptable")){
	    			requestScope("status", "emptable");
	    		} else if(status.equals("usercontrol")){
	    			requestScope("status", "usertable");
	    		}else if(status.equals("domaincontrol")){
	    			requestScope("status", "domaintable");
	    		}
//    	}
//    		requestScope("status", "emptable");
    	}
    	requestScope("departmentList", deps);
    	requestScope("companyStaffList", companyStaff);

//    	requestScope("employeeList", EmployeeService.queryAll());
    	requestScope("actionValue", action);
    	String message=AssetService.getMessage();
    	requestScope("AdMessage", message);
        return forward("admin.jsp");
    }


    //domainのprofileを書き出す。テスト。
    public void printAllProfiles(ContactsService myService)
    	    throws ServiceException, IOException {

    	System.out.println("in printAllProfiles");
    	  // Request the feed
  	  URL feedUrl = new URL("https://www.google.com/m8/feeds/profiles/domain/"+AssetService.getDomain()+"/full");
//    	  URL feedUrl = new URL("https://www.google.com/m8/feeds/profiles/domain/"+AssetService.getDomain()+"/full");
    	//https://www.google.com/m8/feeds/contacts/domain/full
//    	  URL feedUrl = new URL("https://www.google.com/m8/contacts/domain/"+AssetService.getDomain()+"/full");
    	  ContactFeed resultFeed;
    	  try{
        	  resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
    	  } catch (Exception e){
    	    	System.out.println("error return");
    		  return;
    	  }
    	  // Print the results
  		  response.setContentType("text/html; charset=UTF-8");
       	  PrintWriter printOut=response.getWriter();
//    	  PrintStream printOut = System.out;

       	  printOut.println("<pre>");
    	  printOut.println(resultFeed.getTitle().getPlainText());
    	  for (ContactEntry entry : resultFeed.getEntries()) {

    	    	Boolean contFlag=false;
    		  if(entry.hasUserDefinedFields()){
      			System.out.println("user defined field");
      			List<UserDefinedField> field=entry.getUserDefinedFields();
      			if(field!=null){
    				printOut.println(field.toString());
      			}
    		  }

    		//読みが入力されていれば読み出せるのか？
    		if(entry.hasYomiName()){
    			System.out.println("yomi");
    			YomiName yname=entry.getYomiName();
    			if(yname!=null){
    				printOut.println(yname.toString());
    			}
    		}
    		//電話番号が入力させていれば読み出せるのか？
    		if(entry.hasPhoneNumbers()){
    			System.out.println("phone");
    			List<PhoneNumber> phone=entry.getPhoneNumbers();
    			if(phone!=null){
    				contFlag=true;
        			System.out.println(phone.toString());
    				printOut.println(phone.toString());
    				for(PhoneNumber pn : phone){
        				printOut.println("\t\tphone: "+pn.getPhoneNumber());
    				}
    			}
    		}
    		//Organizationが入力させていれば読み出せるのか？
    		if(entry.hasOrganizations()){
    			System.out.println("Organization");
    			 List<Organization> org=entry.getOrganizations();
    			if(org!=null){
    				contFlag=true;
        			System.out.println(org.toString());
    				printOut.println(org.toString());
    				for(Organization o : org){
        				printOut.println("\t\tdept: "+o.getOrgDepartment());
        				printOut.println("\t\text: "+o.getExtensions().toString());
        				printOut.println("\t\tname: "+o.getOrgName());
    				}
    			}
    		}
    		//whereが入力させていれば読み出せるのか？
    		if(entry.hasWhere()){
    			System.out.println("where");
    			Where location=entry.getWhere();
    			if(location!=null){
    				contFlag=true;
        			System.out.println(location.toString());
    			}
    		}
    		if(contFlag==false) continue;
    	    if (entry.hasName()) {
    	      Name name = entry.getName();
    	      if (name.hasFullName()) {
    	        String fullNameToDisplay = name.getFullName().getValue();
    	        if (name.getFullName().hasYomi()) {
    	          fullNameToDisplay += " (" + name.getFullName().getYomi() + ")";
    	        }
    	        printOut.println("\t\t" + fullNameToDisplay);
    	      } else {
    	        printOut.println("\t\t (no full name found)");
    	      }
    	      if (name.hasNamePrefix()) {
    	        printOut.println("\t\t" + name.getNamePrefix().getValue());
    	      } else {
    	        printOut.println("\t\t (no name prefix found)");
    	      }
    	      if (name.hasGivenName()) {
    	        String givenNameToDisplay = name.getGivenName().getValue();
    	        if (name.getGivenName().hasYomi()) {
    	          givenNameToDisplay += " (" + name.getGivenName().getYomi() + ")";
    	        }
    	        printOut.println("\t\t" + givenNameToDisplay);
    	      } else {
    	        printOut.println("\t\t (no given name found)");
    	      }
    	      if (name.hasAdditionalName()) {
    	        String additionalNameToDisplay = name.getAdditionalName().getValue();
    	        if (name.getAdditionalName().hasYomi()) {
    	          additionalNameToDisplay += " (" + name.getAdditionalName().getYomi() + ")";
    	        }
    	        printOut.println("\t\t" + additionalNameToDisplay);
    	      } else {
    	        printOut.println("\t\t (no additional name found)");
    	      }
    	      if (name.hasFamilyName()) {
    	        String familyNameToDisplay = name.getFamilyName().getValue();
    	        if (name.getFamilyName().hasYomi()) {
    	          familyNameToDisplay += " (" + name.getFamilyName().getYomi() + ")";
    	        }
    	        printOut.println("\t\t" + familyNameToDisplay);
    	      } else {
    	        printOut.println("\t\t (no family name found)");
    	      }
    	      if (name.hasNameSuffix()) {
    	        printOut.println("\t\t" + name.getNameSuffix().getValue());
    	      } else {
    	        printOut.println("\t\t (no name suffix found)");
    	      }
    	    } else {
    	      printOut.println("\t (no name found)");
    	    }

    	    printOut.println("Email addresses:");
    	    for (Email email : entry.getEmailAddresses()) {
    	      printOut.print(" " + email.getAddress());
    	      if (email.getRel() != null) {
    	        printOut.print(" rel:" + email.getRel());
    	      }
    	      if (email.getLabel() != null) {
    	        printOut.print(" label:" + email.getLabel());
    	      }
    	      if (email.getPrimary()) {
    	        printOut.print(" (primary) ");
    	      }
    	      printOut.print("\n");
    	    }

    	    printOut.println("IM addresses:");
    	    for (Im im : entry.getImAddresses()) {
    	      printOut.print(" " + im.getAddress());
    	      if (im.getLabel() != null) {
    	        printOut.print(" label:" + im.getLabel());
    	      }
    	      if (im.getRel() != null) {
    	        printOut.print(" rel:" + im.getRel());
    	      }
    	      if (im.getProtocol() != null) {
    	        printOut.print(" protocol:" + im.getProtocol());
    	      }
    	      if (im.getPrimary()) {
    	        printOut.print(" (primary) ");
    	      }
    	      printOut.print("\n");
    	    }

    	    printOut.println("Extended Properties:");
    	    for (ExtendedProperty property : entry.getExtendedProperties()) {
    	      if (property.getValue() != null) {
    	        printOut.println("  " + property.getName() + "(value) = " +
    	            property.getValue());
    	      } else if (property.getXmlBlob() != null) {
    	        printOut.println("  " + property.getName() + "(xmlBlob)= " +
    	            property.getXmlBlob().getBlob());
    	      }
    	    }

    	    Link photoLink = entry.getContactPhotoLink();
    	    String photoLinkHref = photoLink.getHref();
    	    printOut.println("Photo Link: " + photoLinkHref);

    	    if (photoLink.getEtag() != null) {
    	      printOut.println("Profile Photo's ETag: " + photoLink.getEtag());
    	    }

    	    printOut.println("Profile's ETag: " + entry.getEtag());
    	  }
    	  printOut.println("</pre>");
    	}

}
