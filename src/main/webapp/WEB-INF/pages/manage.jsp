<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 13/05/2015
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Manage users
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <script type="text/javascript" src="/js/change_user.js"></script>
        <div class="container" style="padding-top: 80px">
            <div class="starter-template">
                <div class="body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th><spring:message code="locale.name"/></th>
                            <th><spring:message code="locale.email"/></th>
                            <th><spring:message code="locale.password"/></th>
                            <th><spring:message code="locale.role"/></th>
                            <th><spring:message code="locale.reference"/></th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${array}" var="tab">
                                <tr>
                                    <td>${tab.name}</td>
                                    <td>${tab.mail}</td>
                                    <td>${tab.password}</td>
                                    <td>${tab.role}</td>
                                    <td><a href="http://localhost:8080/id${tab.id}">id${tab.id}</a> </td>
                                    <c:if test="${tab.id != online_user.id}">
                                        <td><a href="/manage/delete/id${tab.id}"><spring:message code="locale.delete"/></a> </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
