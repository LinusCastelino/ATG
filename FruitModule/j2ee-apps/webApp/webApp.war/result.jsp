<html>
	<head>
		<title>Registration Result</title>
		<style>
			#resultDiv{
				margin: 10%;
				padding: 5%;
				border: 3px solid black;
				position:relative;
				text-align: center;
			}
			
			#successMsg{
				background-color:#b3d1ff;
			}
			
			#errorMsg{
				background-color:#ff4d4d;
			}
		</style>
	</head>
	
	<body>
		<dsp:page>
			<dsp:importbean bean="/atg/userprofiling/RegistrationFormHandler" />
			<dsp:importbean bean="/atg/dynamo/droplet/Switch" />
			<dsp:importbean bean="/atg/dynamo/droplet/ErrorMessageForEach" />
			
			<dsp:getvalueof bean="RegistrationFormHandler.login" var="loginId"/>
			
			<div id="resultDiv">
				<dsp:droplet name="Switch">
					<dsp:param name="value" bean="RegistrationFormHandler.formError" />
					<dsp:oparam name="true">
							<dsp:droplet name="ErrorMessageForEach">
								<dsp:param name="exceptions" bean="RegistrationFormHandler.formExceptions" />
								<dsp:oparam name="outputStart">
									<div id="errorMsg">
										Error in registering login ${loginId}.
										<ol>
								</dsp:oparam>
								
								<dsp:oparam name="output">
									<li><dsp:valueof param="message" /></li>
								</dsp:oparam>
								
								<dsp:oparam name="outputEnd">
										</ol>
									</div>
								</dsp:oparam>
							</dsp:droplet>
					</dsp:oparam>
					
					<dsp:oparam name="false">
						<div id="successMsg">
							Login ${loginId} has been registered.
						</div>
					</dsp:oparam>
					
				</dsp:droplet>
			</div>
		</dsp:page>
	</body>
</html>