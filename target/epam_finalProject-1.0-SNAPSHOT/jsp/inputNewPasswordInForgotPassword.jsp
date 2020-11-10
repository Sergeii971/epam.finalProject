<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forgotPassword.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container">
    <div class="content">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="CHECK_NEW_PASSWORD">
            <c:if test="${not empty successfulPasswordChange && !successfulPasswordChange}">
                <label class="alert-danger"><fmt:message key="registration.incorrect_data"/></label>
            </c:if>
            <label class="password">
                <span class="label-text"><fmt:message key="label.new_password"/> </span>
           <input type="password" name="password" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,30}">
            </label>
            <label class="password">
                <span class="label-text"><fmt:message key="label.confirm_password"/> </span>
                <input type="password" name="passwordConfirmation" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,30}" >
            </label>
            <div class="text-center">
                <button class="submit" type="submit" name="register"><fmt:message key="button.ok"/></button>
            </div>
        </form>
    </div>
    <jsp:include page="footer/comeBack.jsp"/>
</div>
</body>
</html>
