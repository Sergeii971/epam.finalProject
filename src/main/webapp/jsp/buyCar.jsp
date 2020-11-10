<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
        <form action="${pageContext.request.contextPath}/controller" method="post" class="bcad">
            <input type="hidden" name="command" value="BUY_CAR">
            <input type="hidden" name="carId" value=${carId}>
            <img src="${pageContext.request.contextPath}/uploads/${imageName}" width="300" height="300" alt="IMG">
               <p>
                <fmt:message key="label.car_brand"/>: ${brand}
               </p>
            <p>
                <fmt:message key="label.car_model"/>: ${model}
            </p>
            <p>
                <fmt:message key="label.price"/>: ${price}
            </p>
            <p>
                <fmt:message key="label.box_type"/>: ${box}
            </p>
            <p>
                <fmt:message key="label.color"/>: ${color}
            </p>
            <p>
                <fmt:message key="label.engine_type"/>: ${engine}
            </p>
            <p>
                <fmt:message key="label.car_manufacture_year"/>: ${manufactureYear}
            </p>
            <p>
                <fmt:message key="label.description"/>: ${description}
            </p>
            <p>
                <fmt:message key="label.added_date"/>: ${addedDate}
            </p>
            <div class="text-center">
                <button class="submit" type="submit" name="register"><fmt:message key="button.buy"/></button>
            </div>
        </form>
        </c:if>
    </div>
</div>
</body>
</html>
