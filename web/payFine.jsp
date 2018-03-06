<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.library.beans.Book,com.library.actions.*" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <style>
        .welcome {
            color:darkslategrey;
        }
        .error {
            color:Red;
        }
        .welcome li{
            list-style: none;
        }
    </style>
</head>

<body >
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Library Management System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="CreateBorrower.jsp">Create Borrower</a></li>
        </ul>
        <ul class="nav navbar-nav">
            <li><a href="returnBook.jsp">Return Book</a></li>
        </ul>
        <ul class="nav navbar-nav">
            <li><a href="checkFine.jsp">Check Fine</a></li>
        </ul>
        <ul class="nav navbar-nav">
            <li><a href="payFine.jsp">Pay Fine</a></li>
        </ul>
    </div>
</nav>
<s:if test="hasActionMessages()">
    <div class="welcome">
        <s:actionmessage/>
    </div>
</s:if>
<s:form  class="form-inline" action="PayFine" method="post">
    <div class="form-group">
        <label > Enter Librarian Id</label>
        <s:textfield class="form-control"  key="libId" value="" />
    </div>
    <div class="form-group">
        <label > Enter Card Id</label>
        <s:textfield class="form-control"  key="cardId" value="" />
    </div>
    <s:submit  class="btn btn-default btn-lg" key="submit" value="Pay Fine" />
</s:form>


<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
    <p>Copyright @ Sakshi Gupta</p>
</footer>

</body>
</html>
