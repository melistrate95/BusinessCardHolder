<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        ContactEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="padding-top: 80px">
            <div class="starter-template">
                <div class="body">
                    <form method="post" action="/edit_job" commandName="editJob" class="jumbotron form-signin form-horizontal">
                        <h2><spring:message code="locale.editContact"/></h2>
                        <input type="hidden" id="id" name="id" value="${contact.id}">
                        <input type="text" id="type" name="type" class="form-control" value="${contact.type}">
                        <input type="text" id="content" name="content" class="form-control" value="${contact.content}">
                        <br>
                        <button class="btn btn-primary" type="submit" name="add">Edit Contact</button>
                    </form>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
