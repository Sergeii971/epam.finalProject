<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/showCar.css">
</head>
<body>
<div class="abc">
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="USER_MANAGEMENT_PAGE">
    <button type="submit"><fmt:message key="button.user_management"/></button>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="ADD_CAR_PAGE">
    <button type="submit"> <fmt:message key="button.add_car"/></button>
</form>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="ADMIN_SHOW_CARS_PAGE">
        <button type="submit"> <fmt:message key="button.show_cars"/></button>
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
