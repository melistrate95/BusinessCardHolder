<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SAB
  Date: 28/05/2015
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="scripts">
        <script type="text/javascript" src="/js/drag_and_drop.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="title">
        CardEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <div class="contact">
                <h1>${user.name}</h1>
            </div>
            <c:forEach items="${jobs}" var="job">
                <div class="contact">
                    <h3><spring:message code="locale.company"/>: ${job.company}</h3>
                </div>
                <div class="contact">
                    <h4><spring:message code="locale.post"/>: ${job.post}</h4>
                </div>
            </c:forEach>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-3 col-md-3 ">
                <ul id="contacts" class="list-unstyled">
                    <c:forEach items="${contacts}" var="contact">
                        <li>
                            <div class="form-group-lg contact">
                                <div class="input-group">
                                    <span class="input-group-addon">${contact.type}</span>
                                    <div class="form-control">${contact.content}</div></div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-lg-9 col-md-9 ">
                <div class="col-lg-8 col-md-10 col-sm-8 col-xs-8 work-space">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 card"></div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 basket">
                        <span class="glyphicon glyphicon-trash delete-btn"></span>
                    </div>
                </div>
                <div class="col-lg-4 col-md-10 col-sm-8 col-xs-8">
                    <div class="form-group properties">
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.text"/></span>
                            <input type="text" class="form-control" id="textContacts"
                                   placeholder="<spring:message code="locale.text"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.font"/></span>
                            <input type="text" class="form-control" id="fontSize"
                                   placeholder="<spring:message code="locale.font"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.color"/></span>
                            <input type="text" class="form-control" id="fontColor"
                                   placeholder="<spring:message code="locale.color"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.bgcolor"/></span>
                            <input type="text" class="form-control" id="backgroundColor"
                                   placeholder="<spring:message code="locale.bgcolor"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.width"/></span>
                            <input type="text" class="form-control" id="width"
                                   placeholder="<spring:message code="locale.width"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.height"/></span>
                            <input type="text" class="form-control" id="height"
                                   placeholder="<spring:message code="locale.height"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.xposition"/></span>
                            <input type="text" class="form-control" id="positionX"
                                   placeholder="<spring:message code="locale.xposition"/>"/>
                        </div>
                        <div class="input-group input-group-sm prop">
                            <span class="input-group-addon"><spring:message code="locale.yposition"/></span>
                            <input type="text" class="form-control" id="positionY"
                                   placeholder="<spring:message code="locale.yposition"/>"/>
                        </div>
                    </div>
                    <div class="form-inline">
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><spring:message code="locale.name"/></span>
                            <input type="text" class="form-control" id="nameCard"
                                   placeholder="<spring:message code="locale.name"/>"/>
                        </div>
                        <dutton type="button" id="submitBtn" class="btn btn-default btn-block btn-lg">
                            <spring:message code="locale.save"/>
                        </dutton>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
