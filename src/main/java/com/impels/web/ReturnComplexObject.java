/**
 * 
 */
package com.impels.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Carol Mak
 *
 */
public class ReturnComplexObject extends ReturnSimpleObject {
	private static final Logger logger = Logger.getLogger(ReturnComplexObject.class);
	private String business_type;
	private List results;
	
	public ReturnComplexObject(){
		super();
		this.business_type=ImpelsConstants.COMPLEX_BUSINESS_TYPE;		
	}
	
	public void setResults(List resultsList){
		Boolean verdict=false;
		List<String> simpleObjects=new ArrayList<String>();
		JSONObject jsonSimpleObject;
		try{
			if(resultsList.get(0) instanceof JSONObject){
				for(JSONObject json:(List<JSONObject>)resultsList){
					try {
						verdict=(json.get(ImpelsConstants.ARTICLE_ID)!=null && json.get(ImpelsConstants.IMAGE_FILE) !=null 
								&& json.get(ImpelsConstants.CONTENTS)!=null)? true:false;
						if(verdict){
							simpleObjects.clear();
							simpleObjects=(List<String>)json.get(ImpelsConstants.CONTENTS);
							for(String sobj:simpleObjects){
								jsonSimpleObject=new JSONObject(sobj);
								verdict=(jsonSimpleObject.get(ImpelsConstants.ARTICLE_ID)!=null 
										&& jsonSimpleObject.get(ImpelsConstants.IMAGE_FILE) !=null)?true:false;
								if(!verdict){
									throw new Exception(String.format("The result object is not valid [%s]", sobj));
								}
							}
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
	
}
