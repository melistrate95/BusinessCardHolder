<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Home
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <h1>Business Card Holder</h1>
            <h2><spring:message code="locale.home_text"/></h2>
            <img src="/resources/cardLogo.jpg">
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
