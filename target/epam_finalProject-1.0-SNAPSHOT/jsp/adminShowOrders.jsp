<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminShowOrders.css">
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
    <c:if test="${not empty incorrectParameter && incorrectParameter}">
        <label class="alert-danger"> <fmt:message key="label.incorrect_search_parameter"/></label>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller" method="post" class="registration-form">
        <input type="hidden" name="command" value="FIND_ORDER">

        <div class="selects">
            <input type="text" name="searchParameter" autocomplete="off" pattern="[^*<>/{|}]+"
                                     placeholder=<fmt:message key="placeholder.search"/>>
            <select class="form-control" name="brand">
                <option value=""><fmt:message key="option.default"/> </option>
                <option value="AUDI">Audi</option>
                <option value="BMW">BMW</option>
                <option value="BUGATTI">Bugatti</option>
                <option value="BENTLEY">Bentley</option>
                <option value="CADILLAC">Cadillac</option>
                <option value="Ferrari">Ferrari</option>
                <option value="JAGUAR">Jaguar</option>
                <option value="MASERATI">Maserati</option>
                <option value="NISSAN">Nissan</option>
            </select>
            <select class="form-control" name="color">
                <option value=""><fmt:message key="option.default"/> </option>
                <option value="BLACK"><fmt:message key="color.black"/></option>
                <option value="RED"><fmt:message key="color.red"/></option>
                <option value="WHITE"><fmt:message key="color.white"/></option>
                <option value="ORANGE"><fmt:message key="color.orange"/></option>
            </select>
            <select class="form-control" name="boxType">
                <option value=""><fmt:message key="option.default"/> </option>
                <option value="MECHANICS"><fmt:message key="box.type_mechanics"/></option>
                <option value="AUTOMATION"><fmt:message key="box.type_automation"/></option>
            </select>
            <select class="form-control" name="engineType">
                <option value=""><fmt:message key="option.default"/> </option>
                <option value="DIESEL"><fmt:message key="engine.type_diesel"/></option>
                <option value="PETROL"><fmt:message key="engine.type_petrol"/></option>
            </select>
            <button type="submit">
                <fmt:message key="button.find"/>
            </button>
        </div>
    </form>
    <div>
        <table>
            <tbody>
            <c:forEach var="order" items="${orderPerPage}">
            <tr>
                <td>
                    <img src="${pageContext.request.contextPath}/uploads/${order.car.imageName}" width="250" height="250" alt="IMG">
                </td>
                <td>
                    <fmt:message key="label.user_email"/>: ${order.user.email}
                    <br>
                    <fmt:message key="label.name"/>: ${order.user.name}
                    <br>
                    <fmt:message key="label.name"/>: ${order.user.surname}
                    <br>
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
                        <input type="hidden" name="command" value="ADMIN_DELETE_ORDER">
                        <button class="submit-button" type="submit">
                            <fmt:message key="button.cancel"/>
                        </button>
                    </form>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="orderId" value=${order.orderId}>
                            <input type="hidden" name="command" value="CHANGE_ORDER_STATUS">
                            <button class="submit-button" type="submit">
                                <fmt:message key="button.deal_took_place"/>
                            </button>
                        </form>
                    </c:if>
                </td>
            <tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty isFirstPage && !isFirstPage}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="ORDER_PREVIOUS_PAGE">
                <button><fmt:message key="button.previous_page"/></button>
            </form>
        </c:if>
        <c:if test="${not empty hasNextPage && hasNextPage}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="ORDER_NEXT_PAGE">
                <button><fmt:message key="button.next_page"/></button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>

