<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel='stylesheet' href='/resources/css/edit_button.css'>
<%@ page isELIgnored="false" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        ${user.name}
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="row featurette">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="col-lg-6 col-md-6 col-sm-8 col-xs-8 work-space">
                    <div id="myCarousel" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <c:set var="i" value="${0}"/>
                            <c:forEach items="${cards}" var="card">
                                <li data-target="#myCarousel" data-slide-to="${i}"
                                    <c:if test="${i == 0}">class="active"</c:if>></li>
                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>
                            <c:if test="${i == 0}">
                                <li data-target="#myCarousel" data-slide-to="${i}" class="active"></li>
                            </c:if>
                            <c:if test="${i != 0 and user.id == online_user.id}">
                                <li data-target="#myCarousel" data-slide-to="${i}"></li>
                            </c:if>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <c:set var="i" value="${0}"/>
                            <c:forEach items="${cards}" var="card">
                                <div class="item <c:if test="${i == 0}">active</c:if>">
                                    <a href="/id${user.id}/cards/${card.id}">
                                        <img src="${card.url}" alt="${card.name}">
                                    </a>
                                </div>
                                <c:set var="i" value="${i+1}"/>
                            </c:forEach>
                            <c:if test="${i == 0 and user.id != online_user.id}">
                                <div class="item active">
                                    <img src="/resources/cardLogo.jpg">
                                </div>
                            </c:if>
                            <c:if test="${user.id == online_user.id}">
                                <div class="item <c:if test="${i == 0}">active</c:if>">
                                    <a href="/id${user.id}/add_card">
                                        <img class="first-slide" src="/resources/addCard.png" alt="Add card">
                                    </a>
                                </div>
                            </c:if>
                        </div>
                        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-4 col-xs-6">
                    <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                        <a href="/edit/id${user.id}" class="btn btn-lg edit-button">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </a>
                    </c:if>
                    <div>
                        <h1 class="featurette-heading"><spring:message code="locale.personal_data"/>:</h1>
                        <p class="lead">
                            <spring:message code="locale.name"/>: ${user.name}<br/>
                            <spring:message code="locale.email"/>: ${user.mail}
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="jumbotron field-edit">
            <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                <a href="/add_contact">
                    <button type="button" class="btn btn-lg edit-button"><span class="glyphicon glyphicon-plus"></span></button>
                </a>
            </c:if>
            <div>
                <h1><spring:message code="locale.contacts"/>:</h1>
                <c:forEach items="${contacts}" var="contact">
                    <h2>
                        ${contact.type}: ${contact.content}
                        <a href="/edit_contact/${contact.id}">
                            <button type="button" class="btn btn-lg"><span class="glyphicon glyphicon-pencil"></span></button>
                        </a>
                        <a href="/delete_contact/${contact.id}">
                            <button type="button" class="btn btn-lg"><span class="glyphicon glyphicon-remove"></span></button>
                        </a>
                    </h2>

                </c:forEach>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="jumbotron field-edit">
            <c:if test="${user.id == online_user.id || online_user.role == 'ROLE_ADMIN'}">
                <a href="/add_job">
                    <button type="button" class="btn btn-lg edit-button"><span class="glyphicon glyphicon-plus"></span></button>
                </a>
            </c:if>
            <div>
                <h1><spring:message code="locale.jobs"/>:</h1>
                <c:forEach items="${jobs}" var="job">
                    <h2>
                        <spring:message code="locale.company"/>: ${job.company}
                        <a href="/edit_job/${job.id}">
                            <button type="button" class="btn btn-lg"><span class="glyphicon glyphicon-pencil"></span></button>
                        </a>
                        <a href="/delete_job/${job.id}">
                            <button type="button" class="btn btn-lg"><span class="glyphicon glyphicon-remove"></span></button>
                        </a>
                    </h2>
                    <h2><spring:message code="locale.post"/>: ${job.post}</h2>
                    <br><br>
                </c:forEach>
            </div>
        </div>
        <hr class="featurette-divider">
        <div class="jumbotron field-edit">
            <div>
                <c:if test="${user.id == online_user.id}">
                    <a href="/id${user.id}/add_card" class="btn btn-lg edit-button">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                </c:if>
                <h1><spring:message code="locale.cards"/>:</h1>
                <ul class="list-unstyled">
                    <c:forEach items="${cards}" var="card">
                        <li>
                            <div class="form-group-lg">
                                <h2><a href="/id${user.id}/cards/${card.id}">${card.name}</a></h2>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
