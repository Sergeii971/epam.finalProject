<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userEmailConfirmation.css">
</head>
<body class="reg">
<div class="container">
    <div class="content">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="EMAIL_CONFIRMATION">
        <label class="col-one-half">
            <span class="label-text">Confirmation_code</span>
            <input type="text" name="confirmation_code">
        </label>
        <button class="submit" type="submit" name="register">OK</button>
    </form>
    </div>
</div>
</body>
</html>
