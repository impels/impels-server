package com.impels.web;

/**
 * @author Carol Mak
 *
 */

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.json.JSONException;
import org.json.JSONObject;

@Path(ImpelsConstants.CONTEXT_ROOT)
public class ArticleResource{
	
	private class ReturnSimpleObject{
		private String business_type;
		private Map results_map;
		private int request_status;
		private Set beacon_siblings;
		
		public ReturnSimpleObject(){
			this.business_type=ImpelsConstants.SIMPLE_BUSINESS_TYPE;
			this.beacon_siblings=null;
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
		
		public void setBeacon_siblings(Set beacon_siblings){
			this.beacon_siblings=beacon_siblings;
		}
		
		public Set getBeacon_siblings(){
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
	public Response getArticles(@QueryParam(ImpelsConstants.BLE_MAJOR_ID) String bleMajId) 
	throws FileNotFoundException{
		//TODO: Temporary getting an array of dummy files, but 
		//TODO:	it will be a data service call to Google Data Service
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		JSONObject obj=new JSONObject();
		String relativePath="/";
		String json="";
		
		// Creates dummy sibbling ids
		Set<String> ids=new HashSet<String>(2);
		ids.add(UUID.randomUUID().toString());
		ids.add(UUID.randomUUID().toString());
		
		// Gets the image files from resource folder
		URL pathURL=this.getClass().getResource(relativePath);
		File resourcesFolder=new File(pathURL.getFile());
		File[] imageFiles=resourcesFolder.listFiles();
		List<FileInputStream> inStreams=new ArrayList<FileInputStream>();
		int idx=0;
		for(File imageFile:imageFiles){
			if (imageFile.isFile()){
				inStreams.add(new FileInputStream(imageFile));
			}
		}
		Set<FileInputStream> inStreamSet = new HashSet<FileInputStream>(inStreams);
		
		// Construct the result object by reading byte from byte off these image files.
		ReturnSimpleObject returnObj=new ReturnSimpleObject();
		Map<String, int[]> results_map=new HashMap<String, int[]>(inStreamSet.size());
		for(FileInputStream inStream:inStreamSet){
			try {
				int[] array = new int[(int) inStream.getChannel().size()];
				idx=0;
				DataInputStream dis = new DataInputStream( inStream );
				int nextbyte=dis.read();
				while(nextbyte != -1){
					array[idx]=nextbyte;
					nextbyte=dis.read();
					idx++;
				}
				results_map.put(UUID.randomUUID().toString(), array);
				returnObj.setBeacon_siblings(ids);
				returnObj.setRequest_status(200);
				returnObj.setResults_map(results_map);
				json = ow.writeValueAsString(returnObj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Response.status(200).entity(json).build();
		
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
