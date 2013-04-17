package net.bcglex.gproto.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import net.bcglex.gproto.meta.AccessTokenMeta;
import net.bcglex.gproto.meta.AdInfoMeta;
import net.bcglex.gproto.meta.AdmUserMeta;
import net.bcglex.gproto.meta.AdminMeta;
import net.bcglex.gproto.meta.AssetMeta;
import net.bcglex.gproto.meta.DomainMeta;
import net.bcglex.gproto.meta.EmployeeMeta;
import net.bcglex.gproto.model.AdmUser;
import net.bcglex.gproto.model.Employee;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.slim3.datastore.AscCriterion;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelQuery;

import au.com.bytecode.opencsv.CSVReader;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.OrgDepartment;
import com.google.gdata.data.extensions.Organization;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.Where;

public class EmployeeService {

	private static String targetLocation;

	public static void updateUser(ContactEntry entry){
		String address="no address";
		String extention="no extention";
		String extention1="";
		String extention2="";
		String extention3="";
		String fname="no first name";
		String sname="no second name";
		String dept="";
		String location="no location";
		String kananame="";	//suemitsu

	    //email
	    for (Email email : entry.getEmailAddresses()) {
	    	address=email.getAddress();
	    	if(address.endsWith(AssetService.getDomain())){
	    		break;
	    	}
	    }
    	if(address.endsWith(AssetService.getDomain())==false){
    		return;
    	}
    	if(address.equals("syn-mujinadm@4any.bc-glex.net")){
    		System.out.println("hit admin");
    	}
	    //電話番号 2つめが内線番号
		if(entry.hasPhoneNumbers()){
			System.out.println("phone");
			List<PhoneNumber> phones=entry.getPhoneNumbers();
			if(phones!=null && phones.size()>1){
    			System.out.println(phones.toString());
    			extention1=phones.get(1).getPhoneNumber();
			}else if(phones!=null && phones.size()>2){
    			extention2=phones.get(2).getPhoneNumber();
			}else if(phones!=null && phones.size()>3){
    			extention3=phones.get(3).getPhoneNumber();
			}
//			System.out.println("phone");
//			List<PhoneNumber> phones=entry.getPhoneNumbers();
//			if(phones!=null && phones.size()>1){
//   			System.out.println(phones.toString());
//    			extention=phones.get(1).getPhoneNumber();
//			}
		}

		//Organization
		if(entry.hasOrganizations()){
			System.out.println("Organization");
			 List<Organization> org=entry.getOrganizations();
			if(org!=null){
    			System.out.println(org.toString());
    			OrgDepartment odept=org.get(0).getOrgDepartment();
    			if(odept!=null){
        			dept=odept.getValue();
    			}
    			Where where=org.get(0).getWhere();
    			if(where!=null){
        			location=where.getValueString();
    			}
			}
		}

		//where
//		if(entry.hasWhere()){
//			System.out.println("where");
//			Where where=entry.getWhere();
//			if(where!=null){
//    			location=where.toString();
//			}
//		}

		//name
	    if (entry.hasName()) {
  	      Name name = entry.getName();
  	      if (name.hasGivenName()) {
  	        String givenNameToDisplay = name.getGivenName().getValue();
  	        if (name.getGivenName().hasYomi()) {
  	          givenNameToDisplay += " (" + name.getGivenName().getYomi() + ")";
  	        }
  	        fname=givenNameToDisplay;
  	      }
  	      if (name.hasFamilyName()) {
  	        String familyNameToDisplay = name.getFamilyName().getValue();
  	        if (name.getFamilyName().hasYomi()) {
  	          familyNameToDisplay += " (" + name.getFamilyName().getYomi() + ")";
  	        }
  	        sname=familyNameToDisplay;
  	      }
  	    }


//	    //TEST
//	    System.out.println(entry.getBillingInformation());
//	    System.out.println(entry.getBirthday());
//	    System.out.println(entry.getCalendarLinks());
//	    System.out.println(entry.getContactEditPhotoLink());
//	    System.out.println(entry.getContactPhotoLink());
//	    System.out.println(entry.getDirectoryServer());
//	    System.out.println(entry.getEmailAddresses());
//	    System.out.println(entry.getEvents());
//	    System.out.println(entry.getExtendedProperties());
//	    System.out.println(entry.getExternalIds());
//	    System.out.println(entry.getGender());
//	    System.out.println(entry.getHobbies());
//	    System.out.println(entry.getImAddresses());
//	    System.out.println(entry.getInitials());
//	    System.out.println(entry.getJots());
//	    System.out.println(entry.getLanguages());
//	    System.out.println(entry.getMaidenName());
//	    System.out.println(entry.getMileage());
//	    System.out.println(entry.getName());
//	    System.out.println(entry.getNickname());
//	    System.out.println(entry.getOccupation());
//	    System.out.println(entry.getOrganizations());
//	    System.out.println(entry.getPhoneNumbers());
//	    System.out.println(entry.getPostalAddresses());
//	    System.out.println(entry.getPriority());
//	    System.out.println(entry.getRelations());
//	    System.out.println(entry.getSensitivity());
//	    System.out.println(entry.getShortName());
//	    System.out.println(entry.getStructuredPostalAddresses());
//	    System.out.println(entry.getSubject());
//	    System.out.println(entry.getUserDefinedFields());
//	    System.out.println(entry.getWebsites());
//	    System.out.println(entry.getWhere());
////	    System.out.println(entry.getAdaptedEntry());
////	    System.out.println(entry.getAdaptor());
//	    System.out.println(entry.getAdaptors());
//	    System.out.println(entry.getAuthors());
//	    System.out.println(entry.getCanEdit());
//	    System.out.println(entry.getCategories());
//	    System.out.println(entry.getContent());
////	    System.out.println(entry.getContentHandlerInfo());
//	    System.out.println(entry.getContributors());
//	    System.out.println(entry.getEdited());
//	    System.out.println(entry.getEditLink());
//	    System.out.println(entry.getEtag());
////	    System.out.println(entry.getHandler());
//	    System.out.println(entry.getHtmlLink());
//	    System.out.println(entry.getId());
//	    System.out.println(entry.getKind());
////	    System.out.println(entry.getLink());
//	    System.out.println(entry.getLinks());
//	    System.out.println(entry.getLinks());
//	    System.out.println(entry.getMediaEditLink());
////	    System.out.println(entry.getPlainTextContent());	//カナ
//	    System.out.println(entry.getPubControl());
//	    System.out.println(entry.getPublished());
//	    System.out.println(entry.getResumableEditMediaLink());
//	    System.out.println(entry.getRights());
//	    System.out.println(entry.getSelectedFields());
//	   //System.out.println(entry.getSelf());
//	    System.out.println(entry.getSelfLink());
//	    System.out.println(entry.getService());
//	    System.out.println(entry.getSource());
//	    System.out.println(entry.getSummary());
////	    System.out.println(entry.getTextContent());
//	    System.out.println(entry.getTitle());
//	    System.out.println(entry.getUpdated());
//	    System.out.println(entry.getVersionId());
//
////	    System.out.println(entry.getExtension());
////	    System.out.println(entry.getExtensionDescription());
////	    System.out.println(entry.getExtensionHandler());
//	    System.out.println(entry.getExtensions());
////	    System.out.println(entry.getManifest());
////	    System.out.println(entry.getRepeatingExtension());
//	    System.out.println(entry.getRepeatingExtensions());
//	    System.out.println(entry.getXmlBlob());
//	    System.out.println(entry.getExtensionLocalName());
//	    System.out.println(entry.getExtensionNamespace());
//	    //TEST

		//name
//	    String kanaName = entry.getPlainTextContent();
//	    System.out.println(entry.getPlainTextContent());	//カナ test

	    Employee emp=new Employee();
//	    NamespaceManager.set(AssetService.getDomain());
	    Key key=Datastore.createKey(Employee.class, address);
	    emp.setKey(key);
	    emp.setEmail(address);
	    if(dept.isEmpty()==false)
	    	emp.setDepartment(dept);
	    if(location.isEmpty()==false)
	    	emp.setLocation(location);
//	    if(extention.isEmpty()==false)
//	    	emp.setBirthday(extention);
	    if(extention1.isEmpty()==false)
	    	emp.setPhoneNumber1(extention1);
	    if(extention2.isEmpty()==false)
	    	emp.setPhoneNumber2(extention2);
	    if(extention3.isEmpty()==false)
	    	emp.setPhoneNumber3(extention3);
	    if(fname.isEmpty()==false)
	    	emp.setFname(fname);
	    if(sname.isEmpty()==false)
	    	emp.setSname(sname);
//	    if(kanaName.isEmpty()==false)
//	    	emp.setKanaName(kanaName);

	    // HiroshiFukasawa add 社員テーブルの表示フラグはデフォルトtrue（表示）とする処理を追加
	    emp.setNeedToShow(true);

	    Datastore.put(emp);
	}

