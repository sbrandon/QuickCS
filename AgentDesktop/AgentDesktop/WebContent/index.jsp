<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Sign In | QuickCS</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://getbootstrap.com/dist/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="/">QuickCS</a>
        </div>
      </div>
    </nav>
    <div class="container">
		<s:if test="hasActionErrors()">
			<div class="alert alert-danger">
				<s:iterator value="actionErrors">
					<span class="glyphicon glyphicon-remove"></span>&nbsp;<strong>Error!</strong>
					<span class="errorMessage"><s:property escape="false" />
					</span>
				</s:iterator>
			</div>
		</s:if>
	   	<s:form action="login" theme="bootstrap" class="form-signin">
	   		<h2 class="form-signin-heading">Please sign in</h2>
	       	<label for="inputEmail" class="sr-only">Email address</label>
	       	<s:textfield name="email" id="inputEmail" placeholder="Email" cssClass="form-control"></s:textfield>
			<label for="inputPassword" class="sr-only">Password</label>
			<s:password name="password" id="inputPassword" placeholder="Password" cssClass="form-control"></s:password>
			<br/>
			<s:submit type="button" cssClass="btn btn-large btn-primary btn-block">Sign in</s:submit>
			<br/>
			<div class="alert alert-info">Are you new here?<a href="<s:url action="registerLink"/>">&nbsp;Register Now</a></div>
		</s:form>
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="http://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>