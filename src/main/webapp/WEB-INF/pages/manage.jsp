<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 13/05/2015
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Manage users
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <script type="text/javascript" src="/js/change_user.js"></script>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Reference</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${array}" var="tab">
                <tr>
                    <td>${tab.name}</td>
                    <td>${tab.mail}</td>
                    <td class="change">${tab.password}</td>
                    <td>${tab.role}</td>
                    <td><a href="http://localhost:8080/id${tab.id}">id${tab.id}</a> </td>
                    <td><a href="/manage/delete/id${tab.id}">Delete</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/manage/save"><button class="btn btn-primary">Save change</button></a>
    </tiles:putAttribute>
</tiles:insertDefinition>
