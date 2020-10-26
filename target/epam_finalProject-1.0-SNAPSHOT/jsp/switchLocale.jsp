<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <style type="text/css"></style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<a href="${pageContext.request.contextPath}/controller?command=SWITCH_LOCALE&locale=en">
    <fmt:message key="title.locale.en"/></a>

<a href="${pageContext.request.contextPath}/controller?command=SWITCH_LOCALE&locale=ru">
    <fmt:message key="title.locale.ru"/></a>
</body>
</html>