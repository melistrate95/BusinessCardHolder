<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="scripts">
        <script type="text/javascript" src="/resources/js/html2canvas.js"></script>
        <script type="text/javascript" src="/resources/js/add_card.js"></script>
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    </tiles:putAttribute>
    <tiles:putAttribute name="title">
        CardEditor
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 user-info">
                    <div class="row featurette">
                        <nav>
                            <ul class="nav masthead-nav">
                                <li class="active"><a id="jobs-link" href="#"><spring:message code="locale.jobs"/></a></li>
                                <li><a id="contacts-link" href="#"><spring:message code="locale.contacts"/></a></li>
                            </ul>
                        </nav>
                    </div>
                    <div class="row featurette">
                        <div id="jobs" class="col-md-12">
                            <hr>
                            <h2 class="featurette-heading"><spring:message code="locale.jobs"/></h2>
                            <c:forEach items="${jobs}" var="job">
                                <hr>
                                <p class="lead contact"><spring:message code="locale.company"/>: ${job.company}</p>
                                <p class="lead contact"><spring:message code="locale.post"/>: ${job.post}</p>
                            </c:forEach>
                            <hr class="featurette-divider">
                        </div>
                        <div id="contacts" class="col-md-12">
                            <hr>
                            <h2 class="featurette-heading"><spring:message code="locale.contacts"/></h2>
                            <c:forEach items="${contacts}" var="contact">
                                <hr>
                                <p class="lead contact">${contact.type}: ${contact.content}</p>
                            </c:forEach>
                            <hr class="featurette-divider">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 work-space">
                    <div class="contact name-header">
                        <h1 class="featurette-heading">${user.name}</h1>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 card"></div>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-8 col-xs-8 action-space">
                    <div class="form-group">
                        <dutton type="button" id="openModal" class="btn btn-default btn-lg rule-btn">
                            <span class="glyphicon glyphicon-saved"></span>
                        </dutton>
                        <dutton type="button" id="uploadPdfBtn" class="btn btn-default btn-lg rule-btn">
                            <span class="fa fa-file-pdf-o"></span>
                        </dutton>
                        <div id="mySpiner" class="modal spinner" aria-hidden="true">
                            <div class="dot1"></div>
                            <div class="dot2"></div>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                            aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel"><spring:message code="locale.save"/></h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="nameCard" class="col-sm-2 control-label">
                                                <spring:message code="locale.name"/>
                                            </label>
                                            <input type="text" class="form-control" id="nameCard"
                                                placeholder="<spring:message code="locale.name"/>"/>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <a href="/id${user.id}" id="submitBtn" class="btn btn-primary">
                                            <spring:message code="locale.save"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group properties">
                        <dutton type="button" id="deleteBtn" class="btn btn-default btn-lg btn-block delete-btn">
                            <span class="glyphicon glyphicon-trash"></span>
                        </dutton>
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
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
