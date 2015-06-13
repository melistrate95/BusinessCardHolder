<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <%@ page isELIgnored="false" %>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <tiles:insertAttribute name="title" />
    </title>

    <script type="text/javascript" src="/resources/js/jquery.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='/resources/css/bootstrap.css'>
    <link rel='stylesheet' href='/resources/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/css/style.css">

    <link rel="stylesheet" href="/resources/css/jquery-ui.css">

    <script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
    <tiles:insertAttribute name="scripts"/>
</head>
<body>
    <tiles:insertAttribute name="navigation_bar" />
    <tiles:insertAttribute name="body"/>
    <div class="container" style="padding-top: 80px">
        <div class="starter-template">
            <div class="body">

            </div>
        </div>
    </div>
</body>
</html>
