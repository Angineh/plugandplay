package com.tech.plugandplay.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.pretty.time.PrettyTime;

@SuppressWarnings("serial")
@Entity
@Table(name="dealflowlist")
public class DealflowList implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="LIST_NAME",unique=true)
	private String listName;
	@Column(name="ARCHIVE")
	private Boolean archive;
	@Temporal(TemporalType.TIMESTAMP) /* Tells Hibernate its of time / date format */
	private Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public Boolean getArchive() {
		return archive;
	}
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	public String getTime(){
        PrettyTime t = new PrettyTime();
        String timestamp = t.format(time);
        return timestamp;
    }
	public void setTime(Date time){
        this.time = time;
    }
	
}
