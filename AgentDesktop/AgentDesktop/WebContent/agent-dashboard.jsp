<%@ include file="header.jsp" %>
<div class="container-fluid">                        
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <h3>Conversations</h3>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#">New conversations</a></li>
            <li><a href="#">Your conversations</a></li>
            <li><a href="#">History</a></li>
          </ul> 
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Dashboard</h1>
          <div class="row">
            <div class="col-lg-3 col-md-6">
              <div class="panel panel-primary">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-xs-3">
                      <i class="fa fa-comments fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge">${openConversations.size()}</div>
                        <div class="big">Current!</div>
                    </div>
                  </div>
                </div>
                <a href="/agentconversations.html">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
              </div>
             </div>
          <div class="col-lg-3 col-md-6">
            <div class="panel panel-green">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-3">
                    <i class="fa fa-envelope fa-5x"></i>
                  </div>
                  <div class="col-xs-9 text-right">
                    <div class="huge">${newConversations.size()}</div>
                    <div class="big">New!</div>
                  </div>
                </div>
              </div>
              <a href="#">
                <div class="panel-footer">
                  <span class="pull-left">View Details</span>
                  <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                  <div class="clearfix"></div>
                </div>
              </a>
            </div>
          </div>
          <div class="col-lg-3 col-md-6">
            <div class="panel panel-yellow">
              <div class="panel-heading">
                <div class="row">
                  <div class="col-xs-3">
                          <i class="fa fa-exclamation-triangle fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">${waitingConversations.size()}</div>
                            <div class="big">Waiting for answer!</div>
                        </div>
                    </div>
                </div>
                <a href="#">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-red">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-bomb fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">${overdueConversations.size()}</div>
                            <div class="big">Overdue!</div>
                        </div>
                    </div>
                </div>
                <a href="#">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
    </div>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>              
                <tr>
                  <th datafield="ConversationId" class="table_id">ID</th>
                  <th datafield="DateOfOrigin" class="table_open">Received</th>
                  <th datafield="ConversationTopoic">Subject</th>
                  <th datafield="ConversationStatus"class="table_status">Status</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <s:iterator value="conversations">
                	<tr>
                		<td><s:property value="id"/></td>
                		<td><s:property value="date"/></td>
                		<td></td>
                		<td><s:property value="status"/></td>
                		<td>
                			<s:form action="viewConversation" theme="bootstrap">
								<s:hidden name="conversationId" value="%{id}"/>
								<s:submit cssClass="btn btn-default btn-xs btn-block" type="button"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;View Conversation</s:submit>
							</s:form>
                		</td>
                	</tr>
                </s:iterator>
              </tbody>
            </table>
          </div>
        </div>
      </div>    
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="http://getbootstrap.com/assets/js/docs.min.js"></script>
</body>
</html>