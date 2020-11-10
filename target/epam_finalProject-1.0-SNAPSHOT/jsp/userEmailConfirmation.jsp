<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userEmailConfirmation.css">
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container"><label class="col-one-half">
    <span class="label-text"><fmt:message key="label.information_confirmation_page"/></span>
</label>
    <div class="content">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="EMAIL_CONFIRMATION">
        <c:if test="${not empty successfulEmailConfirmation && !successfulEmailConfirmation}">
            <label class="alert-warning"><fmt:message key="label.account_not_confirmed"/></label>
        </c:if>
        <label class="text-center">
            <div>
            <input type="text" name="confirmation_code" autocomplete="off" required pattern="\p{Alnum}">
            </div>
        </label>
        <br>
        <div class="text-center">
        <button class="submit" type="submit" name="register"><fmt:message key="button.ok"/> </button>
        </div>
    </form>
        <jsp:include page="footer/comeBack.jsp"/>
    </div>
</div>
</body>
</html>
