package com.rabo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.parser.CSVFileParser;
import com.rabo.parser.ParserClient;
import com.rabo.parser.XMLFileParser;

@Service("parserService")
public class ParserService {

	
	public Map<String,String> genParserServe(String type, String fileName) {

		ParserClient parserClient = null;

		Map<String, String> outMap = new HashMap<String,String>();
		
		System.out.println("type" + type);
		
		if(type.equalsIgnoreCase("xml")) {
			parserClient = new ParserClient(new XMLFileParser());
		
		}else if(type.equalsIgnoreCase("csv")) {
			parserClient = new ParserClient(new CSVFileParser());
		}
		
		if(parserClient != null) {
			outMap = parserClient.parseFile(fileName);
		}
		
		return outMap;
	}
		

}
