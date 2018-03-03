<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <style>
        .form-control
        {

        }
    </style>
</head>

<body >
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Library Management System</a>
        </div>
    </div>
</nav>
<s:form  class="form-inline" action="issueBook" method="post">
    <div class="form-group">
    <label >Borrower Id</label>
    <s:textfield class="form-control"  key="borrowerId" />
    </div>
    <s:submit  class="btn btn-default btn-lg" key="submit" value="submit" />
</s:form>


<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
    <p>Copyright @ Sakshi Gupta</p>
</footer>

</body>
</html>
