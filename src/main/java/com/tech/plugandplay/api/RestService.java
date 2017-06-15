package com.tech.plugandplay.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.providers.jackson.Formatted;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tech.plugandplay.hibernate.HibernateUtil;
import com.tech.plugandplay.model.Batch;
import com.tech.plugandplay.model.BatchList;
import com.tech.plugandplay.model.Business;
import com.tech.plugandplay.model.Top100;
import com.tech.plugandplay.model.Top100List;
import com.tech.plugandplay.model.Top20;
import com.tech.plugandplay.model.Top20List;
import com.tech.plugandplay.model.Ventures;
import com.tech.plugandplay.util.Constants;

import net.coobird.thumbnailator.Thumbnails;

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
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any companies.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/ventures/query/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getVenturesPage(@PathParam("page") int page, @QueryParam("query") String query) {
		
		JSONObject pagination = new JSONObject();
		List<Ventures> ventures = null;
		if(query.length() > 2 && query.length() < 32 ){
			pagination.put("count", HibernateUtil.getVenturesSearchCount(query));
	    	ventures = HibernateUtil.getVenturesPage(page, query);
	    	pagination.put("data", ventures);
		} else {
			pagination.put("count", HibernateUtil.getVenturesCount());
	    	ventures = HibernateUtil.getVenturesPage(page);
	    	pagination.put("data", ventures);
		}
       	if(!ventures.isEmpty()){
    		return Response.ok(pagination.toString()).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any content.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@POST
    @Path("/lucene")
    public Response updateLucene() {
		HibernateUtil.updateLuceneIndex();
		return Response.ok().build();
		
	}
	
	@POST
    @Path("/top100/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop100(String json) {
		JSONObject body = new JSONObject(json);
		String listName = body.getString("listName");
    	List<Top100> top100 = HibernateUtil.getAllTop100(listName);
       	if(!top100.isEmpty()){
       		StringBuilder ids = new StringBuilder();
       		for(Top100 venture: top100){
       			ids.append(venture.getVenture_id());
       			ids.append(",");
       		} 		
       		List<Ventures> ventures = HibernateUtil.getVentureTop100(ids.toString().substring(0, ids.toString().length()-1));
       		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any top 100.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@POST
    @Path("/top20/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop20(String json) {	
		JSONObject body = new JSONObject(json);
		String listName = body.getString("listName");
    	List<Top20> top20 = HibernateUtil.getAllTop20(listName);
       	if(!top20.isEmpty()){
       		StringBuilder ids = new StringBuilder();
       		for(Top20 venture: top20){
       			ids.append(venture.getVenture_id());
       			ids.append(",");
       		} 		
       		List<Ventures> ventures = HibernateUtil.getVentureTop20(ids.toString().substring(0, ids.toString().length()-1));
       		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any top 20.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@POST
    @Path("/batch/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getBatch(String json) {	
		JSONObject body = new JSONObject(json);
		String batchName = body.getString("listName");
    	List<Batch> batch = HibernateUtil.getAllBatch(batchName);
       	if(!batch.isEmpty()){
       		StringBuilder ids = new StringBuilder();
       		for(Batch venture: batch){
       			ids.append(venture.getVenture_id());
       			ids.append(",");
       		} 		
       		List<Ventures> ventures = HibernateUtil.getVentureBatch(ids.toString().substring(0, ids.toString().length()-1));
       		return Response.ok(ventures).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any top 20.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/top100/lists")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop100Lists() {
	
    	List<Top100List> top100list = HibernateUtil.getTop100Lists();
       	if(!top100list.isEmpty()){
       		return Response.ok(top100list).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any top 100 lists.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/top20/lists")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop20Lists() {

    	List<Top20List> top20list = HibernateUtil.getTop20Lists();
       	if(!top20list.isEmpty()){
       		return Response.ok(top20list).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any top 20 lists.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/batch/lists")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getBatchLists() {

    	List<BatchList> batchlist = HibernateUtil.getBatchLists();
       	if(!batchlist.isEmpty()){
       		return Response.ok(batchlist).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any top 20 lists.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/top100/archived")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop100ListsArchived() {

    	List<Top100List> top100list = HibernateUtil.getTop100ListsArchived();
       	if(!top100list.isEmpty()){
       		return Response.ok(top100list).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any archived top 100 lists.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/top20/archived")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getTop20ListsArchived() {

    	List<Top20List> top20list = HibernateUtil.getTop20ListsArchived();
       	if(!top20list.isEmpty()){
       		return Response.ok(top20list).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any archived top 20 lists.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@GET
    @Path("/batch/archived")
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response getBatchListsArchived() {

    	List<BatchList> batchlist = HibernateUtil.getBatchListsArchived();
       	if(!batchlist.isEmpty()){
       		return Response.ok(batchlist).header("Access-Control-Allow-Origin", "*").build();
    	}else{
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any archived top 20 lists.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
	
	@POST
	@Path("/top100/delete")
	@Consumes("application/json")
	@Produces("application/json")
	@Formatted
    public Response deleteFromTop100(String json){
		//log.info("Delete from top 100 path param:"+ id);
		JSONObject body = new JSONObject(json);
		int id = body.getInt("id");
		String listName = body.getString("listName");
		Top100 deleteMe = HibernateUtil.getTop100(id,listName );
		if(deleteMe == null){
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity("Could not delete top 100!").header("Access-Control-Allow-Origin", "*").build();
		}
		List<Top100> top100list = HibernateUtil.getAllTop100(listName);
		for(int i = deleteMe.getOrder(); i < top100list.size(); i++){
	    	Top100 tmp = top100list.get(i);
	    	tmp.setOrder(tmp.getOrder()-1);
	    	top100list.set(i-1, tmp);
	    }
		top100list.remove(top100list.size()-1);
		Ventures venture = HibernateUtil.getVenture(id);
		venture.removeTop100(deleteMe);
		HibernateUtil.updateVenture(venture);
	    for(Top100 list : top100list){
	    	HibernateUtil.updateTop100(list);
	    }
		return Response.ok(deleteMe).header("Access-Control-Allow-Origin", "*").build();
		
	}

	@POST
	@Path("/top20/delete")
	@Consumes("application/json")
	@Produces("application/json")
	@Formatted
    public Response deleteFromTop20(String json){
		//log.info("Delete from top 20 path param:"+ id);
		JSONObject body = new JSONObject(json);
		int id = body.getInt("id");
		String listName = body.getString("listName");
		Top20 deleteMe = HibernateUtil.getTop20(id, listName);
		if(deleteMe == null){
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity("Could not delete top 20!").header("Access-Control-Allow-Origin", "*").build();
		}
		List<Top20> top20list = HibernateUtil.getAllTop20(listName);
		for(int i = deleteMe.getOrder(); i < top20list.size(); i++){
	    	Top20 tmp = top20list.get(i);
	    	tmp.setOrder(tmp.getOrder()-1);
	    	top20list.set(i-1, tmp);
	    }
		top20list.remove(top20list.size()-1);
		Ventures venture = HibernateUtil.getVenture(id);
		venture.removeTop20(deleteMe);
		HibernateUtil.updateVenture(venture);
	    for(Top20 list : top20list){
	    	HibernateUtil.updateTop20(list);
	    }
		return Response.ok(deleteMe).header("Access-Control-Allow-Origin", "*").build();
		
	}
	
	@POST
	@Path("/batch/delete")
	@Consumes("application/json")
	@Produces("application/json")
	@Formatted
    public Response deleteFromBatch(String json){
		//log.info("Delete from top 20 path param:"+ id);
		JSONObject body = new JSONObject(json);
		int id = body.getInt("id");
		String listName = body.getString("listName");
		Batch deleteMe = HibernateUtil.getBatch(id, listName);
		if(deleteMe == null){
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity("Could not delete top 20!").header("Access-Control-Allow-Origin", "*").build();
		}
		List<Batch> batchlist = HibernateUtil.getAllBatch(listName);
		for(int i = deleteMe.getOrder(); i < batchlist.size(); i++){
	    	Batch tmp = batchlist.get(i);
	    	tmp.setOrder(tmp.getOrder()-1);
	    	batchlist.set(i-1, tmp);
	    }
		batchlist.remove(batchlist.size()-1);
		Ventures venture = HibernateUtil.getVenture(id);
		venture.removeBatch(deleteMe);
		HibernateUtil.updateVenture(venture);
	    for(Batch list : batchlist){
	    	HibernateUtil.updateBatch(list);
	    }
		return Response.ok(deleteMe).header("Access-Control-Allow-Origin", "*").build();
		
	}
	
	@POST
    @Path("/top100/move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response moveTop100(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		
		Top100 current = HibernateUtil.getTop100(body.getInt("id"), body.getString("listName"));
		int order = body.getInt("order");
		List<Top100> top100list = HibernateUtil.getAllTop100(body.getString("listName"));
		if(order < 1 || order > 200 || order == current.getOrder() || order > top100list.size()){
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString("The order value "+order+" is not valid! Please try again.");
			//log.info(jsonInString);
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).header("Access-Control-Allow-Origin", "*").build();	
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
		
		return Response.ok(current).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top20/move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response moveTop20(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		
		Top20 current = HibernateUtil.getTop20(body.getInt("id"), body.getString("listName"));
		int order = body.getInt("order");
		List<Top20> top20list = HibernateUtil.getAllTop20(body.getString("listName"));
		if(order < 1 || order > 20 || order == current.getOrder() || order > top20list.size()){
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString("The order value "+order+" is not valid! Please try again.");
			//log.info(jsonInString);
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).header("Access-Control-Allow-Origin", "*").build();	
		}
		
	    for(int i = current.getOrder(); i < top20list.size(); i++){
	    	Top20 tmp = top20list.get(i);
	    	tmp.setOrder(tmp.getOrder()-1);
	    	top20list.set(i-1, tmp);
	    }
	    top20list.remove(top20list.size()-1);
	    for(int i = order - 1 ; i < top20list.size(); i++){
	    	Top20 tmp = top20list.get(i);
	    	tmp.setOrder(tmp.getOrder()+1);
	    	top20list.set(i, tmp);
	    } 
	    current.setOrder(order);
	    top20list.add(order-1, current);
	    for(Top20 list : top20list){
	    	HibernateUtil.updateTop20(list);
	    }
		
		return Response.ok(current).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/batch/move")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response moveBatch(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		
		Batch current = HibernateUtil.getBatch(body.getInt("id"), body.getString("listName"));
		int order = body.getInt("order");
		List<Batch> batchlist = HibernateUtil.getAllBatch(body.getString("listName"));
		if(order < 1 || order > 100 || order == current.getOrder() || order > batchlist.size()){
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString("The order value "+order+" is not valid! Please try again.");
			//log.info(jsonInString);
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(jsonInString).header("Access-Control-Allow-Origin", "*").build();	
		}
		
	    for(int i = current.getOrder(); i < batchlist.size(); i++){
	    	Batch tmp = batchlist.get(i);
	    	tmp.setOrder(tmp.getOrder()-1);
	    	batchlist.set(i-1, tmp);
	    }
	    batchlist.remove(batchlist.size()-1);
	    for(int i = order - 1 ; i < batchlist.size(); i++){
	    	Batch tmp = batchlist.get(i);
	    	tmp.setOrder(tmp.getOrder()+1);
	    	batchlist.set(i, tmp);
	    } 
	    current.setOrder(order);
	    batchlist.add(order-1, current);
	    for(Batch list : batchlist){
	    	HibernateUtil.updateBatch(list);
	    }
		
		return Response.ok(current).header("Access-Control-Allow-Origin", "*").build();
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
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any companies.").header("Access-Control-Allow-Origin", "*").build();
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
    		return Response.status(Response.Status.NO_CONTENT).entity("Entity not found for ID: " + id).header("Access-Control-Allow-Origin", "*").build();
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
		List<Top100> top100list = HibernateUtil.getAllTop100(body.getString("listName"));
		if(top100list.size()==200){
			return Response.status(Status.PARTIAL_CONTENT).entity(Constants.GenericErrorMessages.EXCEEDED_SIZE).header("Access-Control-Allow-Origin", "*").build();
		}
		//Check to see if part of top100 already
		if(HibernateUtil.getTop100(body.getInt("id"), body.getString("listName")) != null){
			return Response.status(Status.NO_CONTENT).entity("Top 100 venture with id "+body.getInt("id")+" already exists in list "+body.getString("listName")+"!").header("Access-Control-Allow-Origin", "*").build();
		}
		Top100 top100 = new Top100();
		top100.setOrder(top100list.size()+1);
		top100.setVenture_id(body.getInt("id"));
		top100.setListName(body.getString("listName"));
		HibernateUtil.addTop100(top100);
		Ventures venture = HibernateUtil.getVenture(body.getInt("id"));
		venture.addTop100(top100);
		HibernateUtil.updateVenture(venture);
		return Response.ok(top100).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/ventures/addtop20")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response addTop20(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		List<Top20> top20list = HibernateUtil.getAllTop20(body.getString("listName"));
		if(top20list.size()==20){
			return Response.status(Status.PARTIAL_CONTENT).entity(Constants.GenericErrorMessages.EXCEEDED_SIZE).header("Access-Control-Allow-Origin", "*").build();
		}
		//Check to see if part of top100 already
		if(HibernateUtil.getTop20(body.getInt("id"), body.getString("listName")) != null){
			return Response.status(Status.NO_CONTENT).entity("Top 20 venture with id "+body.getInt("id")+" already exists in list "+body.getString("listName")+"!").header("Access-Control-Allow-Origin", "*").build();
		}
		Top20 top20 = new Top20();
		top20.setOrder(top20list.size()+1);
		top20.setVenture_id(body.getInt("id"));
		top20.setListName(body.getString("listName"));
		HibernateUtil.addTop20(top20);
		Ventures venture = HibernateUtil.getVenture(body.getInt("id"));
		venture.addTop20(top20);
		HibernateUtil.updateVenture(venture);
		return Response.ok(top20).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/ventures/addbatch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response addBatch(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		List<Batch> batchlist = HibernateUtil.getAllBatch(body.getString("listName"));
		if(batchlist.size()==20){
			return Response.status(Status.PARTIAL_CONTENT).entity(Constants.GenericErrorMessages.EXCEEDED_SIZE).header("Access-Control-Allow-Origin", "*").build();
		}
		//Check to see if part of top100 already
		if(HibernateUtil.getBatch(body.getInt("id"), body.getString("listName")) != null){
			return Response.status(Status.NO_CONTENT).entity("Batch venture with id "+body.getInt("id")+" already exists in list "+body.getString("listName")+"!").header("Access-Control-Allow-Origin", "*").build();
		}
		Batch batch = new Batch();
		batch.setOrder(batchlist.size()+1);
		batch.setVenture_id(body.getInt("id"));
		batch.setListName(body.getString("listName"));
		HibernateUtil.addBatch(batch);
		Ventures venture = HibernateUtil.getVenture(body.getInt("id"));
		venture.addBatch(batch);
		HibernateUtil.updateVenture(venture);
		return Response.ok(batch).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top100/newlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response newTop100List(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		//Check to see if part of top100 already
		if(HibernateUtil.getTop100ListByName(body.getString("listName")) != null){
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(mapper.writeValueAsString("Top 100 list with id "+body.getString("listName")+" already exists!")).header("Access-Control-Allow-Origin", "*").build();
		}
		Top100List top100list = new Top100List();
		top100list.setListName(body.getString("listName"));
		top100list.setArchive(new Boolean(false));
		top100list.setTime(new Date());
		top100list = HibernateUtil.addTop100List(top100list);
		//List<Top100List> newlist = HibernateUtil.getTop100Lists();
		return Response.ok(top100list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top20/newlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response newTop20List(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		//Check to see if part of top20 already
		if(HibernateUtil.getTop20ListByName(body.getString("listName")) != null){
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(mapper.writeValueAsString("Top 20 list with id "+body.getString("listName")+" already exists!")).header("Access-Control-Allow-Origin", "*").build();
		}
		Top20List top20list = new Top20List();
		top20list.setListName(body.getString("listName"));
		top20list.setArchive(new Boolean(false));
		top20list.setTime(new Date());
		top20list = HibernateUtil.addTop20List(top20list);

		return Response.ok(top20list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/batch/newlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response newBatchList(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		//Check to see if part of top20 already
		if(HibernateUtil.getBatchListByName(body.getString("listName")) != null){
			ObjectMapper mapper = new ObjectMapper();
			return Response.status(Constants.HTTPCodes.BAD_REQUEST).entity(mapper.writeValueAsString("Batch list with id "+body.getString("listName")+" already exists!")).header("Access-Control-Allow-Origin", "*").build();
		}
		BatchList batchlist = new BatchList();
		batchlist.setListName(body.getString("listName"));
		batchlist.setArchive(new Boolean(false));
		batchlist.setTime(new Date());
		batchlist = HibernateUtil.addBatchList(batchlist);

		return Response.ok(batchlist).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top100/archivelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response archiveTop100List(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		Top100List top100list = HibernateUtil.getTop100List(body.getInt("id"));

		top100list.setArchive(new Boolean(true));
		HibernateUtil.updateTop100List(top100list);

		return Response.ok(top100list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top20/archivelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response archiveTop20List(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		Top20List top20list = HibernateUtil.getTop20List(body.getInt("id"));

		top20list.setArchive(new Boolean(true));
		HibernateUtil.updateTop20List(top20list);

		return Response.ok(top20list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/batch/archivelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response archiveBatchList(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		BatchList batchlist = HibernateUtil.getBatchList(body.getInt("id"));

		batchlist.setArchive(new Boolean(true));
		HibernateUtil.updateBatchList(batchlist);

		return Response.ok(batchlist).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top100/unarchivelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response unarchiveTop100List(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		Top100List top100list = HibernateUtil.getTop100List(body.getInt("id"));

		top100list.setArchive(new Boolean(false));
		HibernateUtil.updateTop100List(top100list);

		return Response.ok(top100list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/top20/unarchivelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response unarchiveTop20List(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		Top20List top20list = HibernateUtil.getTop20List(body.getInt("id"));

		top20list.setArchive(new Boolean(false));
		HibernateUtil.updateTop20List(top20list);

		return Response.ok(top20list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/batch/unarchivelist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response unarchiveBatchList(String json) throws JsonGenerationException, JsonMappingException, IOException {
		
		log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);

		BatchList batchlist = HibernateUtil.getBatchList(body.getInt("id"));

		batchlist.setArchive(new Boolean(false));
		HibernateUtil.updateBatchList(batchlist);

		return Response.ok(batchlist).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/ventures/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response updateVenture(String json) {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
		
		Ventures venture = HibernateUtil.getVenture(body.getInt("id"));
		
		if(venture == null){
			return Response.status(Status.NO_CONTENT).entity("Could not find venture with id "+body.getInt("id")+" to update!").header("Access-Control-Allow-Origin", "*").build();
		}
		if(!body.isNull("advantage")){
			venture.setAdvantage(body.getString("advantage"));
		}
		if(!body.isNull("b2bb2c")){
			venture.setB2bb2c(body.getString("b2bb2c"));
		}
		if(!body.isNull("background")){
			venture.setBackground(body.getString("background"));
		}
		if(!body.isNull("blurb")){
			venture.setBlurb(body.getString("blurb"));
		}
		if(!body.isNull("caseStudy")){
			venture.setCaseStudy(body.getString("caseStudy"));
		}
		if(!body.isNull("city")){
			venture.setCity(body.getString("city"));
		}
		if(!body.isNull("comments")){
			venture.setComments(body.getString("comments"));
		}
		if(!body.isNull("companyName")){
			venture.setCompanyName(body.getString("companyName"));
		}
		if(!body.isNull("competition")){
			venture.setCompetition(body.getString("competition"));
		}
		if(!body.isNull("contactName")){
			venture.setContactName(body.getString("contactName"));
		}
		if(!body.isNull("email")){
			venture.setEmail(body.getString("email"));
		}
		if(!body.isNull("employees")){
			venture.setEmployees(body.getString("employees"));
		}
		if(!body.isNull("founded")){
			venture.setFounded(body.getString("founded"));
		}
		if(!body.isNull("location")){
			venture.setLocation(body.getString("location"));
		}
		if(!body.isNull("materials")){
			venture.setMaterials(body.getString("materials"));
		}
		if(!body.isNull("partnerInterests")){
			venture.setPartnerInterests(body.getString("partnerInterests"));
		}
		if(!body.isNull("phoneNumber")){
			venture.setPhoneNumber(body.getString("phoneNumber"));
		}
		if(!body.isNull("pnpContact")){
			venture.setPnpContact(body.getString("pnpContact"));
		}
		if(!body.isNull("stage")){
			venture.setStage(body.getString("stage"));
		}
		if(!body.isNull("tags")){
			venture.setTags(body.getString("tags"));
		}
		if(!body.isNull("totalMoneyRaised")){
			venture.setTotalMoneyRaised(body.getString("totalMoneyRaised"));
		}
		if(!body.isNull("verticals")){
			venture.setVerticals(body.getString("verticals"));
		}
		if(!body.isNull("website")){
			venture.setWebsite(body.getString("website"));
		}
		if(!body.isNull("portfolio")){
			venture.setPortfolio(body.getBoolean("portfolio"));
		}
		if(!body.isNull("dateOfInvestment")){
			venture.setDateOfInvestment(body.getString("dateOfInvestment"));
		}
		venture.setUpdated(new Date());;
	
		HibernateUtil.updateVenture(venture);
		return Response.ok(venture).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/ventures/logo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response updateVentureLogo(MultipartFormDataInput multipart) {
		
		String fileName = "";
		int id = 0;
		Map<String, List<InputPart>> formParts = multipart.getFormDataMap();
		try {
			id = multipart.getFormDataPart("id", Integer.class, null);
			log.info("Ventures logo id: "+ id);
		} catch (IOException e) {
			log.fatal(e.getMessage(), e.fillInStackTrace());
			return Response.status(Status.NO_CONTENT).entity("Failed to update startup logo!").header("Access-Control-Allow-Origin", "*").build();
		}
		Ventures venture = HibernateUtil.getVenture(id);
		List<InputPart> inPart = formParts.get("file"); // "file" should match the name attribute of your HTML file input 
		for (InputPart inputPart : inPart) {
		  // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
			MultivaluedMap<String, String> headers = inputPart.getHeaders();
			String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
			for (String name : contentDispositionHeader) {
			  if ((name.trim().startsWith("filename"))) {
			    String[] tmp = name.split("=");
			    fileName = tmp[1].trim().replaceAll("\"","");          
			  }
			}
		}
		log.info("File name: "+ fileName);		
		try (InputStream in = multipart.getFormDataPart("file", InputStream.class, null);
		        FileOutputStream fos = new FileOutputStream("/tmp/images/startups/"+id+fileName.substring(fileName.lastIndexOf("."), fileName.length()))) {
		        byte[] buff = new byte[1024];
		        int count;
		        while ((count = in.read(buff)) != -1) {
		            fos.write(buff, 0, count);
		        }
	    } catch (IOException e) {

	    	log.fatal(e.getMessage(), e.fillInStackTrace());
			return Response.status(Status.NO_CONTENT).entity("Failed to update startup logo!").header("Access-Control-Allow-Origin", "*").build();
		}
		
		try {			
			BufferedImage originalImage = ImageIO.read(new File("/tmp/images/startups/"+id+fileName.substring(fileName.lastIndexOf("."), fileName.length())));
			BufferedImage thumbnail = Thumbnails.of(originalImage).size(100, 100).asBufferedImage();
			venture.setThumbnail(thumbnail, fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()));
			venture = HibernateUtil.updateVenture(venture);
		} catch (IOException e) {

			log.fatal(e.getMessage(), e.fillInStackTrace());
			return Response.status(Status.NO_CONTENT).entity("Failed to update startup logo!").header("Access-Control-Allow-Origin", "*").build();
			
		}

		return Response.ok(venture).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
    @Path("/ventures/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Formatted
    public Response createVenture(String json) {
		
		//log.info("JSON body:"+ json);
		JSONObject body = new JSONObject(json);
/*		Ventures venture = HibernateUtil.getVenture(body.getInt("id"));		
		if(venture == null){
			return Response.status(Status.NO_CONTENT).entity("Could not find venture with id "+body.getInt("id")+" to update!").header("Access-Control-Allow-Origin", "*").build();
		}*/
		Ventures venture = new Ventures();
		if(!body.isNull("advantage")){
			venture.setAdvantage(body.getString("advantage"));
		}
		if(!body.isNull("b2bb2c")){
			List<String> listb2b = new ArrayList<String>();
			for (int i = 0; i < body.getJSONArray("b2bb2c").length(); i++) {
			    listb2b.add(body.getJSONArray("b2bb2c").getString(i));
			}
			venture.setB2bb2c(String.join(",", listb2b));
		}
		
		if(!body.isNull("background")){
			venture.setBackground(body.getString("background"));
		}
		if(!body.isNull("blurb")){
			venture.setBlurb(body.getString("blurb"));
		}
		if(!body.isNull("caseStudy")){
			venture.setCaseStudy(body.getString("caseStudy"));
		}
		if(!body.isNull("city")){
			venture.setCity(body.getString("city"));
		}
		if(!body.isNull("comments")){
			venture.setComments(body.getString("comments"));
		}
		if(!body.isNull("companyName")){
			venture.setCompanyName(body.getString("companyName"));
		}
		if(!body.isNull("competition")){
			venture.setCompetition(body.getString("competition"));
		}
		if(!body.isNull("contactName")){
			venture.setContactName(body.getString("contactName"));
		}
		if(!body.isNull("email")){
			venture.setEmail(body.getString("email"));
		}
		if(!body.isNull("employees")){
			venture.setEmployees(body.getString("employees"));
		}
		if(!body.isNull("founded")){
			venture.setFounded(body.getString("founded"));
		}
		if(!body.isNull("location")){
			venture.setLocation(body.getString("location"));
		}
		if(!body.isNull("materials")){
			venture.setMaterials(body.getString("materials"));
		}
		if(!body.isNull("partnerInterests")){
			venture.setPartnerInterests(body.getString("partnerInterests"));
		}
		if(!body.isNull("phoneNumber")){
			venture.setPhoneNumber(body.getString("phoneNumber"));
		}
		if(!body.isNull("pnpContact")){
			venture.setPnpContact(body.getString("pnpContact"));
		}
		if(!body.isNull("stage")){
			venture.setStage(body.getString("stage"));
		}
		if(!body.isNull("tags")){
			List<String> listtabs = new ArrayList<String>();
			for (int i = 0; i < body.getJSONArray("tags").length(); i++) {
				listtabs.add(body.getJSONArray("tags").getString(i));
			}
			venture.setTags(String.join(",", listtabs));
		}
		if(!body.isNull("totalMoneyRaised")){
			venture.setTotalMoneyRaised(body.getString("totalMoneyRaised"));
		}
		if(!body.isNull("verticals")){
			List<String> listverticals = new ArrayList<String>();
			for (int i = 0; i < body.getJSONArray("verticals").length(); i++) {
				listverticals.add(body.getJSONArray("verticals").getString(i));
			}
			venture.setVerticals(String.join(",", listverticals));
		}
		if(!body.isNull("website")){
			venture.setWebsite(body.getString("website"));
		}
		if(!body.isNull("portfolio")){
			venture.setPortfolio(body.getBoolean("portfolio"));
		}
		if(!body.isNull("dateOfInvestment")){
			venture.setDateOfInvestment(body.getString("dateOfInvestment"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		venture.setTimestamp(sdf.format(time));
		venture.setUpdated(new Date());
		
		HibernateUtil.newVenture(venture);
		return Response.ok(venture).header("Access-Control-Allow-Origin", "*").build();
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
		String logo = "";
		BufferedImage originalImage = null;
		String logo_url = null;
		
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
			if(fields.getJSONObject(i).getString("title").contains("Please upload your company materials")){
				materials = fields.getJSONObject(i).getString("id");
			}
			if(fields.getJSONObject(i).getString("title").contains("Please upload your logo")){
				logo = fields.getJSONObject(i).getString("id");
			}
		}
		
		for (int i = 0; i < answers.length(); i++) {
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(tags)){
				List<String> list = new ArrayList<String>();
				if(!answers.getJSONObject(i).getJSONObject("choices").isNull("labels")){
					for(int j = 0; j < answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").length(); j++){
					    list.add(answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").getString(j));
					}
				}		
				if(!answers.getJSONObject(i).getJSONObject("choices").isNull("other")){
					list.add(answers.getJSONObject(i).getJSONObject("choices").getString("other"));	
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
				if(!answers.getJSONObject(i).getJSONObject("choices").isNull("labels")){
					for(int j = 0; j < answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").length(); j++){
					    list.add(answers.getJSONObject(i).getJSONObject("choices").getJSONArray("labels").getString(j));
					}	
				}
				if(!answers.getJSONObject(i).getJSONObject("choices").isNull("other")){
					list.add(answers.getJSONObject(i).getJSONObject("choices").getString("other"));	
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
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(materials)){
				venture.setMaterials(answers.getJSONObject(i).getString("file_url"));
			}
			if(answers.getJSONObject(i).getJSONObject("field").getString("id").equals(logo)){
				logo_url = answers.getJSONObject(i).getString("file_url");
				try {
					URL url = new URL(logo_url);
					originalImage = ImageIO.read(url);
					BufferedImage thumbnail = Thumbnails.of(originalImage).size(100, 100).asBufferedImage();
					venture.setThumbnail(thumbnail, logo_url.substring(logo_url.lastIndexOf(".")+1, logo_url.length()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		venture.setTimestamp(sdf.format(time));
		venture.setUpdated(new Date());

		venture = HibernateUtil.newVenture(venture);
		if(logo_url != null){
			try {
			    // retrieve image
			    File outputfile = new File("/tmp/images/startups/"+venture.getId()+logo_url.substring(logo_url.lastIndexOf("."), logo_url.length()));
			    ImageIO.write(originalImage, logo_url.substring(logo_url.lastIndexOf(".")+1), outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}                      
		}
		
		
		return Response.ok(venture).header("Access-Control-Allow-Origin", "*").build();
		
	}
	
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
    		return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).header("Access-Control-Allow-Origin", "*").build();
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
    		return Response.status(Response.Status.NOT_FOUND).entity("Could not find any companies.").header("Access-Control-Allow-Origin", "*").build();
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
    		return Response.status(Response.Status.NO_CONTENT).entity("Could not find any companies.").header("Access-Control-Allow-Origin", "*").build();
    	}        
    }
}
