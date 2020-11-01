<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Login V1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/image/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/font/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
</head>
<body>
<jsp:include page="header/switchLocale.jsp"/>
<div class="limiter">

    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="${pageContext.request.contextPath}/image/img-01.png" alt="IMG">
            </div>
            <form action="${pageContext.request.contextPath}/controller" method="post" class="login100-form validate-form">
            <input type="hidden" name="command" value="authentication" />
                <span class="login100-form-title">
						<fmt:message key="label.Member_Login"/>
					</span>

                <div class="wrap-input100 validate-input" data-validate=<fmt:message key="login.incorrect_login_format"/>>
                    <c:if test="${not empty successfulAuthorization && !successfulAuthorization}">
                        <label class="alert-danger"><fmt:message key="login.not_authorized"/></label>
                    </c:if>
                    <c:if test="${not empty successfulActivation && !successfulActivation}">
                        <label class="alert-warning"><fmt:message key="login.not_activated_user"/></label>
                    </c:if>
                    <input class="input100" type="email" name="login" placeholder=<fmt:message key="login.email"/>
                            autofocus required
                           minlength="7" maxlength="50"
                           value=${login}>
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" placeholder=<fmt:message key="login.password"/>
                            required
                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,30}" minlength="8" maxlength="128">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                </div>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        <fmt:message key="button.sign_in"/>
                    </button>

                </div>
            <div class="text-center p-t-12">
						<span class="txt1">
						</span>
                    <a class="txt2" href="${pageContext.request.contextPath}/controller?command=MOVE_FORGOT_PASSWORD_PAGE&email=${login}">
                        <fmt:message key="label.forgot_password"/>
                    </a>
                </div>
                <div class="text-center p-t-136">
                    <a class="txt2" href="${pageContext.request.contextPath}/controller?command=Registration_Page">
                        <fmt:message key="label.sign_up"/>
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>

</div>

<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
<%--<!--===============================================================================================-->--%>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<%--<!--===============================================================================================-->--%>
<script src="${pageContext.request.contextPath}/vendor/select2/select2.min.js"></script>
<%--<!--===============================================================================================-->--%>
<script src="${pageContext.request.contextPath}/vendor/tilt/tilt.jquery.min.js"></script>

<%--<!--===============================================================================================-->--%>
<script src="${pageContext.request.contextPath}/js/authorization.js"></script>

</body>
</html>