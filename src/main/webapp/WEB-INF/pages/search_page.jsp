<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="title">
        Search
    </tiles:putAttribute>
    <tiles:putAttribute name="body">
        <div class="container search-space">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <c:forEach items="${cards}" var="card">
                        <p>
                            <img src="${card.url}"/>
                            <br/>
                            <a href="/id${user.id}/cards/${card.id}/show">
                                <h2 class="card-heading">${card.name}</h2>
                            </a>
                            <hr/>
                        </p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
