<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 01/06/2015
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        CardEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="padding-top: 80px">
            <div class="starter-template">
                <div class="body">
                    <div class="jumbotron">
                        <div class="page-header">
                            <h2>  Add Job. </h2>
                        </div>
                        <form method="post" action="/add_job" commandName="addJob" >
                            <input type="text" id="company" name="company" class="form-control" placeholder="Company">
                            <input type="text" id="post" name="post" class="form-control" placeholder="Post">
                            <br>
                            <button class="btn btn-primary" type="submit" name="add">Add Job</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
