package net.bcglex.gproto.model;

import java.io.Serializable;
import java.util.Calendar;

import com.google.appengine.api.datastore.*;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String access_token;
    private String token_type;
    private int expires_in;
    private long timeSaved;
    
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
        AccessToken other = (AccessToken) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String token) {
		if(token.length()<10){
			this.token_type = token;
		} else {
			this.access_token=token;
		}
	}

	public int getExpires_in() {
		Calendar cal=Calendar.getInstance();
		int diff=(int)(cal.getTime().getTime()-this.timeSaved);
		return expires_in-diff;
	}

	public void setExpires_in(int second) {
		System.out.println("expires_in called "+second);
		Calendar cal=Calendar.getInstance();
		this.timeSaved=cal.getTime().getTime();
		this.expires_in = second;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String tokenType) {
		if(tokenType.length()<10){
			this.token_type = tokenType;
		} else {
			this.access_token=tokenType;
		}
	}

	public long getTimeSaved() {
		return timeSaved;
	}

	public void setTimeSaved(long timeSaved) {
		this.timeSaved = timeSaved;
	}

}
