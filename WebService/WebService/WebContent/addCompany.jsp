<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Company | QuickCS Web Service</title>
</head>
<body>
	<h1>Add a Company</h1>
	<s:form action="submitCompany" method="post">
		<s:textfield name="companyName" placeholder="Company Name"></s:textfield>
		<s:textfield name="ipAddress" placeholder="IP Address"></s:textfield>
		<s:textfield name="phoneNumber" placeholder="Phone Number"></s:textfield>
		<s:submit type="button">Add Company</s:submit>
	</s:form>
</body>
</html>