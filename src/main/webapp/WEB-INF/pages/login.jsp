<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        <spring:message code="locale.login"/>
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container" style="padding-top: 80px">
            <div class="starter-template">
                <div class="body">
                    <form class="jumbotron form-signin form-horizontal" action="/j_spring_security_check" method="post">
                        <h2><spring:message code="locale.loginMessage"/></h2>
                        <div class="form-group form-group-lg">
                            <input type="text" id="mail" name="j_username" class="form-control" placeholder="Email">
                        </div>
                        <div class="form-group form-group-lg">
                            <input type="password" id="password" name="j_password" class="form-control" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-lg btn-primary btn-block">
                                <spring:message code="locale.login"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
