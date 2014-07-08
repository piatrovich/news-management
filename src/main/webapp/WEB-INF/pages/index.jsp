<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <spring:message code="title.index"/>
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
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<c:url value="?lang=en"/>">EN</a>
                    </li>
                    <li>
                        <a href="?lang=ru">RU</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- Body -->
    <div class="row">
        <!-- Sidebar-->
        <div class="col-md-3" id="sidebar">
            <div class="list-group">
                <a href="add" class="list-group-item">Add news</a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
            <c:forEach var="article" items="${articles}">
                <div id="article-block" class="row">
                        <div class="col-md-7">
                            <h3>${article.title}</h3>
                        </div>
                        <div class="col-md-5 date">
                            <p>${article.date}</p>
                        </div>
                        <div class="col-md-12">
                            <p>${article.description}</p>
                        </div>
                        <div class="col-md-4 col-md-offset-8">
                            <div class="row">
                                <div class="col-md-4">
                                    <p>
                                        <a href="view/<c:out value="${article.id}"/>">
                                            <spring:message code="action.view"/>
                                        </a>
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <p>
                                        <a href="edit/<c:out value="${article.id}"/>">
                                            <spring:message code="action.edit"/>
                                        </a>
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <p>
                                        <a href="delete/<c:out value="${article.id}"/>">
                                            <spring:message code="action.delete"/>
                                        </a>
                                    </p>
                                </div>
                            </div>
                        </div>
                </div>
            </c:forEach>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
</div>

</body>
</html>