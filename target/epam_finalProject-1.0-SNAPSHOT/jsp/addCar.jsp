<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.contentPage"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addCar.css">
    <title></title>
</head>
<body class="reg">
<jsp:include page="header/switchLocale.jsp"/>
<jsp:include page="header/admin.jsp"/>
<div class="container">
    <div class="content">
        <div class="text-center">
        <form action="${pageContext.request.contextPath}/upload_controller" enctype="multipart/form-data" method="POST">
            <span class="label-text"></span>
            <div>
            <input type="file" name="goodImage" accept="image/*" height="130">
                <span id="val"></span>
                <span id="button"></span>
                </div>
            <label for="select"><fmt:message key="button.select_image"/></label>
            <input id="select" type="submit" class="select-file-button" value=<fmt:message key="button.select_image"/>>
            <label for="upload"><fmt:message key="label.upload_image"/></label>
            <input id="upload" type="submit" value=<fmt:message key="button.upload"/>>
        </form>
        </div>
        <c:if test="${not empty isImageLoaded && !isImageLoaded}">
            <label class="alert-danger"><fmt:message key="label.image_not_load"/></label>
        </c:if>
        <c:if test="${not empty isImageSelected && !isImageSelected}">
            <label class="alert-danger"><fmt:message key="label.image_not_selected"/></label>
        </c:if>
        <c:if test="${not empty isImageLoaded && isImageLoaded}">
            <img src="${pageContext.request.contextPath}/uploads/${imageName}" width="100" height="100" alt="IMG">
        </c:if>
        <c:if test="${not empty isImageLoaded && isImageLoaded}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="ADD_CAR">
            <div class="selects">
            <select class="form-control" name="brand">
                <option value="AUDI">Audi</option>
                <option value="BMW">BMW</option>
                <option value="BUGATTI">Bugatti</option>
                <option value="BENTLEY">Bentley</option>
                <option value="CADILLAC">Cadillac</option>
                <option value="FERRARI">Ferrari</option>
                <option value="JAGUAR">Jaguar</option>
                <option value="MASERATI">Maserati</option>
                <option value="NISSAN">Nissan</option>
            </select>
                    <select class="form-control" name="color">
                        <option value="BLACK"><fmt:message key="color.black"/></option>
                        <option value="RED"><fmt:message key="color.red"/></option>
                        <option value="WHITE"><fmt:message key="color.white"/></option>
                        <option value="ORANGE"><fmt:message key="color.orange"/></option>
                    </select>
                    <select class="form-control" name="boxType">
                        <option value="MECHANICS"><fmt:message key="box.type_mechanics"/></option>
                        <option value="AUTOMATION"><fmt:message key="box.type_automation"/></option>
                    </select>
                    <select class="form-control" name="engineType">
                        <option value="DIESEL"><fmt:message key="engine.type_diesel"/></option>
                        <option value="PETROL"><fmt:message key="engine.type_petrol"/></option>
                    </select>
                </div>
            <label class="col-one-half">
                <c:if test="${not empty incorrectParameter && incorrectParameter['manufactureYear']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_manufacture_year"/></label>
                </c:if>
                <span class="label-text">*<fmt:message key="label.car_manufacture_year"/></span>
                <input type="text" name="manufactureYear" required  pattern="[0-9]{4}" value = ${manufactureYear}>
            </label>

            <label class="col-one-half">
                <c:if test="${not empty incorrectParameter && incorrectParameter['price']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_price"/></label>
                </c:if>
                <span class="label-text">*<fmt:message key="label.price"/></span>
                <input type="text" name="price" required  pattern="[0-9]{1,15}" value = ${price}>
            </label>
            <label class="col-one-half">
                <c:if test="${not empty incorrectParameter && incorrectParameter['model']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_model"/></label>
                </c:if>
                <span class="label-text">*<fmt:message key="label.car_model"/></span>
                <input type="text" name="model" required  pattern="[0-9a-zA-Z-_]{1,50}" value = ${model}>
            </label>
                <c:if test="${not empty incorrectParameter && incorrectParameter['description']}">
                    <label class="alert-danger"><fmt:message key="label.incorrect_description"/></label>
                </c:if>
            <div class="cba">
                <span class="label-text"><fmt:message key="label.description_add_car"/></span>
                <textarea pattern="[^*<>/{|}]+" name="description">${description}</textarea>
            </div>
                <button class="submit" type="submit"><fmt:message key="button.add_car"/></button>

        </form>
        </c:if>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/inputTypeFile.js"></script>
</body>
</html>
