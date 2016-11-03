package org.takastudy.tmpl.solr.tika.config;

public class SolrConfig {
	
	private static final String SOLR_SRVER = "localhost";
	private static final String PORT = "8983";
	private static final String SEARCH_NAME = "new_core";
	
	public static String getBaseUrl(){
		return "http://"+SOLR_SRVER+":"+PORT+"/solr/"+SEARCH_NAME+"/";
	}
	
	

}
