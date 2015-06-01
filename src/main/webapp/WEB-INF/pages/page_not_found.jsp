<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Page Not Found
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <h1>404 Page Not Found</h1>
            <h2>Sorry, this page does not exist.</h2>
            <img src="/resources/page404.jpg">
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>