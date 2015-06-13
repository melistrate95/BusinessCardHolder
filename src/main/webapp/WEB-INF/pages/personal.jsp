<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel='stylesheet' href='/resources/css/edit_button.css'>

<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="scripts">
        <link rel='stylesheet' href='/resources/css/jquery.carousel-3d.default.css'>
        <script type="text/javascript" src="/resources/js/jquery.resize.js"></script>
        <script type="text/javascript" src="/resources/js/jquery.waitforimages.js"></script>
        <script type="text/javascript" src="/resources/js/modernizr.custom.23177.js"></script>
        <script type="text/javascript" src="/resources/js/jquery.carousel-3d.js"></script>
        <script type="text/javascript" src="/resources/js/personal.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="title">
        ${user.name}
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="row featurette">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="container">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 personal-data">
                        <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                            <a href="/edit/id${user.id}" class="btn btn-lg edit-button">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>
                        </c:if>
                        <c:if test="${user.id == online_user.id}">
                            <a href="/id${user.id}/add_card" class="btn btn-lg add-button">
                                <span class="glyphicon glyphicon-plus"></span>
                            </a>
                        </c:if>
                        <h1 class="featurette-heading">${user.name} [ ${user.mail} ]</h1>
                    </div>
                </div>
                <div id="wrapper" class="space-simple-carousel">
                    <div id="myCarousel" data-carousel-3d>
                        <c:set var="i" value="${0}"/>
                        <c:forEach items="${cards}" var="card">
                            <div>
                                <div class="remove-preview">
                                    <a href="#"><span class="glyphicon glyphicon-remove"></span></a>
                                </div>
                                <div class="preview">
                                    <a href="/id${user.id}/cards/${card.id}"><h2 class="card-heading">${card.name}</h2></a>
                                </div>
                                <img src="${card.url}" style="width: 100%;"  <c:if test="${i == 0}">selected</c:if>/>
                            </div>
                            <c:set var="i" value="${i+1}"/>
                        </c:forEach>
                    </div>
                    <div class="left carousel-control" id="prev-button">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </div>
                    <div class="right carousel-control" id="next-button">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <hr class="featurette-divider">
            <div class="row featurette">
                <div class="col-md-7">
                    <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                        <a href="/add_contact" class="btn btn-lg add-button"><span class="glyphicon glyphicon-plus"></span></a>
                    </c:if>
                    <h2 class="featurette-heading"><spring:message code="locale.contacts"/></h2>
                    <c:forEach items="${contacts}" var="contact">
                        <p class="lead">${contact.type}: ${contact.content}</p>
                        <hr>
                    </c:forEach>
                </div>
                <div class="col-md-5">
                    <img class="featurette-image img-responsive center-block" alt="Contacts" src="/resources/images/contacts.jpg" data-holder-rendered="true" kasperskylab_antibanner="on">
                </div>
            </div>
            <hr class="featurette-divider">
            <div class="row featurette">
                <div class="col-md-5 banner">
                    <img class="featurette-image img-responsive center-block" alt="Jobs" src="/resources/images/jobs.jpg" data-holder-rendered="true" kasperskylab_antibanner="on">
                </div>
                <div class="col-md-7 banner">
                    <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                        <a href="/add_job" class="btn btn-lg add-button"><span class="glyphicon glyphicon-plus"></span></a>
                    </c:if>
                    <h2 class="featurette-heading"><spring:message code="locale.jobs"/></h2>
                    <c:forEach items="${jobs}" var="job">
                        <p class="lead">
                            <spring:message code="locale.company"/>: ${job.company}<br/>
                            <spring:message code="locale.post"/>: ${job.post}
                        </p>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
