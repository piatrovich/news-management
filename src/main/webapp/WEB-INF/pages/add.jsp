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
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<c:url value="?lang=en_EN"/>">en</a>
                    </li>
                    <li>
                        <a href="?lang=ru_RU">ru</a>
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
                <a href="${pageContext.request.contextPath}" class="list-group-item">List news</a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
            <div id="article-block" class="row">
                <form:form id="addArticle" method="post" commandName="article">
                    <div class="form-group">
                        <label for="inputTitle" class="col-md-3 control-label">Title</label>
                        <div class="col-md-9">
                            <input type="text" name="title" class="form-control" id="inputTitle">
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger">
                            <form:errors path="title"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="inputShort" class="col-md-3 control-label">Short description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="description" rows="4" id="inputShort"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger">
                            <form:errors path="description"/>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="inputLong" class="col-md-3 control-label">Long description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="text" rows="10" id="inputLong"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger">
                            <form:errors path="text" />
                        </label>
                    </div>
                    <div class="col-md-9 col-md-offset-3 right-position">
                        <input type="submit" class="btn btn-default" value="Save">
                        <input type="button" class="btn btn-default"
                               onclick="location='/${pageContext.request.contextPath}'" value="Cancel">
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