<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.library.beans.Book,com.library.actions.*" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <style type="text/css">
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
    <SCRIPT>
        function validate()
        {

            var error=true;
            var name= document.getElementById("name").value;
            var ssn= document.getElementById("ssn").value;
            var address= document.getElementById("address").value;
            var phone= document.getElementById("phone").value;

            if(name==null||name.trim()==""||ssn==null||ssn.trim()==""||address==null||address.trim()==""||phone==null||phone.trim()=="")
            {
                error=false;
                alert(" All fields are mandatory")

            }
            else
            {
                    var  ssnPattern = /^[0-9]{3}\-?[0-9]{2}\-?[0-9]{4}$/;
                    if(!ssnPattern.test(ssn))
                    {
                        error=false;
                        alert(" Enter a valid SSN")
                    }
                    else
                    {
                        var phoneNumberPattern = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;
                        if(!phoneNumberPattern.test(phone))
                        {
                            error=false;
                            alert(" Enter a valid phone no")
                        }

                    }

            }

           if(error)
           {
               document.forms[0].submit();
           }
        }
    </SCRIPT>
</head>

<body >
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">Library Management System</a>
        </div>
    </div>
</nav>
<s:if test="hasActionMessages()">
    <div class="welcome">
        <s:actionmessage/>
    </div>
</s:if>
<s:if test="hasActionErrors()">
    <div class="errors">
        <s:actionerror/>
    </div>
</s:if>
<s:form  class="form-inline" action="CreateBorrower"  onsubmit="event.preventDefault();validate()" method="post">
    <div class="form-group">
        <label> Name </label>
        <s:textfield class="form-control" id="name" key="name"  />
    </div>
    <div class="form-group">
        <label > SSN</label>
        <s:textfield class="form-control" id="ssn" key="ssn" />
    </div>
    <div class="form-group">
        <label > Address</label>
        <s:textfield class="form-control" id="address"  key="address"  />
    </div>
    <div class="form-group">
        <label > Phone No.</label>
        <s:textfield class="form-control" id="phone" key="phone"  />
    </div>
    <s:submit  class="btn btn-default btn-lg" />
</s:form>


<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
    <p>Copyright @ Sakshi Gupta</p>
</footer>

</body>
</html>
