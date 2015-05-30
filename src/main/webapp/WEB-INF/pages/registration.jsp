<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 11/05/2015
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Registration
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <div class="page-header">
                <h2>  Registration. </h2>
            </div>
            <form method="post" action="/registration" commandName="userRegistration" >
                <input type="text" id="name" name="name" class="form-control" placeholder="Name">
                <input type="text" id="mail" name="mail" class="form-control" placeholder="Mail">
                <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                <br>
                <button class="btn btn-primary" type="submit" name="save">Create account</button>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>