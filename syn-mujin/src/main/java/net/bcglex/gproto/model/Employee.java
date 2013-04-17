package net.bcglex.gproto.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String email;	//as a primary key
    private String location;
    private String fname;
    private String sname;
    private String kanaFname;
    private String kanaSname;
    private String subsection;
    private String birthday;
    private String department;
    private String section;
    private String image;
    private Boolean needToShow;


    private String kanaName;

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

	public String getSubsection() {
		return subsection;
	}

	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phonenumber1) {
		this.phoneNumber1 = phonenumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phonenumber2) {
		this.phoneNumber2 = phonenumber2;
	}

	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	public void setPhoneNumber3(String phonenumber3) {
		this.phoneNumber3 = phonenumber3;
	}


	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getKanaSname() {
		return kanaSname;
	}

	public void setKanaSname(String kanaSname) {
		this.kanaSname = kanaSname;
	}

	public String getKanaFname() {
		return kanaFname;
	}

	public void setKanaFname(String kanaFname) {
		this.kanaFname = kanaFname;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public String getKanaName() {
		return kanaName;
	}


	public Boolean getNeedToShow() {
		return needToShow;
	}

	public void setNeedToShow(Boolean needToShow) {
		this.needToShow = needToShow;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
