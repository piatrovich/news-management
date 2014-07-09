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
        Title
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
                    <li><a href="">Back</a></li>
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
                <a href="#" class="list-group-item">Add news</a>
                <a href="#" class="list-group-item">Edit news</a>
                <a href="${pageContext.request.contextPath}/delete/${article.id}" class="list-group-item">Delete news</a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
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
                    <br />
                    <div class="col-md-12">
                        <p>${article.text}</p>
                    </div>
                </div>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
</div>

</body>
</html>