package com.tech.plugandplay.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.providers.jackson.Formatted;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.bicloud.fyre.RequestGenerator;
import com.tech.plugandplay.hibernate.HibernateUtil;
import com.tech.plugandplay.model.Business;
import com.tech.plugandplay.model.Cluster;
import com.tech.plugandplay.model.Initialize;
import com.tech.plugandplay.model.Nodes;
import com.tech.plugandplay.model.Top100;
import com.tech.plugandplay.model.Ventures;
import com.tech.plugandplay.util.AsyncRequest;
import com.tech.plugandplay.util.Constants;

@Path("/v1")
public class RestService {
	
	private static Log log = LogFactory.getLog(RestService.class);
	
	@GET
    @Path("/ventures/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getAllVentures() {
		
    	List<Ventures> ventures = HibernateUtil.getAllVentures();
       	if(!ventures.isEmpty()){
    		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any companies.").build();
    	}        
    }
	
	@GET
    @Path("/top100/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop100() {
		
    	List<Top100> top100 = HibernateUtil.getAllTop100();
       	if(!top100.isEmpty()){
       		StringBuilder ids = new StringBuilder();
       		for(Top100 venture: top100){
       			ids.append(venture.getVenture_id());
       			ids.append(",");
       		} 		
       		List<Ventures> ventures = HibernateUtil.getVentureTop100(ids.toString().substring(0, ids.toString().length()-1));
       		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any top 100.").build();
    	}        
    }
	
	@DELETE
	@Path("/top100/delete")
	@Consumes("application/json")
	@Produces("application/json")
	@Formatted
    public Response deleteFromTop100(String json){
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		Top100 deleteMe = HibernateUtil.getTop100(body.getInt("id"));
		if(deleteMe == null){
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity("Could not delete top 100!").build();
		}
		HibernateUtil.deleteVentureTop100(body.getInt("id"));
	    HibernateUtil.deleteTop100(deleteMe);
		return Response.ok(deleteMe).build();
		
	}
	
	@POST
    @Path("/top100/move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response moveTop100(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		
		Top100 current = HibernateUtil.getTop100(body.getInt("id"));
		int order = body.getInt("order");
		List<Top100> top100list = HibernateUtil.getAllTop100();
		if(order < 1 || order > 100 || order == current.getOrder() || order > top100list.size()){
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString("The order value "+order+" is not valid! Please try again.");
			log.info(jsonInString);
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();	
		}
		
	    for(int i = current.getOrder(); i < top100list.size(); i++){
	    	Top100 tmp = top100list.get(i);
	    	tmp.setOrder(tmp.getOrder()-1);
	    	top100list.set(i-1, tmp);
	    }
	    top100list.remove(top100list.size()-1);
	    for(int i = order - 1 ; i < top100list.size(); i++){
	    	Top100 tmp = top100list.get(i);
	    	tmp.setOrder(tmp.getOrder()+1);
	    	top100list.set(i, tmp);
	    } 
	    current.setOrder(order);
	    top100list.add(order-1, current);
	    for(Top100 list : top100list){
	    	HibernateUtil.updateTop100(list);
	    }
		
		return Response.ok(current).build();
	}
	
	@GET
    @Path("/ventures/portfolio")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getPortfolioCompanies() {
		
    	List<Ventures> ventures = HibernateUtil.getPortfolioCompanies();
       	if(!ventures.isEmpty()){
    		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any companies.").build();
    	}        
    }
	
	@GET
    @Path("/companies/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getAllCompanies() {
		
    	List<Ventures> ventures = HibernateUtil.getAllVentures();
       	if(!ventures.isEmpty()){
    		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any companies.").build();
    	}        
    }
	
