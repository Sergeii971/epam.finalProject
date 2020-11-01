<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript">
        history.pushState(null, null, location.href);
        history.back();
        history.forward();
        window.onpopstate = function () { history.go(1); };
    </script>
    <script>
        document.addEventListener('keydown', (event) => {
            if (event.keyCode === 116) event.preventDefault();
        })
    </script>
</head>
<body>
<div class="top-line">

<a href="${pageContext.request.contextPath}/controller?command=SWITCH_LOCALE&locale=en">
    <fmt:message key="title.locale.en"/></a>

<a href="${pageContext.request.contextPath}/controller?command=SWITCH_LOCALE&locale=ru">
    <fmt:message key="title.locale.ru"/></a>

<a href="mailto:epam.online.store@gmail.com"><fmt:message key="label.info"/></a>
</div>
</body>
</html>