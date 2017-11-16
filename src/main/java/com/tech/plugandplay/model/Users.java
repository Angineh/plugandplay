package com.tech.plugandplay.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.ocpsoft.pretty.time.PrettyTime;

@SuppressWarnings("serial")
@Entity
@FilterDefs({
	@FilterDef(name = "byName", parameters=@ParamDef( name = "nameFilter", type = "string")),
	@FilterDef(name = "byEmail", parameters=@ParamDef( name = "emailFilter", type = "string")),
	@FilterDef(name = "byRole", parameters=@ParamDef( name = "roleFilter", type = "string"))
})
@Filters({ 
	@Filter(name="byName", condition="NAME like :nameFilter"),
	@Filter(name="byEmail", condition="EMAIL like :emailFilter"),
	@Filter(name="byRole", condition="ROLE like :roleFilter"),
})
@Indexed
@Table(name="users")
public class Users implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="NAME")
	private String name;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="EMAIL")
	private String email;
	@Column(name="PASSWORD")
	private String password;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="ROLE")
	private String role; //admin, venture, global, user, startup, corporation
	@Column(name="PNP_OFFICE") //comma separated list of offices
	private String pnpOffice;
	@Column(name="API_KEY")
	private String api_key;
	@Column(name="REF_ID")
	private int ref_id;	
	@Column(name="VERIFIED")
	private boolean verified;
	@Temporal(TemporalType.TIMESTAMP) /* Tells Hibernate its of time / date format */
	private Date time;
	@Transient
	private String token;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public int getRef_id() {
		return ref_id;
	}
	public void setRef_id(int ref_id) {
		this.ref_id = ref_id;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getTime(){
        PrettyTime t = new PrettyTime();
        String timestamp = t.format(time);
        return timestamp;
    }
	public void setTime(Date time){
        this.time = time;
    }
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPnpOffice() {
		return pnpOffice;
	}
	public void setPnpOffice(String pnpOffice) {
		this.pnpOffice = pnpOffice;
	}	
	
}
