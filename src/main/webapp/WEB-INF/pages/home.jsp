<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Home
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="row featurette">
            <div id="carousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel" data-slide-to="1"></li>
                    <li data-target="#carousel" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item carousel-item active">
                        <img src="/resources/images/personal_data_home.jpg" alt="<spring:message code="locale.personal_data"/>">
                        <div class="home-header-1">
                            <h1 class="home-header-font-1"><spring:message code="locale.homeMessage1"/></h1>
                        </div>
                    </div>
                    <div class="item carousel-item">
                        <img src="/resources/images/jobs_home.jpg" alt="<spring:message code="locale.jobs"/>">
                        <div class="home-header-2">
                            <h1 class="home-header-font-2"><spring:message code="locale.homeMessage2"/></h1>
                        </div>
                    </div>
                    <div class="item carousel-item">
                        <img src="/resources/images/contacts_home.jpg" alt="<spring:message code="locale.contacts"/>">
                        <div class="home-header-3">
                            <h1 class="home-header-font-3"><spring:message code="locale.homeMessage3"/></h1>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#carousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
