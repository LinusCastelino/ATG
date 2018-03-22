package com.pipeline;

import java.io.IOException;

import javax.servlet.ServletException;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.pipeline.InsertableServletImpl;

public class CustomDAFPipelineServlet2 extends InsertableServletImpl {
	
	@Override
	public void service(DynamoHttpServletRequest pRequest,
			DynamoHttpServletResponse pResponse) throws IOException,
			ServletException {

		if(isLoggingInfo()){
			logInfo("Inside CustomDAFPipelineServlet2:service()");
		}
		
		super.service(pRequest, pResponse);    //passRequest() 
	}

}
