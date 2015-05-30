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
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <%@ page isELIgnored="false" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>
        <tiles:insertAttribute name="title" />
    </title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='/css/bootstrap.min.css'>
    <link href="css/style.css" rel="stylesheet">

    <link rel="stylesheet" href="css/jquery-ui.css">

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <script type="text/javascript" src="js/drag_and_drop.js"></script>
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
