<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<div class="container">
    <div class="content">

        <form action="${pageContext.request.contextPath}/controller" method="post" class="registration-form">
            <input type="hidden" name="command" value="CHANGE_PASSWORD">
            <label class="password">
                <span class="label-text">*<fmt:message key="label.old_password"/></span>
                <input type="password" name="oldPassword" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,120}" >
            </label>
            <label class="password">
                <span class="label-text">*<fmt:message key="login.password"/></span>
                <input type="password" name="password" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,120}" >
            </label>
            <label class="password">
                <span class="label-text">*<fmt:message key="login.password"/></span>

                <input type="password" name="password" required pattern="(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,120}" >
            </label>
            <div class="text-center">

                <button class="submit" type="submit" name="register"><fmt:message key="label.sign_up"/></button>
            </div>
        </form>
        <jsp:include page="footer/comeBack.jsp"/>
    </div>
</div>
</body>
</html>
