<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buyCar.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<jsp:include page="header/user.jsp"/>
<div class="container">
    <div class="content">
        <c:if test="${not empty isBought && isBought}">
            <div class="alert-danger">
                <fmt:message key="label.buy_message"/>
            </div>
        </c:if>
        <c:if test="${not empty isBought && !isBought}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="BUY_CAR">
            <input type="hidden" name="carId" value=${carId}>
            <img src="${pageContext.request.contextPath}/uploads/${imageName}" width="300" height="300" alt="IMG">
                <fmt:message key="label.car_brand"/>: ${brand}
                <br>
                <fmt:message key="label.car_model"/>: ${model}
                <br>
                <fmt:message key="label.price"/>: ${price}
                <br>
                <fmt:message key="label.box_type"/>: ${box}
                <br>
                <fmt:message key="label.color"/>: ${color}
                <br>
                <fmt:message key="label.engine_type"/>: ${engine}
                <br>
                <fmt:message key="label.car_manufacture_year"/>: ${manufactureYear}
                <br>
                <fmt:message key="label.description"/>: ${description}
                <br>
                <fmt:message key="label.added_date"/>: ${addedDate}
                <br>
            <div class="text-center">
                <button class="submit" type="submit" name="register"><fmt:message key="button.buy"/></button>
            </div>
        </form>
        </c:if>
    </div>
</div>
</body>
</html>
