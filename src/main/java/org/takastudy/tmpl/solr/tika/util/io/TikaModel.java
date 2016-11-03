package org.takastudy.tmpl.solr.tika.util.io;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class TikaModel {
	
	private Metadata metadata;
	private ContentHandler contentHandler;
	private String fileName;
	
	public TikaModel(){
        contentHandler = new BodyContentHandler(-1);
        metadata = new Metadata();
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public ContentHandler getContentHandler() {
		return contentHandler;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}
