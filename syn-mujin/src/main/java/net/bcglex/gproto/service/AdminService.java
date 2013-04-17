package net.bcglex.gproto.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.slim3.datastore.Datastore;
import com.google.appengine.api.datastore.Key;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;

//import net.bcglex.gproto.controller.Query;
import net.bcglex.gproto.meta.AdmUserMeta;
import net.bcglex.gproto.meta.DomainMeta;
import net.bcglex.gproto.meta.EmployeeMeta;
//import net.bcglex.gproto.meta.AdminMeta;
import net.bcglex.gproto.model.AdInfo;
import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Asset;
import net.bcglex.gproto.model.Domain;
import net.bcglex.gproto.model.Employee;
import net.bcglex.gproto.model.Admin;

public class AdminService {

	
	public static String getAdminEmail(){
		Key key=Datastore.createKey(Admin.class, "Admin");
		try {
			return Datastore.get(Admin.class, key).getAdminEmail();
		} catch(Exception e){
			String adminEmail="syn-mujinadm@4any.bc-glex.net";
			setAdminEmail(adminEmail);
			return adminEmail;
		}
	}
	
	public static void setAdminEmail(String adminEmail){
		Key key=Datastore.createKey(Admin.class, "Admin");
		Admin ai=new Admin();
		ai.setKey(key);
		ai.setAdminEmail(adminEmail);
		Datastore.put(ai);
	}

}