	@GET
    @Path("/ventures/{id}")
    @Produces("application/json")
	@Formatted
    public Response getVentureById(@PathParam("id") int id){
		
		Ventures venture = HibernateUtil.getVenture(id);
    	System.out.println("Company Info");
    	if(venture != null){
    		return Response.ok(venture).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
		
	}
	
	@POST
    @Path("/ventures/addtop100")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response addTop100(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		List<Top100> top100list = HibernateUtil.getAllTop100();
		ObjectMapper mapper = new ObjectMapper();
		if(top100list.size()==100){
			String jsonInString = mapper.writeValueAsString(Constants.GenericErrorMessages.EXCEEDED_SIZE);
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
		}
		//Check to see if part of top100 already
		if(HibernateUtil.getTop100(body.getInt("id")) != null){
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(mapper.writeValueAsString("Top 100 venture with id "+body.getInt("id")+" already exists!")).build();
		}
		Top100 top100 = new Top100();
		top100.setOrder(top100list.size()+1);
		top100.setVenture_id(body.getInt("id"));
		HibernateUtil.addTop100(top100);
		Ventures venture = HibernateUtil.getVenture(body.getInt("id"));
		venture.setTop100(top100);
		HibernateUtil.updateVenture(venture);
		return Response.ok(top100).build();
	}
	
	@POST
    @Path("/ventures/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response newVenture(String json) {
		
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		JSONArray fields = body.getJSONObject("form_response").getJSONObject("definition").getJSONArray("fields");
		JSONArray answers = body.getJSONObject("form_response").getJSONArray("answers");
		
		Ventures venture = new Ventures();
		venture.setAdvantage("");
		venture.setB2bb2c("");
		venture.setBackground("");
		venture.setBlurb("");
		venture.setCaseStudy("");
		venture.setCity("");
		venture.setComments("");
		venture.setCompanyName("");
		venture.setCompetition("");
		venture.setContactName("");
		venture.setEmail("");
		venture.setEmployees("");
		venture.setFounded("");
		venture.setLocation("");
		venture.setMaterials("");
		venture.setPartnerInterests("");
		venture.setPhoneNumber("");
		venture.setPnpContact("");
		venture.setStage("");
		venture.setTags("");
		venture.setTotalMoneyRaised("");
		venture.setVerticals("");
		venture.setWebsite("");
		venture.setPortfolio(new Boolean(false));
		//String timestamp = null;
		String companyName = "";
		String blurb = "";
		String verticals = "";
		String website = "";
		String pnpContact = "";
		String contactName = "";
		String email = "";
		String phoneNumber = "";
		String totalMoneyRaised = "";
		String stage = "";
		String b2bb2c = "";
		String employees = "";
		String location = "";
		String city = "";
		String competition = "";
		String advantage = "";
		String background = "";
		String founded = "";
		String partnerInterests = "";
		String caseStudy = "";
		String comments = "";
		String tags = "";
		String materials = "";
		
		for (int i = 0; i < fields.length(); i++) {
			if(fields.getJSONObject(i).getString("title").contains("Select some tags")){
				tags = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Example of a case study")){
				caseStudy = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("who from PnP")){
				pnpContact = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Name of Company")){
				companyName = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Website")){
				website = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Stage of Company")){
				stage = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Founders' Background")){
				background = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Industrial Applications")){
				verticals = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Any other information or comments")){
				comments = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Email contact")){
				email = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Total Money Raised")){
				totalMoneyRaised = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Specify name of the Country")){
				city = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Competitive Advantage")){
				advantage = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("company founded")){
				founded = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Primary Contact")){
				contactName = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Blurb")){
				blurb = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Closest competition")){
				competition = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("end customer")){
				b2bb2c = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("partners that you would")){
				partnerInterests = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Contact Number")){
				phoneNumber = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Number of Employees")){
				employees = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Where is your HQ")){
				location = fields.getJSONObject(i).getString("id");
			}
		}
		
		for (int i = 0; i < answers.length(); i++) {
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(tags)){
				List<String> list = new ArrayList<String>();
				for(int j = 0; j < answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").length(); j++){
				    list.add(answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").getString(j));
				}
				venture.setTags(String.join(",",list));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(caseStudy)){
				venture.setCaseStudy(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(pnpContact)){
				venture.setPnpContact(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(companyName)){
				venture.setCompanyName(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(website)){
				venture.setWebsite(answers.getJSONObject(i).getString("url"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(stage)){
				venture.setStage(answers.getJSONObject(i).getJSONObject("choice").getString("label"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(background)){
				venture.setBackground(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(verticals)){
				List<String> list = new ArrayList<String>();
				for(int j = 0; j < answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").length(); j++){
				    list.add(answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").getString(j));
				}
				venture.setVerticals(String.join(",",list));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(comments)){
				venture.setComments(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(email)){
				venture.setEmail(answers.getJSONObject(i).getString("email"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(totalMoneyRaised)){
				venture.setTotalMoneyRaised(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(city)){
				venture.setCity(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(advantage)){
				venture.setAdvantage(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(founded)){
				venture.setFounded(answers.getJSONObject(i).getString("date"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(contactName)){
				venture.setContactName(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(blurb)){
				venture.setBlurb(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(competition)){
				venture.setCompetition(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(b2bb2c)){
				List<String> list = new ArrayList<String>();
				for(int j = 0; j < answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").length(); j++){
				    list.add(answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").getString(j));
				}
				venture.setB2bb2c(String.join(",",list));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(partnerInterests)){
				venture.setPartnerInterests(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(partnerInterests)){
				venture.setPartnerInterests(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(phoneNumber)){
				venture.setPhoneNumber(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(phoneNumber)){
				venture.setPhoneNumber(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(employees)){
				venture.setEmployees(answers.getJSONObject(i).getString("text"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(location)){
				venture.setLocation(answers.getJSONObject(i).getJSONObject("choice").getString("label"));
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		venture.setTimestamp(sdf.format(time));

		venture = HibernateUtil.newVenture(venture);
		return Response.ok(venture).build();
		
	}

	
/*	@GET
    @Path("/company/{id}")
    @Produces("application/json")
	@Formatted
    public Response getCompanyById(@PathParam("id") int id){
		
		Ventures company = HibernateUtil.getCompany(id);
    	System.out.println("Company Info");
    	if(company != null){
    		return Response.ok(company).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
		
	}*/
	
	@GET
    @Path("/business/{id}")
    @Produces("application/json")
	@Formatted
    public Response getBusinessById(@PathParam("id") int id){
		
		Business business = HibernateUtil.getBusiness(id);
    	System.out.println("Company Info");
    	if(business != null){
    		return Response.ok(business).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
		
	}
	
	@GET
    @Path("/businesses/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getAllBusinesses() {
		
    	List<Business> businesess = HibernateUtil.getAllBusinesses();
       	if(!businesess.isEmpty()){
    		return Response.ok(businesess).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any companies.").build();
    	}        
    }
	
	
	// CREATE CLUSTER
	@POST
    @Path("/nodes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Formatted
	// http://localhost:8080/bicloud/v1/hw/nodes?placeOrder=true/false
	// default true
    public Response createNodes(String inputJSON,
    		@HeaderParam("provider") String provider,
    		@HeaderParam("user") String user,
    		@HeaderParam("credentials") String credentials, // SL API for softlayer
    		@DefaultValue("true") @QueryParam("placeOrder") String placeOrder) 
	{
		String jsonInString = null;
		log.debug("Received: user: " + user + " credentials: " + credentials + " provider:" + provider);
    	// Error checking
		try {
	    	
			Response errorCheck = errorChecking(inputJSON, provider, user, credentials);
			if(errorCheck != null){
				return errorCheck;
			}
			
			Cluster cluster = Initialize.initCluster(inputJSON, user, provider);
			
			JSONObject request = RequestGenerator.newRequest(cluster, credentials);
			cluster = HibernateUtil.newCluster(cluster);
			
			// call asynchronously
			AsyncRequest.asyncCreate(request, cluster, credentials);

            log.debug(cluster.getStatus());
            return Response.status(Constants.HTTPCodes.ACCEPTED).entity(cluster.getStatus()).build();
            /*return Response.status(Constants.HTTPCodes.ACCEPTED).entity(request.toString()).build();*/
    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonInString = e.getMessage();
        	log.fatal(e.getMessage(), e.fillInStackTrace());
		}
    	
		log.error(jsonInString);
        return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
		
	}
	
	// RELOAD CLUSTER
	// http://localhost:8080/bicloud/v1/hw/nodes/reload
	@PUT
	@Path("/nodes/reload")
	@Consumes("application/json")
	@Produces("application/json")
	@Formatted
	// We take entire JSON from User, Who knows what the user wants to changes? Like OS, then for every server, we need new OS
	// OS was say Redhat linux 6.0, user chooses to change 7.0, we need these inputs
	public Response reloadNodes(String inputJSON,
			@HeaderParam("provider") String provider,
    		@HeaderParam("user") String user,
    		@HeaderParam("credentials") String credentials) // SL API for softlayer)
    {
		String jsonInString = null;
        try
        {
        	log.debug("Received: user: " + user + " credentials: " + credentials + " provider:" + provider);
        	// Error checking
        	Response errorCheck = errorChecking(inputJSON, provider, user, credentials);
			if(errorCheck != null){
				return errorCheck;
			}       	
			JSONObject json = new JSONObject(inputJSON);
			int clusterrequestid = json.getInt("requestId");
			Cluster cluster = HibernateUtil.getCluster(clusterrequestid);
			cluster.setStatus(Constants.ClusterStatus.RELOAD_NODES_RECEIVED);
			cluster.setClusteraction(Constants.Actions.RELOAD);
			HibernateUtil.updateCluster(cluster);
        	// call asynchronously
			AsyncRequest.asyncReload(cluster, credentials);
			
            log.debug(cluster.getStatus());
            return Response.status(Constants.HTTPCodes.ACCEPTED).entity(cluster.getStatus()).build();
        } 
        catch (IOException e)
        {
        	jsonInString = e.getMessage();
        	log.fatal(e.getMessage(), e.fillInStackTrace());
        }
        log.error(jsonInString);
        return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
    }
	
	// DELETE CLUSTER
	// http://localhost:8080/bicloud/v1/hw/nodes
	@DELETE
	@Path("/nodes")
	@Consumes("application/json")
	@Produces("application/json")
	@Formatted
    public Response deleteNodes(String inputJSON,
			@HeaderParam("provider") String provider,
    		@HeaderParam("user") String user,
    		@HeaderParam("credentials") String credentials) {
		String jsonInString = null;
        try
        {
        	log.debug("Received: user: " + user + " credentials: " + credentials + " provider:" + provider);
        	// Error checking
        	Response errorCheck = errorChecking(inputJSON, provider, user, credentials);
			if(errorCheck != null){
				return errorCheck;
			}
			JSONObject json = new JSONObject(inputJSON);
			int clusterrequestid = json.getInt("requestId");
			Cluster cluster = HibernateUtil.getCluster(clusterrequestid);
			cluster.setStatus(Constants.ClusterStatus.DELETE_NODES_RECEIVED);
			cluster.setClusteraction(Constants.Actions.DELETE);
			HibernateUtil.updateCluster(cluster);
        	
			// call asynchronously
			AsyncRequest.asyncDelete(cluster, credentials);

            log.debug(cluster.getStatus());
            return Response.status(Constants.HTTPCodes.ACCEPTED).entity(cluster.getStatus()).build();
        } 
        catch (IOException e)
        {
        	jsonInString = e.getMessage();
        	log.fatal(e.getMessage(), e.fillInStackTrace());
        }
        log.error(jsonInString);
        return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
    }
	
	// Return the status of the request (If acquisition is complete, return array of node details. 
	// If it is still being worked on return the status accordingly)
	// http://localhost:8080/bicloud/v1/hw/nodes/requestId
	@GET
    @Path("/nodes/{requestId}")
    @Produces("application/json")
	@Formatted
    public Response nodesStatusWithRequestId(
    								@PathParam("requestId") String requestId,
    								@HeaderParam("provider") String provider,
    								@HeaderParam("user") String user,
    								@HeaderParam("credentials") String credentials)
	{
		long lStartTime = System.currentTimeMillis();
		String jsonInString = null;
        try
        {
        	log.debug("Received: user: " + user + " credentials: " + credentials + " provider:" + provider);
        	// Error checking
        	Response errorCheck = errorChecking(requestId, provider, user, credentials);
			if(errorCheck != null){
				return errorCheck;
			}
        	// synchronous call
    		Cluster cluster = HibernateUtil.getCluster(Integer.parseInt(requestId));
    		// TIME START
    		long lEndTime = System.currentTimeMillis();
    		long milliseconds = lEndTime - lStartTime;
    		log.info("nodesStatusWithRequestId "+requestId+" took: milliseconds: " + milliseconds + " seconds: " + (milliseconds / 1000.0));
    		// TIME END
           return Response.status(Constants.HTTPCodes.OK).entity(cluster).build();
        } 
        catch (IOException e)
        {
        	jsonInString = e.getMessage();
        	log.fatal(e.getMessage(), e.fillInStackTrace());
        }
 
		log.error(jsonInString);
        return Response.status(Constants.HTTPCodes.UNAUTHORIZED).entity(jsonInString).build();
    }
	
	// Return the status of the request (If acquisition is complete, return array of node details. 
	// If it is still being worked on return the status accordingly)
	// http://localhost:8080/bicloud/v1/hw/nodeIds?nodeIds=
	@GET
	@Path("/nodeIds")
    @Produces("application/json")
	@Formatted
    public Response nodesStatusWithNodeIds(
    								@HeaderParam("provider") String provider,
    								@HeaderParam("user") String user,
    								@HeaderParam("credentials") String credentials,
    								@QueryParam("nodeIds") String nodeIds) 
	{
		long lStartTime = System.currentTimeMillis();
		String jsonInString = null;
		int lstatus = Constants.HTTPCodes.OK;
		
        try
        {
        	log.debug("Received: user: " + user + " credentials: " + credentials + " provider:" + provider);
        	// Error checking
        	Response errorCheck = errorChecking(nodeIds, provider, user, credentials);
			if(errorCheck != null){
				return errorCheck;
			}
			
			log.info("Request nodesStatusWithNodeIds: "+nodeIds);
			
			List<String> ids = Arrays.asList(nodeIds.split("\\s*,\\s*"));
			List<Nodes> nodes = new ArrayList<Nodes>();
			for(String id : ids){
				nodes.add(HibernateUtil.getNode(Integer.parseInt(id)));
			}
			
        	// SYNCHRONOUS CALL, NOTHING IS STORED IN DB; nothing to RETRIEVE
        	//jsonInString = async.nodeStatusUsingNodeIds(nodeIds, provider, user, credentials);
        	log.debug(jsonInString);
        	
    		// TIME START
    		long lEndTime = System.currentTimeMillis();
    		long milliseconds = lEndTime - lStartTime;
    		log.info("nodesStatusWithNodeIds : took: milliseconds: " + milliseconds + " seconds: " + (milliseconds / 1000.0));
    		// TIME END
    		
    		// return cluster status
            return Response.status(Constants.HTTPCodes.OK).entity(new JSONObject().put("nodes", nodes).toString()).build();
        } 

        catch (IOException e)
        {
        	jsonInString = e.getMessage();
        	log.fatal(e.getMessage(), e.fillInStackTrace());
        	lstatus = Constants.HTTPCodes.BAD_REQUEST;
        }
        
        log.error(lstatus);
        log.error(jsonInString);
        return Response.status(lstatus).entity(jsonInString).build();
    }
	
	public Response errorChecking(String inputJSON, String provider, String user, String credentials) throws IOException{
		String jsonInString;
		ObjectMapper mapper = new ObjectMapper();
		if(inputJSON == null || inputJSON.isEmpty()) {   
			jsonInString = mapper.writeValueAsString(Constants.GenericErrorMessages.INPUT_JSON);				
    		log.error(jsonInString);
    		return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
		}
    	if(provider == null || provider.isEmpty() || !provider.equalsIgnoreCase("fyre")) {
    		jsonInString = mapper.writeValueAsString(Constants.GenericErrorMessages.CURRENT_PROVIDER);
    		log.error(jsonInString);
    		return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
    	}
    	if(user == null || user.isEmpty() || credentials == null || credentials.isEmpty()) {
    		jsonInString = mapper.writeValueAsString(Constants.GenericErrorMessages.CREDENTIALS_REQUIRED);
    		log.error(jsonInString);
    		return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).build();
    	}
		return null;
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 OLD METHODS
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	*/

	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response clusterInfo(@PathParam("id") int id) {
    	//Cluster cluster = HibernateUtil.getCluster(id);
    	System.out.println("Cluster Info");    	
    	return Response.ok(id).build();
    	      
    }
	/*
	@GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response activeClusters() {
		
    	List<Cluster> clusters = HibernateUtil.getAllClusters();
    	Iterator<Cluster> i = clusters.iterator();
    	while (i.hasNext()) {
    		Cluster cluster = i.next();
    	    if(cluster.getStatus().equalsIgnoreCase("CANCELLED")){
    	    	i.remove();
   			}
    	}
    	if(!clusters.isEmpty()){
    		return Response.ok(clusters).build();
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any clusters.").build();
    	}        
    }
    
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response newCluster(@QueryParam("name") String name, @QueryParam("user_email") String user_email, @QueryParam("provider") String provider) {
    	System.out.println("New Cluster");
    	Cluster cluster = new Cluster();
    	cluster.setName(name);
    	cluster.setUser_email(user_email);
    	cluster.setProvider(provider);
    	cluster.setStatus("PREPROVISION");
    	cluster = HibernateUtil.newCluster(cluster);
        return Response.ok(cluster).build();
    }
    
    @DELETE
    @Path("/{id}/cancel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response cancel(@PathParam("id") int id, @QueryParam("user_email") String user_email, String json) {
    	System.out.println("Cancel Cluster");
    	Cluster cluster = HibernateUtil.getCluster(id);
    	if(cluster != null && cluster.getUser_email().equalsIgnoreCase(user_email)){
    		cluster.setStatus("CANCELLING");
    		HibernateUtil.updateCluster(cluster);
    		if(json != null){
    			json = json.replaceAll("CLUSTER_PREFIX", cluster.getName()+"-"+cluster.getId());
        		System.out.println(json);	
    		}    		
    		int deleteStatus = ChefUtil.chefCancel(json, cluster);
    		if (deleteStatus == 0){
    			cluster.setStatus("CANCELLED");
        		HibernateUtil.updateCluster(cluster);
    			//HibernateUtil.deleteCluster(cluster);
    			return Response.ok(cluster).build();
    		}else{
    			cluster.setStatus("CANCELLING_FAILED");
        		HibernateUtil.updateCluster(cluster);
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef cancel failed.").build();
    		}
    		
    
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " +id+ " or email "+user_email+" doesn't match cluster owner "+cluster.getUser_email()).build();
    	}
		return null;
        
    }
    
    @POST
    @Path("/{id}/boot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response boot(@PathParam("id") int id, String json) throws IOException {
    	System.out.println("Order Cluster Boot");
    	Cluster cluster = HibernateUtil.getCluster(id);
    	
    	if(cluster != null){
    		int bootStatus = 1;
    		JSONObject getType = new JSONObject(json);
    		String instance_type = null;
    		if(cluster.getProvider().equalsIgnoreCase("softlayer")){
    			instance_type = getType.getJSONObject("softlayer").getJSONObject("creds").getString("instance_type");
    		}else if(cluster.getProvider().equalsIgnoreCase("fyre")){
    			instance_type = getType.getJSONObject("clusterconfig").getString("instance_type");
    		}
    		cluster.setStatus("BOOTING");
    		cluster.setInstance_type(instance_type);
    		HibernateUtil.updateCluster(cluster);
    		json = json.replaceAll("CLUSTER_PREFIX", cluster.getName()+"-"+cluster.getId());
    		//System.out.println(json);
    		if(cluster.getProvider().equalsIgnoreCase("softlayer")){
    			bootStatus = ChefUtil.chefBoot(json, cluster);	
    		}else if(cluster.getProvider().equalsIgnoreCase("fyre")){    		
    			bootStatus = ChefUtil.chefBootFyre(json, cluster);
    		}    		
    		if (bootStatus == 0){
    			cluster.setStatus("BOOTED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.ok(cluster).build();
    		}else{
    			cluster.setStatus("BOOTING_FAILED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef boot failed.").build();
    		}
    		
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
    }
    
    @POST
    @Path("/{id}/sshkeys")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response sshKeys(@PathParam("id") int id, @QueryParam("instancetype") String instancetype, String json) throws IOException {
		return null;
    	//TODO instancetype is not needed
    	System.out.println("Order Cluster Boot SSH Keys");
    	Cluster cluster = HibernateUtil.getCluster(id);
    	if(cluster != null){
    		cluster.setStatus("BOOTING");
    		HibernateUtil.updateCluster(cluster);
    		//json = json.replaceAll("CLUSTER_PREFIX", cluster.getName()+"-"+cluster.getId());
    		int sshkeyStatus = ChefUtil.chefSshKeys(json, cluster);
    		if (sshkeyStatus == 0){
    			cluster.setStatus("BOOTED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.ok(cluster).build();
    		}else{
    			cluster.setStatus("BOOTING_FAILED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef boot failed.").build();
    		}
    		
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
    }
    
    @POST
    @Path("/{id}/install")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response install(@PathParam("id") int id, String json) throws IOException {
    	System.out.println("Order Cluster Setup");
    	Cluster cluster = HibernateUtil.getCluster(id);
    	if(cluster != null){
    		cluster.setStatus("PREPARING");
    		JSONObject getWorkload = new JSONObject(json);
    		String workload = getWorkload.getString("workload");
    		cluster.setWorkload(workload);
    		HibernateUtil.updateCluster(cluster);
    		json = json.replaceAll("CLUSTER_PREFIX", cluster.getName()+"-"+cluster.getId());
    		int setupStatus = 0;
    		if(cluster.getProvider().equalsIgnoreCase("softlayer")){
    			setupStatus = ChefUtil.chefSetup(json, cluster);  	
    		}else if(cluster.getProvider().equalsIgnoreCase("fyre")){
    			setupStatus = ChefUtil.chefSetupFyre(json, cluster);  
    		}
    		if (setupStatus == 0){    			
    			System.out.println("Order Cluster Install");
    			cluster.setStatus("INSTALLING");
        		HibernateUtil.updateCluster(cluster);
    			int installStatus = ChefUtil.chefInstall(json, cluster);
    			if(installStatus == 0){
    				cluster.setStatus("INSTALLED");
            		HibernateUtil.updateCluster(cluster);
    				return Response.ok(json).build();
    			}else{
    				cluster.setStatus("INSTALLING_FAILED");
            		HibernateUtil.updateCluster(cluster);
    				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef install failed.").build();
    			}
    		}else{
    			cluster.setStatus("PREPARING_FAILED");
        		HibernateUtil.updateCluster(cluster);
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef setup failed.").build();
    		}
    		
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
		return null;
    }
    
    @POST
    @Path("/{id}/reload")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response reload(@PathParam("id") int id, @QueryParam("user_email") String user_email) throws IOException {
    	Cluster cluster = HibernateUtil.getCluster(id);
    	if(cluster != null  && cluster.getUser_email().equalsIgnoreCase(user_email)){
    		int reloadStatus = 1;
    		System.out.println("Reloading cluster "+cluster.getName()+"-"+cluster.getId()+"...");
    		cluster.setStatus("RELOADING");
    		HibernateUtil.updateCluster(cluster);
    		
    		if(cluster.getProvider().equalsIgnoreCase("softlayer")){
    			reloadStatus = ChefUtil.chefReload(cluster);	
    		}else if(cluster.getProvider().equalsIgnoreCase("fyre")){
    			reloadStatus = ChefUtil.chefReloadFyre(cluster);
    		}
    		
    		if (reloadStatus == 0){
    			cluster.setStatus("RELOADED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.ok(cluster).build();
    		}else{
    			cluster.setStatus("RELOADING_FAILED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef reload failed.").build();
    		}
    		
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " +id+ " or email "+user_email+" doesn't match cluster owner "+cluster.getUser_email()).build();
    	}
    }
    
    @POST
    @Path("/{id}/reinstall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response reinstall(@PathParam("id") int id, @QueryParam("user_email") String user_email, String json) throws IOException {
    	System.out.println("Uninstalling cluster.");
    	Cluster cluster = HibernateUtil.getCluster(id);
    	if(cluster != null && cluster.getUser_email().equalsIgnoreCase(user_email)){
    		cluster.setStatus("UNINSTALLING");
    		HibernateUtil.updateCluster(cluster);
    		json = json.replaceAll("CLUSTER_PREFIX", cluster.getName()+"-"+cluster.getId());
    		System.out.println(json);
    		int reinstallStatus = ChefUtil.chefReinstall(json, cluster);
    		int cleanupStatus = ChefUtil.chefBICleanup(cluster);
    		if (reinstallStatus == 0 && cleanupStatus == 0){
    			cluster.setStatus("UNINSTALLED");
    			HibernateUtil.updateCluster(cluster);    			
    			Add logic to install now.
    			cluster.setStatus("PREPARING");
        		HibernateUtil.updateCluster(cluster);
        		int setupStatus = ChefUtil.chefSetup(json, cluster);
        		if (setupStatus == 0){    			
        			System.out.println("Order Cluster Install");
        			cluster.setStatus("INSTALLING");
            		HibernateUtil.updateCluster(cluster);
        			int installStatus = ChefUtil.chefInstall(json, cluster);
        			if(installStatus == 0){
        				cluster.setStatus("INSTALLED");
                		HibernateUtil.updateCluster(cluster);
        				return Response.ok(json).build();
        			}else{
        				cluster.setStatus("REINSTALLING_FAILED");
                		HibernateUtil.updateCluster(cluster);
        				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef install failed.").build();
        			}
        		}else{
        			cluster.setStatus("PREPARING_FAILED");
            		HibernateUtil.updateCluster(cluster);
        			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef setup failed.").build();
        		}
    			return Response.ok(cluster).build();
    		}else{
    			cluster.setStatus("REINSTALLING_FAILED");
    			HibernateUtil.updateCluster(cluster);
    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef reinstall failed.").build();
    		}
    		
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " +id+ " or email "+user_email+" doesn't match cluster owner "+cluster.getUser_email()).build();
    	}
		return null;
    }
    
    @POST
    @Path("/{id}/cleanup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response cleanup(@PathParam("id") int id) throws IOException {
    	System.out.println("Cleaning up cluster.");
    	Cluster cluster = HibernateUtil.getCluster(id);
    	if(cluster != null){    		
    		int cleanupStatus = 0;//ChefUtil.chefBICleanup(cluster);
    		if (cleanupStatus == 0){
    			    			
    			Add logic to install now.
    			return Response.ok(cluster).build();
    		}else{

    			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Chef reinstall failed.").build();
    		}
    		
    	}else{
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
    	}
    }*/
}
