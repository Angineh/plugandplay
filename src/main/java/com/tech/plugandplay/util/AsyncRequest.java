package com.tech.plugandplay.util;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.bicloud.fyre.FyreRestHelper;
import com.tech.plugandplay.hibernate.HibernateUtil;
import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Network;
import com.tech.plugandplay.model.Nodes;

public class AsyncRequest {
	
	private static Log log = LogFactory.getLog(AsyncRequest.class);
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	public static void asyncCreate(JSONObject request, Cluster cluster, String apikey) {
		@SuppressWarnings("unused")
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> 
		Create(request, cluster, apikey), executor);
	}
	
	public static void asyncReload(Cluster cluster, String apikey) {
		@SuppressWarnings("unused")
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> 
		Reload(cluster, apikey), executor);
	}
	
	public static void asyncDelete(Cluster cluster, String apikey) {
		@SuppressWarnings("unused")
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> 
		Delete(cluster, apikey), executor);
	}
	
	public static void Create(JSONObject request, Cluster cluster, String apikey){
				
		log.info("Creating nodes with request id:: "+cluster.getClusterrequestid());
		String status_url = null;
		try {
			status_url = FyreRestHelper.createCluster(request.toString(), cluster.getUsername(), apikey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			for(Nodes node : cluster.getNodes()){
				node.setNodeStatus(Constants.NodeStatus.ERROR);
			}
			cluster.setStatus(Constants.ClusterStatus.CREATE_NODES_FAILED);
			HibernateUtil.updateCluster(cluster);
			log.fatal(e.getMessage(), e.fillInStackTrace());	
		}
		cluster.setStatus(Constants.ClusterStatus.CREATING_NODES);
		HibernateUtil.updateCluster(cluster);
		String status = FyreRestHelper.getStatus(status_url, cluster.getUsername(), apikey);
		while(status.equalsIgnoreCase("building") || status.equalsIgnoreCase("new")){
			log.info("Cluster creating status is "+status);
			log.info("Sleeping for 1 minute.");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				for(Nodes node : cluster.getNodes()){
					node.setNodeStatus(Constants.NodeStatus.ERROR);
				}
				cluster.setStatus(Constants.ClusterStatus.CREATE_NODES_FAILED);
				HibernateUtil.updateCluster(cluster);
				log.fatal(e.getMessage(), e.fillInStackTrace());	
			}
			status = FyreRestHelper.getStatus(status_url, cluster.getUsername(), apikey);
		}
		log.info("Completed status is "+status);
		if(status.equalsIgnoreCase("completed")){
			JSONObject clusterInfo = FyreRestHelper.getClusterInfo(cluster.getClusterprefix(), cluster.getUsername(), apikey);
			JSONArray arr = clusterInfo.getJSONArray(cluster.getClusterprefix());
			for (int i = 0; i < arr.length(); i++) {
				for(Nodes node : cluster.getNodes()){
					if(arr.getJSONObject(i).getString("node").equalsIgnoreCase(node.getHostname())){			
						Network network = node.getNetwork();
						network.setPublicIpAddress(arr.getJSONObject(i).getString("publicip"));
						network.setPrivateIpAddress(arr.getJSONObject(i).getString("privateip"));
						node.setNetwork(network);
						node.setNodeStatus(Constants.NodeStatus.ACTIVE);
						node.setUserName("root");
						node.setPassword("P@ssw0rd");
					}
				}
			}
			cluster.setStatus(Constants.ClusterStatus.CREATED_NODES);
			HibernateUtil.updateCluster(cluster);
		}else{
			for(Nodes node : cluster.getNodes()){
				node.setNodeStatus(Constants.NodeStatus.ERROR);
			}
			cluster.setStatus(Constants.ClusterStatus.CREATE_NODES_FAILED);
			HibernateUtil.updateCluster(cluster);
		}	
	}
	
	public static void Reload(Cluster cluster, String apikey){
		log.info("Reloading cluster with request id: "+cluster.getClusterrequestid());
		
		String status_url = FyreRestHelper.reloadCluster(cluster.getClusterprefix(), cluster.getUsername(), apikey);
		for(Nodes node : cluster.getNodes()){
			node.setNodeStatus(Constants.NodeStatus.PENDING);
		}
		cluster.setStatus(Constants.ClusterStatus.RELOADING_NODES);
		HibernateUtil.updateCluster(cluster);
		log.info(status_url);
		
		String status = FyreRestHelper.getStatus(status_url, cluster.getUsername(), apikey);
		while(status.equalsIgnoreCase("reloading") || status.equalsIgnoreCase("new")){
			log.info("Cluster reload status is "+status);
			log.info("Sleeping for 1 minute.");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				for(Nodes node : cluster.getNodes()){
					node.setNodeStatus(Constants.NodeStatus.ERROR);
				}
				cluster.setStatus(Constants.ClusterStatus.RELOAD_NODES_FAILED);
				log.fatal(e.getMessage(), e.fillInStackTrace());
			}
			status = FyreRestHelper.getStatus(status_url, cluster.getUsername(), apikey);
		}
		
		log.info("Completed status is "+status);
		
		if(status.equalsIgnoreCase("completed")){
			log.info("Reload of cluster with request id "+cluster.getClusterrequestid()+" was successful!");
			for(Nodes node : cluster.getNodes()){
				node.setNodeStatus(Constants.NodeStatus.ACTIVE);
			}
			cluster.setStatus(Constants.ClusterStatus.RELOADED_NODES);
			HibernateUtil.updateCluster(cluster);
		}else{
			log.info("Reload of cluster with request id "+cluster.getClusterrequestid()+" failed.");
			for(Nodes node : cluster.getNodes()){
				node.setNodeStatus(Constants.NodeStatus.ERROR);
			}
			cluster.setStatus(Constants.ClusterStatus.RELOAD_NODES_FAILED);
		}
		
	}
	
	public static void Delete(Cluster cluster, String apikey){
		log.info("Deleting cluster with request id: "+cluster.getClusterrequestid());
		String status_url = FyreRestHelper.deleteCluster(cluster.getClusterprefix(), cluster.getUsername(), apikey);
		cluster.setStatus(Constants.ClusterStatus.DELETING_NODES);
		HibernateUtil.updateCluster(cluster);
		String status = FyreRestHelper.getStatus(status_url, cluster.getUsername(), apikey);
		
		while(status.equalsIgnoreCase("pending delete")){
			log.info("Delete cluster status is "+status);
			log.info("Sleeping for 1 minute.");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				for(Nodes node : cluster.getNodes()){
					node.setNodeStatus(Constants.NodeStatus.ERROR);
				}
				cluster.setStatus(Constants.ClusterStatus.DELETE_NODES_FAILED);
				HibernateUtil.updateCluster(cluster);
				log.fatal(e.getMessage(), e.fillInStackTrace());	
			}
			status = FyreRestHelper.getStatus(status_url, cluster.getUsername(), apikey);
		}
		
		log.info("Delete completed status is "+status);
		if(status.equalsIgnoreCase("completed")){
			for(Nodes node : cluster.getNodes()){
				node.setNodeStatus(Constants.NodeStatus.DELETED);
			}
			cluster.setStatus(Constants.ClusterStatus.DELETED_NODES);
			HibernateUtil.updateCluster(cluster);
		} else {
			for(Nodes node : cluster.getNodes()){
				node.setNodeStatus(Constants.NodeStatus.ERROR);
			}
			cluster.setStatus(Constants.ClusterStatus.DELETE_NODES_FAILED);
			HibernateUtil.updateCluster(cluster);
		}
	}
		
		
	
	
}
