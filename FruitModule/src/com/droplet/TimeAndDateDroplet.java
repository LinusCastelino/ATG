package com.droplet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.DynamoServlet;

public class TimeAndDateDroplet extends DynamoServlet{

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "hh:mm:ss";
	private static final String PARAM_DATE = "date";
	private static final String PARAM_TIME = "time";
	private static final String OUT_PARAM = "output";
	
	public void service(final DynamoHttpServletRequest request, final DynamoHttpServletResponse response){
		if(isLoggingDebug())
			logDebug("Start - TimeAndDateDroplet:service()");
		
		try{
			String reqTimeZone = request.getParameter("timeZone");
			Date date = new Date();
			TimeZone tz = TimeZone.getTimeZone(reqTimeZone);
			Calendar calendar = Calendar.getInstance(tz);
			calendar.setTime(date);
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.setTimeZone(tz);
			
			sdf.applyPattern(DATE_FORMAT);
			String formattedDate = sdf.format(calendar.getTime());
			if(isLoggingDebug())
				logDebug("formattedDate : "+formattedDate);
			
			sdf.applyPattern(TIME_FORMAT);
			String formattedTime = sdf.format(calendar.getTime());
			if(isLoggingDebug())
				logDebug("formattedTime : "+formattedTime);
			
			request.setParameter(PARAM_DATE, formattedDate);
			request.setParameter(PARAM_TIME, formattedTime);
			request.serviceLocalParameter(OUT_PARAM, request, response);
			
		}catch(Exception e){
			if(isLoggingError())
				logError("Exception encountered while trying to generate time and date", e);
		}
		
		if(isLoggingDebug())
			logDebug("End - TimeAndDateDroplet:service()");
	}
}
