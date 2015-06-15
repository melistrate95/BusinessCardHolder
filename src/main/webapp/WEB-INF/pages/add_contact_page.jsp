<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 29/05/2015
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        ContactEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="padding-top: 80px">
            <div class="starter-template">
                <div class="body">
                    <div class="jumbotron">
                        <div class="page-header">
                            <h2>  Add Contact. </h2>
                        </div>
                        <form method="post" action="/add_contact" commandName="addContact" >
                            <input type="text" id="type" name="type" class="form-control" placeholder="Type">
                            <input type="text" id="content" name="content" class="form-control" placeholder="Content">
                            <br>
                            <button class="btn btn-primary" type="submit" name="add">Add Contact</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
