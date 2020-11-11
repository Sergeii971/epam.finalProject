<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>DailyUI Challenge 001</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
</head>

<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container">
    <div class="content">
    <header>
        <h1>
            <a href="#">
                <img src="${pageContext.request.contextPath}/image/logo-1.png" alt="Authentic Collection">
            </a>
        </h1>
    </header>

    <h1 class="text-center"><fmt:message key="button.registration"/></h1>
        <c:if test="${not empty incorrectParameter && incorrectParameter['loginExist']}">
            <label class="alert-danger"><fmt:message key="label.login_exist"/></label>
        </c:if>
        <form action="${pageContext.request.contextPath}/controller" method="post" class="registration-form">
        <input type="hidden" name="command" value="ADD_USER">
        <label class="col-one-half">
            <c:if test="${not empty incorrectParameter && incorrectParameter['name']}">
            <label class="alert-danger"><fmt:message key="label.incorrect_name"/></label>
        </c:if>
            <span class="label-text">*<fmt:message key="label.name"/></span>
            <input type="text" name="name" autocomplete="off" required  pattern="^([А-Я]{1}[а-яё]{1,29}|[A-Z]{1}[a-z]{1,29})$" value = ${name}>
        </label>
        <label class="col-one-half">
            <c:if test="${not empty incorrectParameter && incorrectParameter['surname']}">
                <label class="alert-danger"><fmt:message key="label.incorrect_surname"/></label>
            </c:if>
            <span class="label-text">*<fmt:message key="label.surname"/></span>
            <input type="text" name="surname" autocomplete="off" required  pattern="^([А-Я]{1}[а-яё]{1,29}|[A-Z]{1}[a-z]{1,29})$" value = ${surname}>
        </label>
        <label>
            <c:if test="${not empty incorrectParameter && incorrectParameter['email']}">
            <label class="alert-danger"><fmt:message key="label.incorrect_email"/></label>
        </c:if>
            <span class="label-text">*<fmt:message key="login.email"/></span>
            <input type="email" name="email" autocomplete="off" required pattern="{1,50}" value = ${email}>
        </label>
        <label class="password">
            <c:if test="${not empty incorrectParameter && incorrectParameter['password']}">
            <label class="alert-danger"><fmt:message key="label.incorrect_password"/></label>
        </c:if>
            <span class="label-text">*<fmt:message key="login.password"/></span>

            <input type="password" name="password" autocomplete="off" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,120}" >
        </label>
        <div class="text-center">

           <button class="submit" type="submit" name="register"><fmt:message key="label.sign_up"/></button>
        </div>
        </form>
        <jsp:include page="footer/comeBack.jsp"/>
    </div>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="${pageContext.request.contextPath}/js/registration.js"></script>

</body>
</html>
