package com.tech.plugandplay.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tech.plugandplay.util.Constants;

public class Initialize {
	
	public static Cluster initCluster(String inputJSON, String username, String provider){
		JSONObject json = new JSONObject(inputJSON);
		Cluster cluster = new Cluster();
		cluster.setClusterrequestid(json.getInt("requestId"));
		cluster.setUsername(username);
		cluster.setProvider(provider);
		cluster.setClusteraction(Constants.Actions.CREATE);
		cluster.setNodes(initNodes(inputJSON));
		String clusterprefix = cluster.getNodes().get(0).getHostname();
		clusterprefix = clusterprefix.substring(0, clusterprefix.length() - 6);
		cluster.setClusterprefix(clusterprefix);
		cluster.setStatus(Constants.ClusterStatus.CREATE_NODES_RECEIVED);
		return cluster;
	}
	
	public static List<Nodes> initNodes(String inputJSON){
		JSONArray json = new JSONObject(inputJSON).getJSONArray("nodes");
		List<Nodes> nodes = new ArrayList<Nodes>();
		for (int i = 0; i < json.length(); i++) {
			  Nodes node = new Nodes();
			  node.setHostname(json.getJSONObject(i).getString("hostname"));
			  node.setDomain(json.getJSONObject(i).getString("domain"));
			  if(json.getJSONObject(i).getString("instanceType").contains("VM")){
				  node.setInstanceType("virtual_server");  
			  }else if(json.getJSONObject(i).getString("instanceType").contains("BM")){
				  node.setInstanceType("physical_server");
			  }			  
			  node.setMachineSpecId(json.getJSONObject(i).getString("machineSpecId"));
			  node.setNodeStatus(Constants.NodeStatus.PENDING);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
			  Date date = new Date();
			  node.setCreationTime(sdf.format(date));
			  Network network = new Network();
			  network.setPublicIpAddress("Pending");
			  network.setPrivateIpAddress("Pending");
			  node.setNetwork(network);
			  nodes.add(node);
		}
		return nodes;
	}

}
