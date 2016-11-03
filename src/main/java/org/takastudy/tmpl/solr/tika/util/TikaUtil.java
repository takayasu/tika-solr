package org.takastudy.tmpl.solr.tika.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.takastudy.tmpl.solr.tika.util.io.TikaModel;
import org.xml.sax.SAXException;

public class TikaUtil {

	public static TikaModel parse(String filePath) throws IOException,
			SAXException, TikaException {
		
		TikaModel model = new TikaModel();
		model.setFileName(filePath);

		try (InputStream is = new BufferedInputStream(new FileInputStream(
				filePath))) {

			Parser parser = new AutoDetectParser();
			ParseContext context = new ParseContext();

			parser.parse(is, model.getContentHandler(), model.getMetadata(),
					context);

		}

		return model;
	}

}
