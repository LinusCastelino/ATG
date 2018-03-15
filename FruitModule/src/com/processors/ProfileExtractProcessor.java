package com.processors;

import java.util.List;

import atg.nucleus.GenericService;
import atg.repository.Query;
import atg.repository.QueryBuilder;
import atg.repository.QueryExpression;
import atg.repository.Repository;
import atg.repository.RepositoryException;
import atg.repository.RepositoryItem;
import atg.repository.RepositoryView;
import atg.repository.rql.RqlStatement;

public class ProfileExtractProcessor extends GenericService {

	String viewName;
	String displayProp;
	String RQL_QueryCondition;
	List<String> QB_QueryConditions;
	List<String> QB_QueryParams;
	List<String> RQL_QueryParams;
	Repository profileRepo;

	public void extractProfilesToFile(){
		//RepositoryItem[] profiles = extractProfilesUsingRQL();
		RepositoryItem[] profiles = extractProfilesUsingQueryBuilder();
		
		if(profiles != null){
			if(isLoggingDebug())
				logDebug(profiles.length + " profiles extracted for the given condition");
			
			for(RepositoryItem profile : profiles){
				if(isLoggingInfo()){
					logInfo((String)profile.getPropertyValue(getDisplayPropertyName()));
				}
			}
		}
		else{
			if(isLoggingInfo())
				logInfo("ProfileExtractProcessor : No profiles returned for the queried conditions");
		}
	}    //extractProfilesToFiles()
	
	
	public RepositoryItem[] extractProfilesUsingRQL(){
		RepositoryItem[] profiles = null;
		try{
			RepositoryView view = getProfileRepository().getView(getViewName());
			RqlStatement rqlStmt= RqlStatement.parseRqlStatement(getRQLQueryCondition());
			Object params[] = new Object[getRQLQueryParams().size()];
			int i=0;
			for(String param : getRQLQueryParams()){
				params[i++] = param;
			}
			
			profiles = rqlStmt.executeQuery(view, params);
		}
		catch(RepositoryException re){
			if(isLoggingError()){
				logError("Exception encountered while reading data from repository"
						, re);
			}
		}
		catch(Exception e){
			if(isLoggingError()){
				logError("Exception encountered in ProfileExtractProcessor:extractProfilesUsingRQL",
						e);
			}
		}
		
		return profiles;
	}    //extractProfilesUsingRQL()
	
	
	public RepositoryItem[] extractProfilesUsingQueryBuilder(){
		RepositoryItem[] profiles= null;
		try{
			RepositoryView view = getProfileRepository().getView(getViewName());
			QueryBuilder qBuilder = view.getQueryBuilder();
			Query[] queryList = null;
			if(getQBQueryConditions().size() == getQBQueryParams().size()){
				queryList = new Query[getQBQueryConditions().size()];
				for(int i=0 ; i<getQBQueryConditions().size(); i++){
					QueryExpression qExp = qBuilder.createPropertyQueryExpression(getQBQueryConditions().get(i));
					QueryExpression qVal = qBuilder.createConstantQueryExpression(getQBQueryParams().get(i));
					Query query = qBuilder.createComparisonQuery(qExp, qVal, QueryBuilder.EQUALS);
					queryList[i] = query;
				}
				
				Query finalQuery = qBuilder.createOrQuery(queryList);
				profiles = view.executeQuery(finalQuery);
			}
			else{
				if(isLoggingWarning())
					logWarning("Mismatch between query condition and query params count");
			}
			
		}
		catch(RepositoryException re){
			if(isLoggingError()){
				logError("Exception encountered while reading data from repository"
						, re);
			}
		}
		catch(Exception e){
			if(isLoggingError()){
				logError("Exception encountered in ProfileExtractProcessor:extractProfilesUsingRQL",
						e);
			}
		}
		return profiles;
	}
	
	public void setViewName(String pViewName){
		this.viewName = pViewName;
	}
	
	public String getViewName(){
		return this.viewName;
	}
	
	public void setDisplayPropertyName(String pDisplayPropertyName){
		this.displayProp= pDisplayPropertyName;
	}
	
	public String getDisplayPropertyName(){
		return this.displayProp;
	}
	
	public void setRQLQueryCondition(String pRQLQueryCondition){
		this.RQL_QueryCondition = pRQLQueryCondition;
	}
	
	public String getRQLQueryCondition(){
		return this.RQL_QueryCondition;
	}
	
	public void setQBQueryConditions(List<String> pQB_QueryConditions){
		this.QB_QueryConditions = pQB_QueryConditions;		
	}
	
	public List<String> getQBQueryConditions(){
		return this.QB_QueryConditions;
	}
	
	public void setQBQueryParams(List<String> pQB_QueryParams){
		this.QB_QueryParams =pQB_QueryParams;		
	}
	
	public List<String> getQBQueryParams(){
		return this.QB_QueryParams;
	}
	
	public void setRQLQueryParams(List<String> pRQLQueryParams){
		this.RQL_QueryParams=pRQLQueryParams;
	}
	
	public List<String> getRQLQueryParams(){
		return this.RQL_QueryParams;
	}
	
	public void setProfileRepository(Repository pProfileRepo){
		this.profileRepo = pProfileRepo;
	}
	
	public Repository getProfileRepository(){
		return this.profileRepo;
	}
}
