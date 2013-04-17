package net.bcglex.gproto.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.gdata.client.Service;
import com.google.gdata.client.contacts.ContactsService;

import net.bcglex.gproto.controller.AdminController;
import net.bcglex.gproto.meta.AdmUserMeta;
import net.bcglex.gproto.meta.DomainMeta;
import net.bcglex.gproto.meta.EmployeeMeta;
import net.bcglex.gproto.model.AdInfo;
import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Domain;

public class AssetService {
	//google apps final account
	//こちらのsuperadminは処理では使用していない。
	private static final String SUPERADMINEMAIL="";
//	private static final String SUPERADMINEMAIL="syn-mujinadm@g-proto.bc-glex.net";
//	private static final String VALIDDOMAIN="4any.bc-glex.net";
//	private static final String ADMINPASSWORD="I2!!8Owk";
	//google apps account - could not access
	//todo: ドメインごとの管理者をdbから取ってくるようにする
	private static String ADMINEMAIL="";
	private static String VALIDDOMAIN="";
	private static String ADMINPASSWORD="";
//	private static final String ADMINEMAIL="syn-mujinadm@g-proto.bc-glex.net";
//	private static final String VALIDDOMAIN="g-proto.bc-glex.net";
//	private static final String ADMINPASSWORD="hkcQmmS$71";
	//google app engine account
//	private static final String ADMINEMAIL="syn.mujin@gmail.com";
//	private static final String VALIDDOMAIN="gmail.com";
//	private static final String ADMINPASSWORD="f1QvNnmW";

	// 引数emailからDomainテーブルを参照し、マッチする登録アドレスを取得
	// そのアドレスのドメインをメンバ変数VALIDDOMAIN、アドレスをADMINEMAILに登録
	// また戻り値はVALIDDOMAIN
	public static String setAssets(String email){
		System.out.println(email);
		NamespaceManager.set("root");
		List<Domain> domains=getDomains();
		for(Domain dmn:domains){
			String adr=dmn.getEmail();
			String[] parts=adr.split("@");
//			NamespaceManager.set(parts[1]);
			if(email.endsWith("@"+parts[1])==true){
				ADMINEMAIL=dmn.getEmail();
				VALIDDOMAIN=parts[1];
				System.out.println(adr+" "+VALIDDOMAIN+" "+ADMINEMAIL);
//				return VALIDDOMAIN;
//				break;
			}
		}
		return VALIDDOMAIN;
	}

	// HiroshiFukasawa add
	// AssetServiceのメンバ変数VALIDDOMAINを引数に設定
	public static void setAssetsVALIDDOMAIN(String email){
		System.out.println(email);
			String adr=email;
			String[] parts=adr.split("@");
//			NamespaceManager.set(parts[1]);
			ADMINEMAIL=email;
			VALIDDOMAIN=parts[1];
	}

	public static Boolean isValidSuperAdmin(String email){
		return email.equals(SUPERADMINEMAIL);
	}

	public static Boolean isValidAdmin(String email){
		boolean isvalid=email.equals(ADMINEMAIL);
		return isvalid;
	}

	public static Boolean isValidUser(String email){
//○		if(isValidAdmin(email)) return true;
		List<AdmUser> users=AdmUserService.getUsers();
		for(AdmUser u : users){
			if(u.getEmail().equals(email)){
				return true;
			}
		}
		return false;
//		return email.matches(".*@"+VALIDDOMAIN);
	}

	public static String getAdminpassword() {
		return ADMINPASSWORD;
	}

	public static boolean addDomain(String email, String password){
		NamespaceManager.set("root");
		Key key=Datastore.createKey(Domain.class, email);
		Domain dom=new Domain();
		dom.setKey(key);
		dom.setEmail(email);
		dom.setPassword(password);
		key=null;
		try {
			String adr=dom.getEmail();
			String[] parts=adr.split("@");

			//domaintableのdomain追加
			key=Datastore.put(dom);
			NamespaceManager.set(parts[1]);

			// HiroshiFukasawa add
			// AssetServiceのメンバ変数VALIDDOMAINに追加ドメイン名を登録（AdmUserService.syncUsersで使用するため）
//			AssetService.setAssets(email);
			AssetService.setAssetsVALIDDOMAIN(email);

			ContactsService service=new ContactsService("syn-mujin");
			System.out.println(NamespaceManager.get());
			System.out.println("email,password"+email+" "+password);
    		service.setUserCredentials(email, password);
    		AdmUserService.syncUsers(service);
    		//

		} catch (Exception e){
			e.printStackTrace();
			System.out.println("param error");
		}
		return (key!=null)? true:false;
	}

	public static List<Domain> getDomains(){
		NamespaceManager.set("root");
		return Datastore.query(DomainMeta.get()).asList();
	}

	public static String getDomain(){
		return VALIDDOMAIN;
	}

	public static void deleteDomain(String email){
		NamespaceManager.set("root");
		Key key=Datastore.createKey(Domain.class, email);
		Datastore.delete(key);

		String[] parts=email.split("@");
		key=Datastore.createKey(AdmUser.class, parts[1]);
		List<Key> keys=Datastore.query(key).asKeyList();
		Datastore.delete(keys);

		NamespaceManager.set(parts[1]);
		keys = Datastore.query(EmployeeMeta.get()).asKeyList();
		Datastore.delete(keys);
	}

	public static void setMessage(String message){
		Key key=Datastore.createKey(AdInfo.class, "adinfo");
		AdInfo ai=new AdInfo();
		ai.setKey(key);
		ai.setMessage(message);
		Datastore.put(ai);
	}

	public static String getMessage(){
		Key key=Datastore.createKey(AdInfo.class, "adinfo");
		try {
			return Datastore.get(AdInfo.class, key).getMessage();
		} catch(Exception e){
			String msg="いらっしゃいませ。担当者を検索するには画面をタッチしてください。";
			setMessage(msg);
			return msg;
		}
	}
}
