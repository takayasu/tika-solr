package org.takastudy.tmpl.solr.tika;


import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.exception.TikaException;
import org.takastudy.tmpl.solr.tika.util.SolrUtil;
import org.takastudy.tmpl.solr.tika.util.TikaUtil;
import org.takastudy.tmpl.solr.tika.util.io.TikaModel;
import org.xml.sax.SAXException;

public class Main {

	private static final String OPTION_FILE = "file";

	public static void main(String[] args) {
		
		try {
			CommandLine cl = new DefaultParser().parse(getOptions(),args);
			
			String filePath = cl.getOptionValue(OPTION_FILE);
			
			TikaModel model = TikaUtil.parse(filePath);
			
			SolrInputDocument doc = SolrUtil.convert(model);
			SolrUtil.addDocument(doc);
			
			
		} catch (ParseException | IOException | SAXException | TikaException | SolrServerException e) {
			e.printStackTrace();
		}

	}
	
	private static Options getOptions(){
		Options ops = new Options();
		
		ops.addOption(
				Option.builder(OPTION_FILE).required().hasArg().argName(OPTION_FILE).build()
				);		
		
		return ops;
	}
	

}
