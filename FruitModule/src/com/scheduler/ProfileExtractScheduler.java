package com.scheduler;

import com.processors.ProfileExtractProcessor;

import atg.service.scheduler.ScheduledJob;
import atg.service.scheduler.Scheduler;
import atg.service.scheduler.SingletonSchedulableService;

public class ProfileExtractScheduler extends SingletonSchedulableService {
	
	boolean enabled;
	ProfileExtractProcessor processor;
	
	@Override
	public void doScheduledTask(Scheduler arg0, ScheduledJob arg1) {
		if(isLoggingDebug())
			logDebug("Start - ProfileExtractScheduler:doScheduledTask");
		
		if(isEnabled()){
			getProfileExtractProcessor().extractProfilesToFile();
		}
		
		if(isLoggingDebug())
			logDebug("End - ProfileExtractScheduler:doScheduledTask");
	}

	public void setEnabled(boolean pEnabled){
		this.enabled = pEnabled;
	}
	
	public boolean isEnabled(){
		return this.enabled;
	}
	
	public void setProfileExtractProcessor(ProfileExtractProcessor pProfileExtractProcessor){
		this.processor = pProfileExtractProcessor;
	}
	
	public ProfileExtractProcessor getProfileExtractProcessor(){
		return this.processor;
	}
}    //ProfileExtractSccheduler
