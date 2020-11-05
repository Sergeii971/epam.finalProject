<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header/switchLocale.jsp"/>
<jsp:include page="header/admin.jsp"/>
<jsp:include page="footer/comeBack.jsp"/>
</body>
</html>
