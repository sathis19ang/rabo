package com.rabo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rabo.parser.BaseFileParser;
import com.rabo.parser.CSVFileParser;
import com.rabo.parser.ParserClient;
import com.rabo.parser.XMLFileParser;
import com.rabo.service.ParserService;

@CrossOrigin(origins = "*")
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/parser")
public class ParserController {
	
	@Autowired
	ParserService parserService;
	
	@GetMapping(value = "/{type}/{filename}")
	public Map<String,String> postUpdateStatusSaleOrder(@PathVariable("type") String type,@PathVariable("filename") String filename) {
		Map<String, String> outMap= new HashMap<String,String>();
		System.out.println("type1" + type);
		System.out.println("filename1" + filename);
		outMap = parserService.genParserServe(type, filename);
		
		return outMap;
	}
}
