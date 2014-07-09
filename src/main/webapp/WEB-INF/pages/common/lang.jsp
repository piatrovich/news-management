<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav navbar-nav navbar-right">
    <li>
        <a href="<c:url value="?lang=en_EN"/>">en</a>
    </li>
    <li>
        <a href="?lang=ru_RU">ru</a>
    </li>
    <li>
        <a href="#">Current: ${pageContext.response.locale}</a>
    </li>
</ul>
