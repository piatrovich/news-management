<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <spring:message code="title.edit"/>
    </title>
    <!-- Styles -->
    <link href="<spring:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/css/custom.css"/>" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Static navbar -->
    <div class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a onclick="window.history.back()" href="javascript:void(0)">
                            <spring:message code="menu.back"/>
                        </a>
                    </li>
                </ul>
                <c:import url="common/lang.jsp"/>
            </div>
        </div>
    </div>

    <!-- Body -->
    <div class="row">
        <!-- Sidebar-->
        <div class="col-md-3" id="sidebar">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}" class="list-group-item">
                    <spring:message code="menu.list.news"/>
                </a>
                <a href="${pageContext.request.contextPath}/delete/${article.id}" class="list-group-item">
                    <spring:message code="menu.delete.news"/>
                </a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
            <div id="article-block" class="row articles">
                <form:form method="post" commandName="proposedArticle">
                    <div class="form-group">
                        <label for="inputTitle" class="col-md-3 control-label">
                            <spring:message code="page.body.title"/>
                        </label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="inputTitle" value="${article.title}">
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger">
                            <form:errors path="title"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="inputShort" class="col-md-3 control-label">
                            <spring:message code="page.body.description.short"/>
                        </label>
                        <div class="col-md-9">
                            <textarea class="form-control" rows="4" id="inputShort">${article.description}</textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger">
                            <form:errors path="description"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="inputLong" class="col-md-3 control-label">
                            <spring:message code="page.body.description.long"/>
                        </label>
                        <div class="col-md-9">
                            <textarea class="form-control" rows="10" id="inputLong">${article.text}</textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger">
                            <form:errors path="text"/>
                        </label>
                    </div>
                    <div class="col-md-9 col-md-offset-3 right-position">
                        <input type="submit" class="btn btn-default"
                               value="<spring:message code="page.body.button.save"/>">
                        <input type="button" class="btn btn-default"
                               onclick="window.location.href='/${pageContext.request.contextPath}'"
                               value="<spring:message code="page.body.button.cancel"/>">
                    </div>
                </form:form>
            </div>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
</div>

</body>
</html>