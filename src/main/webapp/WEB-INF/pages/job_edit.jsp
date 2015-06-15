<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        JobEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="padding-top: 80px">
            <div class="starter-template">
                <div class="body">
                    <form method="post" action="/edit_job" commandName="editJob" class="jumbotron form-signin form-horizontal">
                        <h2><spring:message code="locale.editJob"/></h2>
                        <input type="hidden" id="id" name="id" value="${job.id}">
                        <input type="text" id="company" name="company" class="form-control" value="${job.company}">
                        <input type="text" id="post" name="post" class="form-control" value="${job.post}">
                        <br/>
                        <button class="btn btn-primary" type="submit" name="add">Edit Job</button>
                    </form>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