	private static void storeData(CSVReader reader) throws NullPointerException, IOException{
		String [] nextLine;
		List<Employee> emplist=new ArrayList<Employee>();
		while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
			Employee emp=new Employee();
			Key key=Datastore.createKey(Employee.class, nextLine[1]);
			emp.setKey(key);
			emp.setLocation(nextLine[0]);
			emp.setEmail(nextLine[1]);
			emp.setSname(nextLine[2]);
			emp.setFname(nextLine[3]);
			emp.setKanaSname(nextLine[4]);
			emp.setKanaFname(nextLine[5]);
			emp.setSubsection(nextLine[6]);
			emp.setBirthday(nextLine[7]);
			emp.setDepartment(nextLine[8]);
			emp.setSection(nextLine[9]);
			emp.setImage(nextLine[10]);
			if(nextLine.length>11){
				emp.setNeedToShow(Boolean.valueOf(nextLine[11]));
			} else {
				emp.setNeedToShow(true);
			}
			emplist.add(emp);
//	        System.out.println(nextLine[0] + nextLine[1] + "etc...");
	    }
		Datastore.put(emplist);
	}

	public static void initData(byte[] csv) throws Exception{
		System.out.println("initData with byte[] size: "+csv.length);
		InputStream input = new ByteArrayInputStream(csv);
		CSVReader reader=new CSVReader(new InputStreamReader(input, "UTF-8"), ',', '\"', 1);
		storeData(reader);
	}

	public static void initData(String csv) throws Exception{
		FileInputStream input=new FileInputStream(csv);
		CSVReader reader=new CSVReader(new InputStreamReader(input, "UTF-8"), ',', '\"', 1);
		storeData(reader);
	}

	public static void deleteAll(){
		// 全データ削除
		NamespaceManager.set("root");
		List <Key> keys = Datastore.query(EmployeeMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdmUserMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdInfoMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(DomainMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AssetMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AccessTokenMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdminMeta.get()).asKeyList();
		Datastore.delete(keys);

		NamespaceManager.set("4any.bc-glex.net");
		keys = Datastore.query(EmployeeMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdmUserMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdInfoMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(DomainMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AssetMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AccessTokenMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdminMeta.get()).asKeyList();
		Datastore.delete(keys);

		NamespaceManager.set("g-proto.bc-glex.net");
		keys = Datastore.query(EmployeeMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdmUserMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdInfoMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(DomainMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AssetMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AccessTokenMeta.get()).asKeyList();
		Datastore.delete(keys);
		keys = Datastore.query(AdminMeta.get()).asKeyList();
		Datastore.delete(keys);
	}

	public static List<Employee> queryAll() {
		return Datastore.query(EmployeeMeta.get()).asList();
	}

	public static Employee getEmployeeByEmail(String email){
//		NamespaceManager.set(AssetService.getDomain());
		AssetService.setAssetsVALIDDOMAIN(email);
		NamespaceManager.set(AssetService.getDomain());
		System.out.println("Namespace"+AssetService.getDomain());
		Key key=Datastore.createKey(Employee.class, email);
		return Datastore.get(Employee.class, key);
	}

	// HiroshiFukasawa mod 拠点リスト取得処理を部署一覧取得に含めるため、変更
	private static TreeSet<String> getDepartments(List<Employee> companyStaff, List<String> locations,Boolean hide){
		NamespaceManager.set(AssetService.getDomain());
		TreeSet<String> strSet=new TreeSet<String>();
		TreeSet<String> strSet_location=new TreeSet<String>();
		EmployeeMeta e=EmployeeMeta.get();
		List<Employee> elist=null;
		if(hide){
			String location=getTargetLocation();
			elist=Datastore.query(e).filter(e.needToShow.equal(true), e.location.equal(location)).asList();
		} else {
			elist=Datastore.query(e).asList();
		}
		System.out.println("Found "+elist.size()+" entries");
		for(Employee emp : elist){

			String location = emp.getLocation();
			location=(location==null)? "":location;
			strSet_location.add(location);

			String department=emp.getDepartment();

			// HiroshiFukasawa mod 部署の入力がない場合本部所属とする修正
//			department=(department==null)? "":department;
//			if(department.isEmpty()){
//				companyStaff.add(emp);
//			} else {
//				strSet.add(emp.getDepartment());
//			}
			department=(department==null)? "本部所属":department;
			strSet.add(department);
			// mod end

		}
		for(String loc:strSet_location){
			locations.add(loc);
		}
		return strSet;
	}

	// HiroshiFukasawa mod 拠点リスト取得処理を部署一覧取得に含めるため、変更
	public static TreeSet<String> getDepartments(List<Employee> companyStaff,List<String> location){
		return getDepartments(companyStaff,location, false);
	}

	// HiroshiFukasawa mod 拠点リスト取得処理を部署一覧取得に含めるため、変更
	public static TreeSet<String> fetchDepartments(List<Employee> companyStaff,List<String> location){
		return getDepartments(companyStaff,location, true);
	}

	private static TreeSet<String> getSections(String department, List<Employee> departmentStaff, Boolean hide){
		TreeSet<String> strSet=new TreeSet<String>();
		EmployeeMeta e=EmployeeMeta.get();
		List<Employee> elist=null;
		if(hide){
			String location=getTargetLocation();
			elist=Datastore.query(e).filter(e.department.equal(department), e.needToShow.equal(true), e.location.equal(location)).asList();
		} else {
			elist=Datastore.query(e).filter(e.department.equal(department)).asList();
		}
		for(Employee em : elist){
			String section=em.getSection();
			if(section.isEmpty()){
				if(departmentStaff!=null) departmentStaff.add(em);
			} else {
				strSet.add(em.getSection());
			}
		}
		return strSet;
	}

	public static TreeSet<String> getSections(String department, List<Employee> departmentStaff){
		return getSections(department, departmentStaff, false);
	}

	public static TreeSet<String> fetchSections(String department, List<Employee> departmentStaff){
		return getSections(department, departmentStaff, true);
	}

	private static TreeSet<String> getSubSections(String department, String section, List<Employee> sectionStaff, Boolean hide){
		TreeSet<String> strSet=new TreeSet<String>();
		EmployeeMeta e=EmployeeMeta.get();
		List<Employee> elist=null;
		if(hide){
			String location=getTargetLocation();
			elist=Datastore.query(e).filter(e.department.equal(department), e.section.equal(section), e.needToShow.equal(true), e.location.equal(location)).asList();
		} else {
			elist=Datastore.query(e).filter(e.department.equal(department), e.section.equal(section)).asList();
		}

		for(Employee em : elist){
			String subsection=em.getSubsection();
			if(subsection.isEmpty()){
				sectionStaff.add(em);
			} else {
				strSet.add(em.getSubsection());
			}
		}
		return strSet;
	}

	public static TreeSet<String> getSubSections(String department, String section, List<Employee> sectionStaff){
		return getSubSections(department, section, sectionStaff, false);
	}

	public static TreeSet<String> fetchSubSections(String department, String section, List<Employee> sectionStaff){
		return getSubSections(department, section, sectionStaff, true);
	}

	public static List<Employee> getEmployeesByName(String sname, String fname){
		EmployeeMeta e=EmployeeMeta.get();
		ModelQuery<Employee> equery=null;
		if(sname!=null){
			if(fname!=null){
				equery=Datastore.query(e).filter(e.sname.equal(sname), e.fname.equal(fname));
			} else {
				equery=Datastore.query(e).filter(e.sname.equal(sname));
			}
		} else if(fname!=null){
			equery=Datastore.query(e).filter(e.fname.equal(fname));
		} else {
			return null;
		}
		return equery.sort(e.kanaSname.asc).asList();
	}

	private static List<Employee> getEmployees(String department, String section, String subsection, Boolean hide, String[] sort){
		// HiroshiFukasawa add 拠点絞込み対応
		// ログインユーザの情報から表示対象拠点を検索しこれをsetTargetLocation()しておく。
		// この拠点に属する社員のみ一覧に表示する。
		AdmUser user=AdmUserService.getUserByEmail(UserServiceFactory.getUserService().getCurrentUser().getEmail());
		setTargetLocation(user.getLocation());
//		System.out.println("location"+user.getLocation());
		// add end

		EmployeeMeta e=EmployeeMeta.get();
		List<Employee> elist=null;
		ModelQuery<Employee> equery=null;
		NamespaceManager.set(AssetService.getDomain());
		if(department==null){
			if(hide){
				String location=getTargetLocation();
				equery=Datastore.query(e)
						.filter(e.needToShow.equal(true), e.location.equal(location));
//				System.out.println("e.location"+e.location);
			} else {
				equery=Datastore.query(e);
			}
		} else {
			if(section==null){
				if(hide){
					String location=getTargetLocation();
					equery=Datastore.query(e)
							.filter(e.department.equal(department), e.needToShow.equal(true), e.location.equal(location));
				} else {
					equery=Datastore.query(e)
							.filter(e.department.equal(department));
				}
			} else {
				if(subsection==null){
					if(hide){
						String location=getTargetLocation();
						equery=Datastore.query(e)
								.filter(e.department.equal(department), e.section.equal(section), e.needToShow.equal(true), e.location.equal(location));
					} else {
						equery=Datastore.query(e)
								.filter(e.department.equal(department), e.section.equal(section));
					}
				} else {
					if(hide){
						String location=getTargetLocation();
						equery=Datastore.query(e)
								.filter(e.department.equal(department), e.section.equal(section), e.subsection.equal(subsection), e.needToShow.equal(true), e.location.equal(location));
					} else {
						equery=Datastore.query(e)
								.filter(e.department.equal(department), e.section.equal(section), e.subsection.equal(subsection));
					}
				}
			}
		}
		AscCriterion sortCr=null;
		if(sort!=null){
			if(sort[0].equals("kanaFname")){
				sortCr=(AscCriterion)((sort[1].equals("ASC"))? e.kanaFname.asc:e.kanaFname.desc);
			} else if(sort[0].equals("kanaSname")){
				sortCr=(AscCriterion)((sort[1].equals("ASC"))? e.kanaSname.asc:e.kanaSname.desc);
			}
			elist=equery.sort(sortCr).asList();
		} else {
			elist=equery.asList();
		}
		return elist;
	}

	public static List<Employee> getEmployeesSorted(String department, String section, String subsection, String sortColumn, String sortType){
		String[] sort={sortColumn, sortType};
		// HiroshiFukasawa mod 拠点絞込み処理のため変更
//		return getEmployees(department, section, subsection, false, sort);
		return getEmployees(department, section, subsection, true, sort);
	}


	public static List<Employee> getEmployees(String department, String section, String subsection){
		return getEmployees(department, section, subsection, false, null);
	}

	public static List<Employee> fetchEmployees(String department, String section, String subsection){
		return getEmployees(department, section, subsection, true, null);
	}

	public static void setNeedToShow(Key key, Boolean show){
		Employee emp=Datastore.get(Employee.class, key);

//		EmployeeMeta e=EmployeeMeta.get();
//		Employee emp=Datastore.query(e)
//				.filter(e.key.equal(key))
//				.asSingle();
		emp.setNeedToShow(show);
		Datastore.put(emp);
	}

	public static String employeeJson(List<Employee> empList){
		ObjectMapper mapper=new ObjectMapper();
		ArrayNode rootNode=mapper.createArrayNode();

		for(Employee e : empList){
			ObjectNode subNode=mapper.valueToTree(e);
			rootNode.add(subNode);
		}
		//can access first name of index at i by rootNode[i]["fname"]...
		return rootNode.toString();
	}

	/*<!-- ***************************************************************************************************************************************************************************************** -->*/
	/*<!-- ***************************************************************************************************************************************************************************************** -->*/
		public static String fetchEmployeeByInitials(String pattern)
		{
			EmployeeMeta   e        = EmployeeMeta.get();
			List<Employee> elist    = new ArrayList<Employee>();
			String         location = getTargetLocation();
	/*
			System.out.println(location);

			List<Employee> tmplist = Datastore.query(e)
								   . filter(e.kanaSname.startsWith(pattern), e.needToShow.equal(true), e.location.equal(location))
								   . sort(e.kanaSname.asc).asList();
	*/
			List<Employee> tmplist = Datastore.query(e)
					   			   . filter(e.sname.startsWith(pattern), e.location.equal(location))
					   			   . sort(e.sname.asc).asList();

			elist.addAll(tmplist);

			return employeeJson(elist);
		}
	/*<!-- ***************************************************************************************************************************************************************************************** -->*/
	/*<!-- ***************************************************************************************************************************************************************************************** -->*/
		public static String fetchEmployeeByInitials_department(String pattern)
		{
			EmployeeMeta   e        = EmployeeMeta.get();
			List<Employee> elist    = new ArrayList<Employee>();
			String         location = getTargetLocation();

			List<Employee> tmplist;

			if (pattern.equals("dummy"))
			{
				tmplist = Datastore.query(e)
						. filter(e.department.equal(null), e.location.equal(location))
						. sort(e.sname.asc).asList();
			}
			else
			{
				tmplist = Datastore.query(e)
						. filter(e.department.equal(pattern), e.location.equal(location))
						. sort(e.sname.asc).asList();
			}

			elist.addAll(tmplist);

			return employeeJson(elist);
		}
	/*<!-- ***************************************************************************************************************************************************************************************** -->*/
	/*<!-- ***************************************************************************************************************************************************************************************** -->*/


	public static String getTargetLocation() {
		return targetLocation;
	}

	public static void setTargetLocation(String targetLocation) {
		EmployeeService.targetLocation = targetLocation;
	}

}
