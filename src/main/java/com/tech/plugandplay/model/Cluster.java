package com.tech.plugandplay.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("serial")
@Entity
@Table(name="cluster")
public class Cluster implements Serializable {
	
	@Id
	@Column(name="CLUSTERREQUESTID")
	private int clusterrequestid;
	@Column(name="PROVIDER")
	private String provider;
	@Column(name="USERNAME")
	private String username;
	@Column(name="CLUSTERACTION")
	private String clusteraction;
	@Column(name="CLUSTERPREFIX")
	private String clusterprefix;
	@Column(name="STATUS")
	private String status;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
    @Fetch(value = FetchMode.SUBSELECT)
    @ForeignKey(name = "nodes_fk", inverseName = "nodes_inverse_fk")
	private List<Nodes> nodes;
	
	public int getClusterrequestid() {
		return clusterrequestid;
	}
	public void setClusterrequestid(int clusterrequestid) {
		this.clusterrequestid = clusterrequestid;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClusteraction() {
		return clusteraction;
	}
	public void setClusteraction(String clusteraction) {
		this.clusteraction = clusteraction;
	}	
	public String getClusterprefix() {
		return clusterprefix;
	}
	public void setClusterprefix(String clusterprefix) {
		this.clusterprefix = clusterprefix;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Nodes> getNodes() {
		return nodes;
	}
	public void setNodes(List<Nodes> nodes) {
		this.nodes = nodes;
	}

	
}
