<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="um" uri="userManagement" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
</head>
<body>
<jsp:include page="header/switchLocale.jsp"/>
<jsp:include page="header/admin.jsp"/>
<div class="col-8">
    <div class="abc">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="filter_users">
        <select class="form-control" name="userStatus">
        <option value="all"><fmt:message key="option.user_status_all"/></option>
        <option value="isConfirmed"><fmt:message key="option.user_status_confirmed"/></option>
        <option value="isBlocked"><fmt:message key="option.user_status_blocked"/></option>
    </select>
    <button type="submit">
        <fmt:message key="button.filter"/>
    </button>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="SEARCH_USER">

            <input class="reg" type="text" name="searchParameter" autocomplete="off"
                   placeholder=<fmt:message key="placeholder.search"/> pattern="[0-9a-zA-Z!@#$%^&*]">
        <button class="reg" type="submit">
            <fmt:message key="button.find"/>
        </button>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="sort_users">
        <select class="form-control" name="sortType">
            <option value="default"><fmt:message key="option.default_sort"/></option>
            <option value="surname"><fmt:message key="option.sort_by_surname"/></option>
            <option value="email"><fmt:message key="option.sort_by_email"/></option>
        </select>
        <button type="submit">
            <fmt:message key="button.sort"/>
        </button>
    </form>
    </div>
<table>
    <tbody>
    <tr>
        <th><fmt:message key="column.email"/></th>
        <th><fmt:message key="column.name"/></th>
        <th><fmt:message key="column.surname"/></th>
        <th><fmt:message key="column.is_confirmed"/></th>
        <th><fmt:message key="column.is_blocked"/></th>
        <th><fmt:message key="column.change_user_status"/></th>
        <c:if test="${not empty isNotConfirmedUserExist && isNotConfirmedUserExist}">
            <th><fmt:message key="column.delete_not_confirmed_user"/></th>
        </c:if>
    </tr>
    <um:userManagement/>
    </tbody>
</table>
</div>
<div class="reg">
</div>
</body>
</html>
