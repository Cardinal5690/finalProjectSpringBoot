<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 24.05.2021
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<%@ page isELIgnored = "false" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <style>
        .wrapper{
            display: flex;
            align-items: center;
            min-height: 75vh;
            resize: none;
        }
    </style>
    <title><fmt:message key="text.title"/></title>
</head>
<body>
<nav>
    <div class="nav-wrapper">
        <div class="container"  >
            <a class="brand-logo"><fmt:message key="text.title"/></a >
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/testing/main"><fmt:message key="text.main"/></a></li>
                <li><a href="${pageContext.request.contextPath}/testing/logout"><fmt:message key="text.logout"/></a></li>
                <li><a href="${pageContext.request.contextPath}/testing/language/en">English</a></li>
                <li><a href="${pageContext.request.contextPath}/testing/language/ua">Українська</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="wrapper"/>
</body>
</html>