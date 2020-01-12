package com.rabo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RaboProps {
	
	public static String DUPLICATE_ERROR;
	public static String ENDBALANCE_NEGATIVE_ERROR;
	
	public RaboProps() {
		
	}
	
	 @Value("${rabo.DUPLICATE_ERROR}")
	 private void setDUPLICATE_ERROR(String DUPLICATE_ERROR) {
		 this.DUPLICATE_ERROR=DUPLICATE_ERROR;
	 }
	 
	 @Value("${rabo.ENDBALANCE_NEGATIVE_ERROR}")
	 private void setENDBALANCE_NEGATIVE_ERROR(String ENDBALANCE_NEGATIVE_ERROR) {
		 this.ENDBALANCE_NEGATIVE_ERROR=ENDBALANCE_NEGATIVE_ERROR;
	 }
	

}
