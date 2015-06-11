<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 18/05/2015
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='/resources/css/locale_button.css'>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">
                BusinessCardHolder
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <spring:message code="locale.theme"/> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><spring:message code="locale.dark"/></a></li>
                        <li><a href="#"><spring:message code="locale.light"/></a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <a href="/manage">
                            <spring:message code="locale.manage"/>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li>
                        <a href="/registration">
                            <spring:message code="locale.registration"/>
                        </a>
                    </li>
                    <li>
                        <a href="/login">
                            <spring:message code="locale.login"/>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="/personal">${email}</a>
                    </li>
                    <li>
                        <a href="/logout">
                            <spring:message code="locale.logout"/>
                        </a>
                    </li>
                </sec:authorize>
                <li>
                    <div class="btn-toolbar locale-button">
                        <a href="?locale=en">
                            <button class="btn-sm">en</button>
                        </a>
                        <a href="?locale=ru">
                            <button class="btn-sm">ru</button>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
