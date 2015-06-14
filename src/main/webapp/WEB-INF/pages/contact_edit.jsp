<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 29/05/2015
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        ContactEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <div class="page-header">
                <h2>  Edit Contact. </h2>
            </div>
            <form method="post" action="/edit_contact" commandName="editContact" >
                <input type="hidden" id="id" name="id" value="${contact.id}">
                <input type="text" id="type" name="type" class="form-control" value="${contact.type}">
                <input type="text" id="content" name="content" class="form-control" value="${contact.content}">
                <br>
                <button class="btn btn-primary" type="submit" name="add">Edit Contact</button>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
