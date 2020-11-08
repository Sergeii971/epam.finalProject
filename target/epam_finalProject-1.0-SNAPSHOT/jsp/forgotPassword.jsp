<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <label class="text-center">
                <div>
                    <span class="label-text"><fmt:message key="login.email"/></span>
                    <input type="text" name="email" value=${email}>
                </div>
            </label>
            <br>
            <c:if test="${not empty isBlocked && isBlocked}">
                <label class="alert-danger"><fmt:message key="login.not_activated_user"/></label>
            </c:if>
            <c:if test="${!isConfirmationCodeSend}">
                <div class="text-center">
                <button class="submit">
                    <fmt:message key="button.ok"/>
                </button>
                </div>
            </c:if>


        </form>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="FORGOT_PASSWORD_EMAIL_CONFIRMATION">
             <c:if test="${not empty isConfirmationCodeSend && isConfirmationCodeSend}">
                 <c:if test="${not empty successfulEmailConfirmation && !successfulEmailConfirmation}">
                     <label class="alert-danger"><fmt:message key="label.account_not_confirmed"/></label>
                 </c:if>
                 <label class="text-center">
                     <div>
                         <span class="label-text"><fmt:message key="label.information_confirmation_page"/></span>
                     </div>
                     <div>
                         <input type="text" name="confirmation_code" autocomplete="off"
                                required pattern="\p{Alnum}">
                     </div>
                 </label>
                 <br>
            <div class="text-center">
            <button class="submit"><fmt:message key="button.ok"/></button>
            </div>
             </c:if>
        </form>
    </div>
    <jsp:include page="footer/comeBack.jsp"/>
</div>
</body>
</html>
