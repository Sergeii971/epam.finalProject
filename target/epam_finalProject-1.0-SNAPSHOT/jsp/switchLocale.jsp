<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="controller?command=switch_locale&locale=en">
    <fmt:message key="title.locale_en"/></a>
<a href="controller?command=switch_locale&locale=ru">
    <fmt:message key="title.locale_ru"/></a>
</form>
</body>
</html>
