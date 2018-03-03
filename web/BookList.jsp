<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<%@page import="com.library.beans.Book,com.library.actions.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
<script>
    function getCardId() {
       popitup("getBorrowerId.jsp")
    }

    function popitup(url) {
        newwindow=window.open(url,'name','height=200,width=150');
        if (window.focus) {newwindow.focus()}
        return false;
    }
    </script>

</head>
<body>

<div class="table-responsive">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Library Management System</a>
            </div>
        </div>
    </nav>
    <H1>Book  Details</H1>
<table border="1" class="table">
    <tr>
        <th>
            ISBN
        </th>
        <th>
            Book title
        </th>
        <th>
            Book author(s)
        </th>
        <th>
            Book availability
        </th>
    </tr>
    <s:iterator value="bookList" var="book">

        <tr>
            <td>${book.isbn}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td> <button class="btn btn-primary" onclick="getCardId()"> ${book.available}</button></td>
        </tr>
    </s:iterator>
</table>
</div>
</body>
</html>