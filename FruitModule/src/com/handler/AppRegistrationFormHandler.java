package com.handler;

import atg.droplet.DropletException;
import atg.droplet.GenericFormHandler;
import atg.repository.MutableRepository;
import atg.repository.MutableRepositoryItem;
import atg.repository.Repository;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;

public class AppRegistrationFormHandler extends GenericFormHandler {
	
	private static final String LOGIN_PROPERTY="login";
	private static final String PWD_PROPERTY="password";
	
	private String login;
	private String password;
	private String registrationSuccessURL;
	private String registrationErrorURL;
	private String viewName;
	private Repository registrationRepo;
	
	public void handleCreate(DynamoHttpServletRequest request, DynamoHttpServletResponse response){
		preCreateUser(request, response);
		
		registerUser(request, response);
		
		postCreateUser(request, response);
	}
	
	public void preCreateUser(DynamoHttpServletRequest pRequest,
			DynamoHttpServletResponse pResponse) {
		
		String login = pRequest.getParameter("login");
		String pwd = pRequest.getParameter("password");
		
		if(isLoggingDebug()){
			logDebug("Login - "+login+" || Password - "+pwd);
			logDebug("getLogin - "+getLogin()+" || getPassword - "+getPassword());
		}
		
	}    //preCreateUser()
	
	public void registerUser(DynamoHttpServletRequest pRequest, DynamoHttpServletResponse response){
		//Adding user to the custom registration repository
		try{
			MutableRepository regRepo = (MutableRepository)getRegistrationRepository();
			MutableRepositoryItem regRepoItem = regRepo.createItem(getViewName());
			
			regRepoItem.setPropertyValue(LOGIN_PROPERTY, getLogin());
			regRepoItem.setPropertyValue(PWD_PROPERTY, getPassword());
			
			regRepo.addItem(regRepoItem);
			
			if(isLoggingDebug())
				logDebug("User registered successfully");
		}
		catch(Exception e){
			String errorMsg = "Exception encountered while adding user " +
					"to the RegistrationRepository";
			if(isLoggingError()){
				logError(errorMsg, e);
			}
			addFormException(new DropletException(errorMsg +" : "+e.getMessage()));
		}
	}    //registerUser()
	
	public void postCreateUser(DynamoHttpServletRequest pRequest,
			DynamoHttpServletResponse pResponse)  {
		try{
			checkFormRedirect(getCreateSuccessURL(), getCreateErrorURL(), pRequest, pResponse);
		}
		catch(Exception e){
			if(isLoggingError())
				logError("AppRegistrationFormHandler:postCreateUser " +
						"- Error occurred while redirecting user",e);
		}
	}    //postCreateUser()
	
	public void setLogin(String pLogin){
		this.login = pLogin;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public void setPassword(String pPassword){
		this.password = pPassword;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setCreateSuccessURL(String pCreateSuccessURL){
		this.registrationSuccessURL = pCreateSuccessURL;
	}
	
	public String getCreateSuccessURL(){
		return this.registrationSuccessURL;
	}
	
	public void setCreateErrorURL(String pCreateErrorURL){
		this.registrationErrorURL = pCreateErrorURL;
	}
	
	public String getCreateErrorURL(){
		return this.registrationErrorURL;
	}
	
	public void setViewName(String pViewName){
		this.viewName = pViewName;
	}
	
	public String getViewName(){
		return this.viewName;
	}
	
	public void setRegistrationRepository(Repository pRegistrationRepository){
		this.registrationRepo = pRegistrationRepository;
	}
	
	public Repository getRegistrationRepository(){
		return this.registrationRepo;
	}
}    //AppRegistrationFormHandler
