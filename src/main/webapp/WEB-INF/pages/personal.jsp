<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 13/05/2015
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel='stylesheet' href='/css/edit_button.css'>
<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        ${user.name}
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron field-edit">
            <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                <a href="/edit/id${user.id}">
                    <button type="button" class="btn btn-lg edit-button"><span class="glyphicon glyphicon-pencil"></span></button>
                </a>
            </c:if>
            <div>
                <h1>Personal data:</h1>
                <h2>${user.name}</h2>
                <h3>${user.mail}</h3>
            </div>
        </div>
        <div class="jumbotron field-edit">
            <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                <a href="/add_contact">
                    <button type="button" class="btn btn-lg edit-button"><span class="glyphicon glyphicon-plus"></span></button>
                </a>
            </c:if>
            <div>
                <h1>Contacts:</h1>
                <c:forEach items="${contacts}" var="contact">
                    <h2>${contact.type}: ${contact.content}</h2>
                </c:forEach>
            </div>
        </div>
        <div class="jumbotron field-edit">
            <div>
                <c:if test="${user.id == online_user.id}">
                    <a href="/id${user.id}/add_card" class="btn btn-lg edit-button">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                </c:if>
                <h1>Cards:</h1>
                <ul class="list-unstyled">
                    <c:forEach items="${cards}" var="card">
                        <li>
                            <div class="form-group-lg">
                                <h2><a href="${card.url}">${card.name}</a></h2>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
