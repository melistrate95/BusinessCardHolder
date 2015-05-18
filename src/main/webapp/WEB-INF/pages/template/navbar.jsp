<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 18/05/2015
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">
                BusinessCardHolder
            </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Theme <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Dark</a></li>
                        <li><a href="#">Light</a></li>
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
                    <a href="?locale=en">
                        <button class="btn-sm">en</button>
                    </a>
                </li>
                <li>
                    <a href="?locale=ru">
                        <button class="btn-sm">ru</button>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
