<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 11/05/2015
  Time: 01:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!DOCTYPE html>
    <%@ page isELIgnored="false" %>
    <title>
        <tiles:insertAttribute name="title" />
    </title>
    <script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script>
        $().dropdown('toggle')
    </script>
    <link rel='stylesheet' href='/css/bootstrap.min.css'>
</head>
<body>
    <tiles:insertAttribute name="navigation_bar" />
    <div class="container" style="padding-top: 80px">
        <div class="starter-template">
            <div class="body">
                <tiles:insertAttribute name="body" />
            </div>
        </div>
    </div>
</body>
</html>
