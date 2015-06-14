<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 01/06/2015
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        JobEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <div class="page-header">
                <h2>  Edit Job. </h2>
            </div>
            <form method="post" action="/edit_job" commandName="editJob" >
                <input type="hidden" id="id" name="id" value="${job.id}">
                <input type="text" id="company" name="company" class="form-control" value="${job.company}">
                <input type="text" id="post" name="post" class="form-control" value="${job.post}">
                <br>
                <button class="btn btn-primary" type="submit" name="add">Edit Job</button>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
