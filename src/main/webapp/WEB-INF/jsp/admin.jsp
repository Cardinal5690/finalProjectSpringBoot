<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 25.05.2021
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title><fmt:message key="text.title"/></title>
    <jsp:include page="/WEB-INF/jsp/head.jsp"/>
    <style>
        .mb15 {
            margin: 0 0 15px;
        }

        .row {
            display: flex;
            align-items: center;
        }
        table{
            width: 30%;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col s12 m6">
            <h1 class="flow-text mb15"><fmt:message key="text.subjects.create"/> </h1>
            <form method="get"
                  action="${pageContext.request.contextPath}/testing/admin/subject">
                <button class="btn" name="locale" value="EN" type="submit"><fmt:message key="text.subjects.en"/> </button>
                <button class="btn" name="locale" value="UA" type="submit"><fmt:message key="text.subjects.ua"/> </button>
            </form>
            <form method="get"
                  action="${pageContext.request.contextPath}/testing/admin/user">
                <h1 class="flow-text mb15"><fmt:message key="text.user.update"/> </h1>
                <button class="btn" type="submit"><fmt:message key="text.update"/> </button>
            </form>
        </div>
        <div class="col s12 m6">
            <h1 class="flow-text"><fmt:message key="text.admin"/></h1>
            <div class="card blue-grey darken-1">
                <div class="card-content white-text">
                    <div>
                        <fmt:message key="text.name"/>
                        <c:out value="${admin.getName()}"/>
                        <br>
                        <br>
                        <fmt:message key="text.surname"/>
                        <c:out value="${admin.getSurname()}"/>
                        <br>
                        <br>
                        <fmt:message key="text.email"/>
                        <c:out value="${admin.getEmail()}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/foot.jsp"/>
</body>
</html>
