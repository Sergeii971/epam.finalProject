<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/showCar.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container">
    <c:choose>
    <c:when test="${not empty isAdmin && isAdmin}">
<jsp:include page="header/admin.jsp"/>
    </c:when>
        <c:otherwise>
            <jsp:include page="header/user.jsp"/>
        </c:otherwise>
    </c:choose>
<c:if test="${not empty isEmpty && isEmpty}">
    <div class="a">
    <fmt:message key="label.list_empty"/>
    </div>
</c:if>
    <form action="${pageContext.request.contextPath}/controller" method="post" class="registration-form">
        <div class="abcde">
        <input type="hidden" name="command" value="FIND_CAR">
        <input class="reg" type="text" name="searchParameter" autocomplete="off" pattern="[^*<>/{|}]+"
               placeholder=<fmt:message key="placeholder.search"/>>
        <label>
        <span class="label-text"><fmt:message key="label.price"/>
        <input class="reg" type="text" name="fromPrice" autocomplete="off"
               placeholder=<fmt:message key="input.from"/> pattern="[0-9]">
        <input class="reg" type="text" name="toPrice" autocomplete="off"
               placeholder=<fmt:message key="input.to"/> pattern="[0-9]">
            </span>
        </label>
        </div>
        <div class="selects">
            <select class="form-control" name="brand">
                <option value=""><fmt:message key="option.default"/> </option>
                <option value="AUDI">Audi</option>
                <option value="BMW">BMW</option>
                <option value="BUGATTI">Bugatti</option>
                <option value="BENTLEY">Bentley</option>
                <option value="CADILLAC">Cadillac</option>
                <option value="FERRARI">Ferrari</option>
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
        <c:choose>
                <c:when test="${not empty isAdmin && isAdmin}">
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
            <c:if test="${not empty inOrderList && inOrderList}">
                <label class="alert-danger"><fmt:message key="label.in_order_list"/></label>
                <br>
            </c:if>
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

        </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="carId" value=${car.carId}>
                    <input type="hidden" name="command" value="BUY_CAR_PAGE">
                    <button class="submit-button" type="submit">
                        <fmt:message key="button.buy"/>
                    </button>
                </form>
            </c:otherwise>
        </c:choose>
        </td>
    <tr>
</c:forEach>
    </tbody>
</table>
    <div class="abc">
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
    </div>
</body>
</html>
