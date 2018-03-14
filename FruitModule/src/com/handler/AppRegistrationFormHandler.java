package com.handler;

import java.io.IOException;

import javax.servlet.ServletException;

import atg.commerce.profile.CommerceProfileFormHandler;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;





public class AppRegistrationFormHandler extends CommerceProfileFormHandler {

	@Override
	protected void postCreateUser(DynamoHttpServletRequest pRequest,
			DynamoHttpServletResponse pResponse) throws ServletException,
			IOException {
		
		
		
		super.postCreateUser(pRequest, pResponse);
	}
	
	@Override
	protected void preCreateUser(DynamoHttpServletRequest pRequest,
			DynamoHttpServletResponse pResponse) throws ServletException,
			IOException {
		
		String login = (String)getValue().get("login");
		String pwd = (String)getValue().get("password");
		
		logDebug("Login - "+login+" || Password - "+pwd);
		
		super.preCreateUser(pRequest, pResponse);
	}
	
}
