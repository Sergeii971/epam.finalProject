<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fp" uri="forgotPassword" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forgotPassword.css">
</head>
<body>
<jsp:include page="header/switchLocale.jsp"/>
<body class="reg">
<div class="container">
    <div class="content">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="SEND_RECOVERY_KEY">
            <label class="col-one-half">
                <span class="label-text">Email</span>
                <input type="text" name="email" value=${email}>
            </label>
<%--            <button class="submit">OK</button>--%>
            <fp:user-pagination/>
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="FORGOT_PASSWORD">
            <label class="col-one-half">
                <span class="label-text">Confirmation_code</span>
                <input type="text" name="email" autocomplete="off">
            </label>
            <button class="submit">OK</button>
        </form>
        <jsp:include page="footer/comeBack.jsp"/>
    </div>
</div>
<jsp:include page="footer/comeBack.jsp"/>
</body>
</html>
