package net.bcglex.gproto.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.bcglex.gproto.meta.AdmUserMeta;
import net.bcglex.gproto.meta.DomainMeta;
import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Asset;
import net.bcglex.gproto.model.Domain;
import net.bcglex.gproto.model.Employee;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;

public class AdmUserService {

	public static List<Domain> getDomains(){
		return Datastore.query(DomainMeta.get()).asList();
	}

	public static boolean addDomain(String email){
		Key key=Datastore.createKey(Domain.class, email);
		Domain domain=new Domain();
		domain.setKey(key);

		if(Datastore.put(domain)==null) return false;
		return true;
	}

	public static List<AdmUser> getUsers(){
		NamespaceManager.set("root");
			return Datastore.query(AdmUserMeta.get()).asList();
	}

	public static AdmUser getUserByEmail(String email){
		NamespaceManager.set("root");
		Key key=Datastore.createKey(AdmUser.class, email);
		try {
			return Datastore.get(AdmUser.class, key);
		} catch(Exception e) {
			return null;
		}
	}

	public static boolean addUser(String email){
//		System.out.println("addUser in");
		Employee emp;
		try{
			emp=EmployeeService.getEmployeeByEmail(email);
		} catch (Exception e){
			return false;
		}
		if(emp==null) return false;
		NamespaceManager.set("root");
		Key key=Datastore.createKey(AdmUser.class, email);
		AdmUser usr=new AdmUser();
		usr.setKey(key);
		usr.setEmail(email);
		String[] parts=email.split("@");
		usr.setDomain(parts[1]);
		usr.setLocation(emp.getLocation());
		usr.setName(emp.getSname()+" "+emp.getFname());
		if(Datastore.put(usr)==null) return false;
		return true;
	}

	public static void deleteUser(String id){
		NamespaceManager.set("root");
		Key key=Datastore.createKey(AdmUser.class, id);
		Datastore.delete(key);
	}

	public static void syncUsers(ContactsService myService) throws MalformedURLException{

//		System.out.println("syncUser");
		Key key=Datastore.createKey(Asset.class, "uniqueasset");
		long utime;
		try {
			utime=Datastore.get(Asset.class, key).getUtime();
		} catch(Exception e){
			utime=0;
		}

		String validdomain=AssetService.getDomain();
		System.out.println("domain is "+validdomain);
		URL feedUrl = new URL("https://www.google.com/m8/feeds/profiles/domain/"+validdomain+"/full");
		ContactFeed resultFeed;
		Query myQuery = new Query(feedUrl);
		  myQuery.setMaxResults(10000);
//		  ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
		try{
//			resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			resultFeed = myService.query(myQuery, ContactFeed.class);
		} catch (Exception e){
		  	System.out.println("syncUser");
		  	e.printStackTrace();
		  	return;
		}
		System.out.println(resultFeed.getTitle().getPlainText());
		DateTime dtime=resultFeed.getUpdated();
		if(dtime.getValue()>utime){
			for (ContactEntry entry : resultFeed.getEntries()) {
				if(resultFeed.getUpdated().getValue()>utime){
					EmployeeService.updateUser(entry);
				}
			}
		}
	}

	// AdmUserテーブルのlocationを更新する。
	public static void updateLocation(String location) {
		// TODO 自動生成されたメソッド・スタブ
		NamespaceManager.set("root");
//		System.out.println(UserServiceFactory.getUserService().getCurrentUser().getEmail());
		AdmUser user=AdmUserService.getUserByEmail(UserServiceFactory.getUserService().getCurrentUser().getEmail());
	    Key key=Datastore.createKey(AdmUser.class, UserServiceFactory.getUserService().getCurrentUser().getEmail());
	    user.setLocation(location);
	    Datastore.put(user);
	}
}
