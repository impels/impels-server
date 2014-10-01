/**
 * 
 */
package com.impels.web;

import java.util.Set;

import org.json.JSONObject;

/**
 * @author Carol Mak
 *
 */
public interface ReturnObject {
	public String getBusiness_type();
	public void setRequest_status(int http_status_code);
	public int getRequest_status();
	public void setResults(JSONObject results);
	public String getResults();
	public void setBeacon_siblings(Set beacon_siblings);
	public Set getBeacon_siblings();
}
