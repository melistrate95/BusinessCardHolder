<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 11/05/2015
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Log In
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="col-md-6 col-md-offset-3">
            <div class="page-header">
                <h2>  Log in. </h2>
            </div>
            <form name="loginForm" class="form-horizontal" action="/j_spring_security_check" method="post" id="loginForm">
                <input type="text" id="mail" name="j_username" class="form-control" placeholder="Email">
                <input type="password" id="password" name="j_password" class="form-control" placeholder="Password">
                <br>
                <button type="submit" class="btn btn-primary">     Sign in     </button>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
