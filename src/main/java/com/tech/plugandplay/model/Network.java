package com.tech.plugandplay.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="network")
public class Network implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="PRIVATEIPADDRESS")
	private String privateIpAddress;
	@Column(name="PUBLICIPADDRESS")
	private String publicIpAddress;
	@Column(name="DEFAULTGATEWAY")
	private String defaultGateway;
	@Column(name="DEFAULTINTERFACE")
	private String defaultInterface;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrivateIpAddress() {
		return privateIpAddress;
	}
	public void setPrivateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
	}
	public String getPublicIpAddress() {
		return publicIpAddress;
	}
	public void setPublicIpAddress(String publicIpAddress) {
		this.publicIpAddress = publicIpAddress;
	}
	public String getDefaultGateway() {
		return defaultGateway;
	}
	public void setDefaultGateway(String defaultGateway) {
		this.defaultGateway = defaultGateway;
	}
	public String getDefaultInterface() {
		return defaultInterface;
	}
	public void setDefaultInterface(String defaultInterface) {
		this.defaultInterface = defaultInterface;
	}

	
}
