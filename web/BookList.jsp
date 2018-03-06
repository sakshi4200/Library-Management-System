<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="/struts-tags" prefix="s"%>
<%@page import="com.library.beans.Book,com.library.actions.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <%--<script>--%>
        <%--$(document).ready(function(){--%>
            <%--$("button").click(function(){--%>
            <%--$("#a").add--%>
            <%--$.ajax({--%>
                <%--method: "POST",--%>
                <%--url: "BookBorrow",--%>
                <%--data: { "isbn" : "123" },--%>


        <%--});  });--%>
        <%--});--%>

    <%--</script>--%>
<script>
    function getBorrowerId(isbn,av)
    {   if(av=="Available") {
        alert(isbn);
        document.getElementById("isbn").value = isbn;
        Document.form[0].action = "BookBorrow";
        Document.form.submit();
    }
    else
    {

    }

    }
</script>

</head>
<body>
<form method="get" action="BookBorrow">
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
        <th>
            Book availability
        </th>
    </tr>

    <s:hidden key="isbn" id="isbn"></s:hidden>
    <s:iterator value="bookList" var="book">

        <tr>
            <td>${book.isbn}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td> <button id="id1" onclick="getBorrowerId('${book.isbn}','${book.available}')" class="btn btn-primary" > ${book.available}</button></td>
            <td><p id="a"></p></td>
        </tr>

    </s:iterator>

</table>
</div>
</form>
</body>
</html>