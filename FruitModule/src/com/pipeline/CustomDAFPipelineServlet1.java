package com.pipeline;

import java.io.IOException;

import javax.servlet.ServletException;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.pipeline.PipelineableServletImpl;

public class CustomDAFPipelineServlet1 extends PipelineableServletImpl {

	@Override
	public void service(DynamoHttpServletRequest pRequest,
			DynamoHttpServletResponse pResponse) throws IOException,
			ServletException {

		if(isLoggingInfo())
			logInfo("Inside CustomDAFPipelineServlet1:service()");
		
		super.service(pRequest, pResponse);    //passRequest()
	}

}
