package org.takastudy.tmpl.solr.tika.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.metadata.TikaCoreProperties;
import org.takastudy.tmpl.solr.tika.config.SolrConfig;
import org.takastudy.tmpl.solr.tika.util.io.TikaModel;

public class SolrUtil {
	
	private static final int COMMITSIZE = 10000;
	
	private static SolrClient client = new HttpSolrClient.Builder(SolrConfig.getBaseUrl()).build();
	

	public static void addDocuments(List<SolrInputDocument> list) throws SolrServerException, IOException {
		
		int uncommitCount = 0;
		
		for(SolrInputDocument doc : list){
			try {
				client.add(doc);
				uncommitCount++;
			} catch (SolrServerException | IOException e) {
				System.out.println("skip");
				e.printStackTrace();
			}
			
			if(uncommitCount > COMMITSIZE){
				client.commit();
				uncommitCount = 0;
			}
			
		}
		
		client.commit();
		
		
	}
	
	public static void addDocument(SolrInputDocument doc) throws SolrServerException, IOException{
		List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();
		list.add(doc);
		
		addDocuments(list);
		
	}
	

	public static SolrInputDocument convert(TikaModel model) {
		SolrInputDocument inputDoc = new SolrInputDocument();

		Optional<String> title = Optional.ofNullable(model.getMetadata().get(
				TikaCoreProperties.TITLE));
		Optional<String> filename = Optional.ofNullable(model.getFileName());
		Optional<String> content = Optional.ofNullable(model
				.getContentHandler().toString());

		title.ifPresent(_title -> inputDoc.setField(
				SearchDocumentField.TITLE.value(), _title)); //
		filename.ifPresent(_fileName -> inputDoc.setField(
				SearchDocumentField.FILENAME.value(), _fileName)); //
		content.ifPresent(_content -> inputDoc.setField(
				SearchDocumentField.CONTENTS.value(), _content)); //
		
		inputDoc.setField(SearchDocumentField.ID.value(), createId());

		return inputDoc;
	}
	
	private static String createId(){
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}

	public static enum SearchDocumentField {

		TITLE("title"), CONTENTS("content"), FILENAME("filename"),ID("id") ;

		String fieldName;

		private SearchDocumentField(String name) {
			fieldName = name;
		}

		public String value() {
			return fieldName;
		}

	}

}
