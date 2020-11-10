<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>500</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Muli:400" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Passion+One" rel="stylesheet">

</head>
<body>
<div class="abc">
    <a href="${pageContext.request.contextPath}/controller?command=SWITCH_LOCALE&locale=en&current_page=/jsp/error404.jsp">
        <fmt:message key="title.locale.en"/></a>

    <a href="${pageContext.request.contextPath}/controller?command=SWITCH_LOCALE&locale=ru&current_page=/jsp/error404.jsp">
        <fmt:message key="title.locale.ru"/></a>
</div>
<div id="notfound">
    <div class="notfound-bg"></div>
    <div class="notfound">
        <div class="notfound-404">
            <h1>500</h1>
        </div>
        <h2><fmt:message key="label.error"/></h2>
        <a href="${pageContext.request.contextPath}/controller?command=MOVE_AUTHORIZATION_PAGE">
            <fmt:message key="label.back_to_the_main_page"/>
        </a>
    </div>
</div>
</body>
</html>