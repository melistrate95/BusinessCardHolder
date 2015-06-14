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
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="col-lg-offset-2 col-lg-8 col-md-8 work-space">
                        <div class="contact name-header">
                            <div class="form-group">
                                <h1 class="featurette-heading">${card.name}</h1>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 card"></div>
                    </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
