<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Home
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="jumbotron">
            <h1>Business Card Holder</h1>
            <h2>We present our new app! This application is designed to create and store cards.</h2>
            <h2>May the Force be with you!</h2>
            <img src="/resources/cardLogo.jpg">
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
