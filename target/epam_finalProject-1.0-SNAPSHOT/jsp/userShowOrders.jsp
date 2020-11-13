<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userShowOrders.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container">
    <jsp:include page="header/user.jsp"/>

    <c:if test="${not empty isEmpty && isEmpty}">
        <div class="a">
            <fmt:message key="label.list_empty"/>
        </div>
    </c:if>
    <div class="abcdef">
        <table>
            <tbody>
            <c:forEach var="order" items="${orderList}">
            <tr>
                <td>
                    <img src="${pageContext.request.contextPath}/uploads/${order.car.imageName}" width="250" height="250" alt="IMG">
                </td>
                <td>
                    <fmt:message key="label.car_brand"/>: ${order.car.brand.brand}
                    <br>
                    <fmt:message key="label.car_model"/>: ${order.car.model}
                    <br>
                    <fmt:message key="label.price"/>: ${order.car.price}
                    <br>
                    <fmt:message key="label.box_type"/>: ${order.car.boxType.box}
                    <br>
                    <fmt:message key="label.color"/>: ${order.car.color.color}
                    <br>
                    <fmt:message key="label.engine_type"/>: ${order.car.engineType.engine}
                    <br>
                    <fmt:message key="label.car_manufacture_year"/>: ${order.car.manufactureYear}
                    <br>
                    <c:if test="${not empty order.car.description}">
                        <fmt:message key="label.description"/>: ${order.car.description}
                        <br>
                    </c:if>
                    <fmt:message key="label.order_date"/>: ${order.date}
                    <br>
                    <c:if test="${order.inProcessing}">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="orderId" value=${order.orderId}>
                            <input type="hidden" name="command" value="USER_DELETE_ORDER">
                            <button class="submit-button" type="submit">
                                <fmt:message key="button.cancel"/>
                            </button>
                        </form>
                    </c:if>
                </td>
            <tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

