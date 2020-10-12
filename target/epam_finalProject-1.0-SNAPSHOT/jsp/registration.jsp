<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>DailyUI Challenge 001</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">


</head>

<body class="reg">
<div class="container">
    <div class="content">

    <header>
        <h1>
            <a href="#">
                <img src="${pageContext.request.contextPath}/image/logo-1.png" alt="Authentic Collection">
            </a>
        </h1>
    </header>

    <h1 class="text-center">Register</h1>
    <form action="${pageContext.request.contextPath}/controller" method="post" class="registration-form">
        <input type="hidden" name="command" value="ADD_USER">
        <label class="col-one-half">
            <span class="label-text">Name</span>
            <input type="text" name="name" value = ${name}>
        </label>
        <label class="col-one-half">
            <span class="label-text">Surname</span>
            <input type="text" name="surname" value = ${surname}>
        </label>
        <label>
            <span class="label-text">Email</span>
            <input type="text" name="email" value = ${email}>
        </label>
        <label class="password">
            <span class="label-text">Password</span>
            <button class="toggle-visibility"  title="toggle password visibility" tabindex="-1">
                <span class="glyphicon glyphicon-eye-close"></span>
            </button>
            <input type="password" name="password">
        </label>
        <div class="text-center">
           <button class="submit" type="submit" name="register">Sign Me Up</button>
        </div>

    </form>
    </div>

</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<%--<script src="../js/registration.js"></script>--%>

</body>
</html>
