/**
 * 
 */
package com.impels.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Carol Mak
 *
 */
public class ReturnSimpleObject implements ReturnObject {
	private static final Logger logger = Logger.getLogger(ReturnSimpleObject.class);
	private String business_type;
	private List results;
	private int request_status;
	private Set beacon_siblings;
	
	public ReturnSimpleObject(){
		this.business_type=ImpelsConstants.SIMPLE_BUSINESS_TYPE;
		this.beacon_siblings=null;
		this.request_status=200; //TODO: change to HTTP Status Code from the response
		this.results=new ArrayList<String>();
	}
	
	public String getBusiness_type(){
		return this.business_type;
	}
	
	public void setResults(List resultsList){
		Boolean verdict=false;
		try{
			if(resultsList.get(0) instanceof JSONObject){
				for(JSONObject json:(List<JSONObject>)resultsList){
					try {
						verdict=(json.get(ImpelsConstants.ARTICLE_ID)!=null && json.get(ImpelsConstants.IMAGE_FILE) !=null)? true:false;
						if(verdict){
							this.results.add(json.toString());
						}else{
							throw new Exception(String.format("The result object is not valid [%s]", json.toString()));
						}
					} catch (JSONException e) {
						logger.error("The results are not json objects.", e);
						this.results=Collections.EMPTY_LIST;
					} catch (Exception exception){
						logger.error(exception);
						this.results=Collections.EMPTY_LIST;
					}
				}
			}else{
				this.results=Collections.EMPTY_LIST;
				throw new Exception("The result objects are not JSONObjects");
			}
		}catch(Exception invalidTypeException){
			logger.error(invalidTypeException);
		}
		
	}
	
	public List getResults(){
		return this.results;
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
