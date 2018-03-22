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
			#timeAndDatePlaceHolder{
				position : absolute;
				top : 50px;
				right : 50px;
				border : 2px solid black;
				padding : 5px;
			}
			
		</style>
	</head>
	
	<body>
		<dsp:page>
			<dsp:importbean bean="/atg/userprofiling/RegistrationFormHandler" />
			<dsp:importbean bean="/atg/userprofiling/Profile" />
			<dsp:importbean bean="/com/droplet/TimeAndDateDroplet" />

			<c:set var="timezone" value="America/New_York" />
			
			<dsp:form id="regForm" method="POST">
				<dsp:input type="hidden" bean="RegistrationFormHandler.createSuccessURL" value="../result.jsp"/>
				<dsp:input type="hidden" bean="RegistrationFormHandler.createErrorURL" value="../result.jsp"/>
				
				User ID : <dsp:input type="text" bean="RegistrationFormHandler.login" id="login" name="login" />
				<br><br>
				Password : <dsp:input type="password" bean="RegistrationFormHandler.password" id="password" name="password" />
				<br><br>
				<dsp:input type="submit" bean="RegistrationFormHandler.create" value="Register" />
			</dsp:form>
			
			<div id="timeAndDatePlaceHolder">
				<dsp:droplet name="TimeAndDateDroplet">
					<dsp:param name="timeZone" value="${timezone}" />
					<dsp:oparam name="output">
						<dsp:getvalueof var="tzDate" param="date" />
						<dsp:getvalueof var="tzTime" param="time" />
						
						Date : ${tzDate}  
						<br />
						Time : ${tzTime}
						<br />
						Time Zone : ${timezone}
					</dsp:oparam>
				</dsp:droplet>
			</div>
		</dsp:page>
	</body>
</html>