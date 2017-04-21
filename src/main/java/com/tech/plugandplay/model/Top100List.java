package com.tech.plugandplay.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="top100list")
public class Top100List implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="LIST_NAME",unique=true)
	private String listName;
	@Column(name="ARCHIVE")
	private Boolean archive;
	
	
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
	
}
