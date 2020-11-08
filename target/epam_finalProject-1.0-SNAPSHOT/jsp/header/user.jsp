<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userAdminHeader.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="SHOW_CARS_PAGE">
    <button type="submit"> <fmt:message key="button.show_cars"/></button>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="MOVE_AUTHORIZATION_PAGE"/>
    <button>
        <fmt:message key="button.come_back"/>
    </button>
</form>
</body>
</html>
