package org.takastudy.tmpl.solr.tika.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TikaTest {
	
	private static final String TESTFILENAME1 = "osstext_ver1.0.1.pdf";
	private static final String TESTFILENAME2 = "session.docx";

	@Test
	public void test() throws FileNotFoundException, IOException {
		parseFile(TESTFILENAME1);
	}
	
	@Test
	public void wordTest() throws FileNotFoundException, IOException {
		parseFile(TESTFILENAME2);
	}
	
	private void parseFile(String fileName) throws FileNotFoundException, IOException{
		try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            Parser parser = new AutoDetectParser();
            ContentHandler contentHandler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            parser.parse(is, contentHandler, metadata, context);
            
            System.out.println("Title:"+metadata.get(TikaCoreProperties.TITLE));
            System.out.println("Contents");
            System.out.println(contentHandler.toString());
            
            
            
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}
	

}
