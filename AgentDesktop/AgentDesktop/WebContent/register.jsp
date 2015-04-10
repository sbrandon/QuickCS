<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!--favicon-->
    <link rel="icon" type="image/png" href="graphics/icon.png">  
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://getbootstrap.com/dist/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
<title>Register | QuickCS</title>
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
		<div class="row">
			<s:if test="hasActionErrors()">
				<div class="alert alert-danger">
					<s:iterator value="actionErrors">
						<span class="glyphicon glyphicon-remove"></span>&nbsp;<strong>Error!</strong>
						<span class="errorMessage"><s:property escape="false" />
						</span>
					</s:iterator>
				</div>
			</s:if>
		</div><!-- /row -->
		<div class="row">
		   	<s:form action="register" theme="bootstrap" class="form-signin">
		   		<h2 class="form-signin-heading">New User</h2>
		       	<label for="inputEmail" class="sr-only">Email address</label>
		       	<s:textfield name="email" id="inputEmail" placeholder="Email" cssClass="form-control"></s:textfield>
		       	<label for="firstName" class="sr-only">First Name</label>
		       	<s:textfield name="firstName" id="firstName" placeholder="First Name" cssClass="form-control"></s:textfield>
		       	<label for="lastName" class="sr-only">Last Name</label>
		       	<s:textfield name="lastName" id="lastName" placeholder="Last Name" cssClass="form-control"></s:textfield>
				<label for="inputPassword" class="sr-only">Password</label>
				<s:password name="password" id="inputPassword" placeholder="Password" cssClass="form-control"></s:password>
				<br/>
				<label for="verification" class="sr-only">Verification Code</label>
				<s:password name="verification" id="verification" placeholder="Verification Code" cssClass="form-control"></s:password>
				<br/>
				<s:submit type="button" cssClass="btn btn-large btn-primary btn-block">Register</s:submit>
			</s:form>
		</div><!-- /row -->	
	</div><!-- /container -->
</body>
</html>