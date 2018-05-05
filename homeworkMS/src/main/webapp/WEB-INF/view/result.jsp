<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Policy Information</h2>
   <table>
    <tr>
        <td>policyId</td>
        <td>${policyid}</td>
    </tr>
    <tr>
        <td>policyCode</td>
        <td>${policycode}</td>
    </tr>
    <tr>
        <td>policyProductsumamt</td>
        <td>${policyproductsumamt}</td>
    </tr>
     <tr>
        <td>transactionDate</td>
        <td>${transactiondate}</td>
    </tr>
</table>  
</body>
</html>