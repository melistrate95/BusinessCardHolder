<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="scripts">
        <script type="text/javascript" src="/resources/js/show_card.js"></script>
    </tiles:putAttribute>
    <tiles:putAttribute name="title">
        CardViewer
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="row">
            <div class="jumbotron">
                <div>
                    <h2><spring:message code="locale.cardName"/>: <span>${card.name}</span></h2>
                    <input type="hidden" id="idCard" name="id" value="${card.id}"/>
                    <button type="button" id="showCard" class="btn btn-default btn-lg">
                        <spring:message code="locale.showCard"/>
                    </button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="col-lg-offset-2 col-lg-9 col-md-9 work-space">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 card"></div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
