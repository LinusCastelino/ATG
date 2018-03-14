<html>
	<head>
		<title>Fruit App - Register</title>
		<style>
			#login:focus, #password:focus{
				background-color: #add8e6;
			}
			#regForm{
				margin: 10%;
				padding: 5%;
				border: 3px solid black;
				position:relative;
				text-align: center;
			}
		</style>
	</head>
<dsp:page>
	<dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
	<dsp:importbean bean="/atg/userprofiling/RegistrationFormHandler" />
	<dsp:importbean bean="/atg/userprofiling/Profile" />
	
	<dsp:form id="regForm" method="POST">
		<dsp:input type="hidden" bean="RegistrationFormHandler.createSuccessURL" value="/asdf.jsp"/>
		<dsp:input type="hidden" bean="RegistrationFormHandler.createErrorURL" value="../index.jsp"/>
		
		User ID : <dsp:input type="text" bean="RegistrationFormHandler.value.login" id="login" />
		<br><br>
		Password : <dsp:input type="password" bean="RegistrationFormHandler.value.password" id="password" />
		<br><br>
		<dsp:input type="submit" bean="RegistrationFormHandler.create" value="Register" />
	</dsp:form>
	
</dsp:page>

</html>