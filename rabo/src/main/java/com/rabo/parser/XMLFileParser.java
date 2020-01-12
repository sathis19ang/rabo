package com.rabo.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rabo.config.RaboProps;

public class XMLFileParser extends BaseFileParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLFileParser.class);
	
	public Map<String,String> parseFile(String inputFile){ 
		
		 ClassLoader classloader = new XMLFileParser().getClass().getClassLoader();	
		
		 List<String> checkList = new ArrayList<String>();
		 Map<String,String> outMap = new HashMap<String,String>();
		 Document document = null;

		 //Get Document Builder
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 try {
		 DocumentBuilder builder = factory.newDocumentBuilder();
		  
		 	//Build Document
		 	document = builder.parse(new File(classloader.getResource(inputFile).getFile()));
		 	
	

		 //Normalize the XML Structure
		 document.getDocumentElement().normalize();
		  
		 //root node
		 Element root = document.getDocumentElement();
		 logger.debug(root.getNodeName());
		  
		 //Get all records
		 NodeList nList = document.getElementsByTagName("record");
		 
		 for (int temp = 0; temp < nList.getLength(); temp++)
		 {
		  Node node = nList.item(temp);
		  if (node.getNodeType() == Node.ELEMENT_NODE)
		  {
			  	//Print each records detail
			  	Element eElement = (Element) node;
			  	logger.debug("Reference : "    + eElement.getAttribute("reference"));
			  	logger.debug("EndBalance : "  + eElement.getElementsByTagName("endBalance").item(0).getTextContent());
		     
	        	String reference = eElement.getAttribute("reference");
	        	String endBalance = eElement.getElementsByTagName("endBalance").item(0).getTextContent();
	        	if(checkList.contains(reference)) {
	        		outMap.put(reference, RaboProps.DUPLICATE_ERROR);
	        	}else {		        	
	        		checkList.add(reference);
	        		if(endBalance != "") {
	        			float endBalNum = Float.parseFloat(endBalance);
	        			if(endBalNum < 0) {
	        				outMap.put(reference, RaboProps.ENDBALANCE_NEGATIVE_ERROR);
	        			}
	        		}
	        	}
		  }
		 }
		 }catch(ParserConfigurationException e1) {
			 logger.error("ParserConfigurationException "+e1.getStackTrace());
		 }catch(IOException e2) {
			 logger.error("IOException "+e2.getStackTrace());
		 }catch(Exception e){
			 logger.error("Exception "+e.getStackTrace());
		 }	 
		 return outMap;
	}

}
