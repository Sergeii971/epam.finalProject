<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller" method="POST">
    <input type="hidden" name="command" value="authorization" />
    <br/>Login:<br/>
    <input type="text"  name="login">
    <input type="password" name="password">
    <input type="submit" value="OK">
</form>
</body>
</html>
