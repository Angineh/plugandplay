package com.ibm.bicloud.fyre;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Nodes;

public class RequestGenerator {

	public static JSONObject newRequest(Cluster cluster, String credentials){
		
		JSONObject request = new JSONObject();
		request.put("fyre", new JSONObject().put("creds", new JSONObject().put("username", cluster.getUsername()).put("api_key", credentials)));				
		request.put("cluster_prefix", cluster.getClusterprefix());		
		request.put("clusterconfig", new JSONObject().put("instance_type", cluster.getNodes().get(0).getInstanceType()).put("platform", "x"));
		
		JSONArray nodes = new JSONArray();
		for(Nodes node : cluster.getNodes()){
			JSONObject obj = new JSONObject();
			obj.put("name", node.getHostname().substring(node.getHostname().length()-5, node.getHostname().length()));
			obj.put("os", "Redhat 6.7");
			if(node.getMachineSpecId().contains("master")){
				obj.put("publicvlan", "y");
				obj.put("privatevlan", "y");	
			}else if(node.getMachineSpecId().contains("data")){
				obj.put("publicvlan", "n");
				obj.put("privatevlan", "y");
			}
			if(node.getInstanceType().equalsIgnoreCase("virtual_server")){
				obj.put("cpu", 8);
				obj.put("memory", 24);
			}else{
				obj.put("baremetal_profile", "3650m5a");
			}
			nodes.put(obj);
		}
		request.put(cluster.getClusterprefix(), nodes);
		return request;
	}
}
