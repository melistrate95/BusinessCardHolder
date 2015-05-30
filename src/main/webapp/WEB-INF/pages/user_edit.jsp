<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 13/05/2015
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Personal
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <div class="page-header">
                <h2>  Edit Personal Data. </h2>
            </div>
            <form method="post" action="/save" commandName="userSave" >
                <input type="text" id="name" name="name" class="form-control" value="${user.name}">
                <input type="text" id="mail" name="mail" class="form-control" value="${user.mail}">
                <input type="text" id="password" name="password" class="form-control" value="${user.password}">
                <input type="text" id="role" name="role" class="form-control" value="${user.role}">
                <br>
                <button class="btn btn-primary" type="submit" name="save">Save</button>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
