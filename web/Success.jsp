<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

    <style type="text/css">
        .welcome {
            background-color:#DDFFDD;
            border:1px solid #009900;
            width:200px;
        }
        .welcome {
            background-color:Red;
            width:200px;
        }
        .welcome li{
            list-style: none;
        }
    </style>

</head>
<body>
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

</body>
</html>
