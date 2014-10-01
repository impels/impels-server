/**
 * 
 */
package com.impels.web;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Carol Mak
 *
 */
public class ReturnSimpleObject implements ReturnObject {
	private String business_type;
	private String results;
	private int request_status;
	private Set beacon_siblings;
	
	public ReturnSimpleObject(){
		this.business_type=ImpelsConstants.SIMPLE_BUSINESS_TYPE;
		this.beacon_siblings=null;
		this.request_status=200; //TODO: change to HTTP Status Code from the response
		this.results=null;
	}
	
	public String getBusiness_type(){
		return this.business_type;
	}
	
	public void setResults(JSONObject results){
		this.results=results.toString();
	}
	
	public String getResults(){
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
	
	public static JSONObject toJsonObject(byte[] bytes) {
        String json;
        try {
            json = new String(bytes, "UTF8");
            return new JSONObject(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
