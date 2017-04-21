package com.tech.plugandplay.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@SuppressWarnings("serial")
@Entity
@Table(name="top100")
public class Top100 implements Serializable {
	
	@Id
	@Column(name="VENTURE_ID")
	private int venture_id;
	@Column(name="ORDER_ID")
	private int order;
	@Column(name="LIST_NAME")
	private String listName;
	
	public int getVenture_id() {
		return venture_id;
	}
	public void setVenture_id(int venture_id) {
		this.venture_id = venture_id;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	
	

}
