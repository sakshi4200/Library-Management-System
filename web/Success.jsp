<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

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
