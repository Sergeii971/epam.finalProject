<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>DailyUI Challenge 001</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addGood.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<jsp:include page="header/user.jsp"/>
<div class="container">
    <div class="content">
        <div>
        <form action="${pageContext.request.contextPath}/upload_controller" method="POST">
            <fmt:message key="label.upload"/><input type="file" name="goodImage" accept="image/*" height="130">
            <input type="submit" value=<fmt:message key="button.upload"/>>
        </form>
        </div>
        <form action="${pageContext.request.contextPath}/controller" method="post" class="registration-form">
            <input type="hidden" name="command" value="ADD_GOOD">

            <label class="col-one-half">
                <c:if test="${not empty incorrectParameter && incorrectParameter['goodName']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_name"/></label>
                </c:if>
                <span class="label-text"><fmt:message key="label.goodName"/></span>
                <input type="text" name="name" required  pattern="[a-zA-Zа-яА-Я-]{1,50}" value = ${goodName}>
            </label>

            <label class="col-one-half">
                <c:if test="${not empty incorrectParameter && incorrectParameter['price']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_price"/></label>
                </c:if>
                <span class="label-text"><fmt:message key="label.price"/></span>
                <input type="text" name="price" required  pattern="[0-9]{1,20}" value = ${surname}>
            </label>

                <c:if test="${not empty incorrectParameter && incorrectParameter['description']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_description"/></label>
                </c:if>
            <div class="text-center">
                <span class="label-text"><fmt:message key="label.description"/></span>
            </div>
            <div class="text-center">
                <textarea name="description" required pattern="[0-9a-zA-Z!@#$%^&*]{,500}"></textarea>
            </div>
            <div class="text-center">
                <button class="submit" type="submit" name="register"><fmt:message key="button.add_good"/></button>
            </div>
        </form>
        <jsp:include page="footer/comeBack.jsp"/>
    </div>
</div>
</body>
</html>
