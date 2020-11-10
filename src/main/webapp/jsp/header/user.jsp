<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userAdminHeader.css">
</head>
<body>
<div class="abcd">
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="SHOW_CARS_PAGE">
    <button type="submit"> <fmt:message key="button.show_cars"/></button>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="USER_SHOW_ORDERS_PAGE">
    <button type="submit"> <fmt:message key="button.show_user_orders"/></button>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="MOVE_AUTHORIZATION_PAGE"/>
    <button>
        <fmt:message key="button.come_back"/>
    </button>
</form>
</div>
</body>
</html>
