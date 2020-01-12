
package com.rabo.parser;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class ParserClient {
	
	private BaseFileParser baseFileParser;
	
	 public ParserClient(BaseFileParser baseFileParser){
		    this.baseFileParser=baseFileParser;
	 }  
	 public Map<String, String> parseFile(String filename){
		   return baseFileParser.parseFile(filename);
	 } 

}
