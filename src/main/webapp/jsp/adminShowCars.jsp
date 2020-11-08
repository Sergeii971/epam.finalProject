<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/showCar.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container">
<jsp:include page="header/admin.jsp"/>
<c:if test="${not empty isEmpty && isEmpty}">
    <div class="a">
    <fmt:message key="label.list_empty"/>
    </div>
</c:if>

<div>
<table>
    <tbody>
<c:forEach var="car" items="${carPerPage}">
<tr>
    <td>
    <img src="${pageContext.request.contextPath}/uploads/${car.imageName}" width="250" height="250" alt="IMG">
    </td>
    <td>
        <fmt:message key="label.car_brand"/>: ${car.brand.brand}
        <br>
        <fmt:message key="label.car_model"/>: ${car.model}
        <br>
        <fmt:message key="label.price"/>: ${car.price}
        <br>
        <fmt:message key="label.box_type"/>: ${car.boxType.box}
        <br>
        <fmt:message key="label.color"/>: ${car.color.color}
        <br>
        <fmt:message key="label.engine_type"/>: ${car.engineType.engine}
        <br>
        <fmt:message key="label.car_manufacture_year"/>: ${car.manufactureYear}
        <br>
        <fmt:message key="label.description"/>: ${car.description}
       <br>
        <fmt:message key="label.added_date"/>: ${car.addedDate}
        <br>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="carId" value=${car.carId}>
            <input type="hidden" name="command" value="DELETE_CAR">
            <button class="submit-button" type="submit">
                <fmt:message key="button.delete"/>
                </button>
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="carId" value=${car.carId}>
            <input type="hidden" name="command" value="CHANGE_CAR_IS_AVAILABLE_STATUS">
            <c:choose>
                <c:when test="${car.isAvailable}">
                    <input type="hidden" name="isAvailable" value="false">
                    <button class="submit-button" type="submit">
                        <fmt:message key="button.block_car"/>
                    </button>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="isAvailable" value="true">
                    <button class="submit-button" type="submit">
                        <fmt:message key="button.unblock_car"/>
                    </button>
                </c:otherwise>
            </c:choose>
        </form>
        </td>
    <tr>
</c:forEach>
    </tbody>
</table>
    <c:if test="${not empty isFirstPage && !isFirstPage}">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="CAR_PREVIOUS_PAGE">
        <button><fmt:message key="button.previous_page"/></button>
    </form>
    </c:if>
        <c:if test="${not empty hasNextPage && hasNextPage}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="CAR_NEXT_PAGE">
                <button><fmt:message key="button.next_page"/></button>
            </form>
        </c:if>
</div>
    </div>
</body>
</html>
