<%@ page contentType="text/html;charset=UTF-8"%>
<style>
    <%@ include file="/jsp/css/authorization.css" %>
</style>
<html>
<head>
    <meta charset="UTF-8">
    <title>22</title>
</head>
<body>
<div id="login-form">
    <h1>АВТОРИЗАЦИЯ</h1>
    <fieldset>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="authorization" />
            <input type="email" required value="Логин" name="login">
            <input type="password" required value="Пароль" name="password">
            <input type="submit" value="ВОЙТИ">
        </form>
    </fieldset>
</div>
</body>
</html>
