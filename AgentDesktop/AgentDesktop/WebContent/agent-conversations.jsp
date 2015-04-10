<%@ include file="header.jsp" %>
    <div class="container-fluid" id="maincontainer">
	    
	        <div class="col-sm-4" id="conversations">
	            <ul class="list-group" id="cslist-group">
				    <!-- List of anchor links to conversations -->
				</ul>
	        </div>

	        <div class="col-sm-8 col-md-offset-1" id="agentchat">
	        	<div class="row"  id="agentchatoutput">
			        
			        <ul class="list-group">
			        	<s:iterator value="messages">
			        		<li class="list-group-item messagetoagent">
				        		<s:property value="content"/>
				        		<br>
				        		From: 
			        		</li>
			        	</s:iterator>
				</ul>
		        </div>
		        
		        <div class="row" id="agentchatinput">
			        <s:form action="sendMessage" theme="bootstrap">
						<s:hidden name="conversationId" value="%{conversationId}"/>
						<s:textarea class="form-control" rows="4" name="content"></s:textarea>
						   	<div>
						   		<div class="btn-group dropup">
									<button type="button" class="btn btn-default">Change Status</button>
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						  				<span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
						  				<li><a onclick="">Open</a></li>
										<li><a onclick="">Waiting</a></li>
										<li><a onclick="">Close</a></li>
										<li class="divider"></li>
										<li><a onclick="">Reassign</a></li>
									</ul>
								</div>
						   		<s:submit type="button" cssClass="btn btn-success">Send</s:submit>
						  		<div id="buttons_right" class="btn-group" role="group">
									<button type="button" class="btn btn-default">Payment Request</button>
									<button type="button" class="btn btn-default">Attachement</button>
									<div class="btn-group dropup">
									 <button type="button" class="btn btn-default">Insert</button>
									 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							   			<span class="caret"></span>								    
							 		</button>
							 		</div>
							 		<ul class="dropdown-menu" role="menu">
							   			<li><a onclick="">Link</a></li>
										<li><a onclick="">Symbol</a></li>
										<li><a onclick="">Other</a></li>
							 		</ul>
								</div>
							</div>
			        </s:form>
			    </div>
	</div>

	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

</body>
</html>
