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
        Personal page
        <br>
        Name: ${name}
        <br>
        Email: ${this_email}
    </tiles:putAttribute>
</tiles:insertDefinition>
