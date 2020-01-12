package com.rabo.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;

import com.rabo.config.RaboProps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class CSVFileParser extends BaseFileParser{
	
	private static final Logger logger = LoggerFactory.getLogger(CSVFileParser.class);
	 
	public Map<String,String> parseFile(String inputFile) {
		
		 ClassLoader classloader = new CSVFileParser().getClass().getClassLoader();	
		
		 List<String> checkList = new ArrayList<String>();
		 Map<String,String> outMap = new HashMap<String,String>();
		 
		    try{
		    	
		    	logger.debug("InputFile", inputFile);
		    	
		        Scanner scanner = new Scanner(classloader.getResourceAsStream(inputFile));
		        scanner.nextLine();
		        while (scanner.hasNext()) {
		        	
		        	String line1 = scanner.nextLine();
		        	String[] line = line1.split(",");
		        	
		        	logger.debug("Reference", line[0]);
		        	logger.debug("EndBalance", line[5]);
		        	
		        	if(checkList.contains(line[0])) {
		        		outMap.put(line[0], RaboProps.DUPLICATE_ERROR);
		        	}else {		        	
		        		checkList.add(line[0]);
		        		if(line[5] != "") {
		        			float endBalance = Float.parseFloat(line[5]);
		        			if(endBalance < 0) {
		        				outMap.put(line[0], RaboProps.ENDBALANCE_NEGATIVE_ERROR);
		        			}
		        		}
		        	}
		            
		        }
		    }catch(Exception e2) {
		    	logger.error("Exception " + e2.getStackTrace());
		    }
		    return outMap ;
		
	}
	
}
