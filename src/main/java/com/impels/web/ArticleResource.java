package com.impels.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path(ImpelsConstants.CONTEXT_ROOT)
public class ArticleResource{
	
	private class ReturnSimpleObject{
		private String business_type;
		private Map results_map;
		private int request_status;
		private List beacon_siblings;
		
		public ReturnSimpleObject(){
			this.business_type=ImpelsConstants.SIMPLE_BUSINESS_TYPE;
			this.beacon_siblings=Collections.EMPTY_LIST;
			this.request_status=200; //TODO: change to HTTP Status Code from the response
			this.results_map=Collections.EMPTY_MAP;
		}
		
		public String getBusiness_type(){
			return this.business_type;
		}
		
		public void setResults_map(Map results){
			this.results_map=results;
		}
		
		public Map getResults_map(){
			return this.results_map;
		}
		
		public void setRequest_status(int http_status_code){
			this.request_status=http_status_code;
		}
		
		public int getRequest_status(){
			return this.request_status;
		}
		
		public void setBeacon_siblings(List beacon_siblings){
			this.beacon_siblings=beacon_siblings;
		}
		
		public List getBeacon_siblings(){
			return this.beacon_siblings;
		}
		
		
	}
	
	/**
	 * Gets the images based on beacon major Id
	 * @param bleMajId
	 * @return OutputStream
	 * @throws FileNotFoundException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ImpelsConstants.GET_PATH)
	public Set<FileInputStream> getArticles(@QueryParam(ImpelsConstants.BLE_MAJOR_ID) String bleMajId) 
	throws FileNotFoundException{
		//TODO: Temporary getting an array of dummy files, but 
		//TODO:	it will be a data service call to Google Data Service
		String relativePath="../../../../resources/";
		File resourcesFolder=new File(relativePath);
		File[] imageFiles=resourcesFolder.listFiles();
		FileInputStream[] inStreams=new FileInputStream[imageFiles.length];
		int idx=0;
		for(File imageFile:imageFiles){
			inStreams[idx]=new FileInputStream(imageFile);
			idx++;
		}
		Set<FileInputStream> inStreamSet = new HashSet<FileInputStream>(Arrays.asList(inStreams));
		
		
//		ReturnSimpleObject returnObj=new ReturnSimpleObject();
//		Map results_map=new HashMap(inStreamSet.size());
//		List<Byte> array = new ArrayList();
//		for(FileInputStream inStream:inStreamSet){
//			DataInputStream dis = new DataInputStream( inStream );
//			
//			results_map.put(UUID.randomUUID().toString(), );
//			
//		}
		
		return inStreamSet;
		
	}
	
	/**
	 * Gets a set of article ids
	 * @param bleMajId
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(ImpelsConstants.GET_ID_PATH)
	public Response getArticleIds(@QueryParam(ImpelsConstants.BLE_MAJOR_ID) String bleMajId){
		//TODO: Temporary returning a set of ids
		//TODO: it will be a data service call to Google Data Service
		Set<String> ids=new HashSet<String>(2);
		ids.add(UUID.randomUUID().toString());
		ids.add(UUID.randomUUID().toString());
		
		JSONObject obj=new JSONObject();
		try {
			obj.put(ImpelsConstants.ARTICLE_ID, ids);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(obj.toString()).build();
	}
	
	@GET
    @Path("/add/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addPlainText(@PathParam("a") double a, @PathParam("b") double b) {
        return (a + b) + "";
    }
	
	/**
	 * Creates an article from an image file
	 * @param imageFile
	 */
	@POST
	@Consumes(ImpelsConstants.IMG_TYPE)
	@Path(ImpelsConstants.CREATE_PATH)
	public String setArticle(@PathParam(ImpelsConstants.BLE_MAJOR_ID) String bleMajId, 
							@PathParam(ImpelsConstants.IMAGE_FILE) File imageFile){
		//TODO:  Will call Google Data Service for adding an entry
		String articleId= UUID.randomUUID().toString();
		return articleId;
	}
	
	/**
	 * Deletes an article based on articleId
	 * @param articleId
	 * @return
	 */
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Path(ImpelsConstants.DELETE_PATH)
	public boolean deleteArticle(@PathParam(ImpelsConstants.ARTICLE_ID) String articleId){
		boolean suceed=true;
		//TODO: Will call Google Data Service to delete an article/image
		return suceed;
	}
}
