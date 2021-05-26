<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 25.05.2021
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<table>
    <thead>
    <tr>
        <th><fmt:message key="text.test.name"/></th>
        <th><fmt:message key="text.result"/> </th>
        <br/>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${databaseList}" var="result">
        <tr>
            <td> ${result.getTest().getTestName()}</td>
            <td><jsp:text>${result.getResult()}/100</jsp:text></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="container">
    <div class="row">
        <div class="col s12 m6">
            <h1 class="flow-text mb15"><fmt:message key="text.subjects"/> </h1>
            <form method="get"
                  action="${pageContext.request.contextPath}/testing/student/subject">
                <input type="hidden">
                <button class="btn" name="locale" value="EN" type="submit"><fmt:message key="text.subjects.en"/> </button>
                <button class="btn" name="locale" value="UA" type="submit"><fmt:message key="text.subjects.ua"/> </button>
            </form>
        </div>
        <div class="col s12 m6">
            <div class="card blue-grey darken-1">
                <div class="card-content white-text">
                    <div>
                        <fmt:message key="text.name"/>
                        <c:out value="${user.getName()}"/>
                        <br>
                        <br>
                        <fmt:message key="text.surname"/>
                        <c:out value="${user.getSurname()}"/>
                        <br>
                        <br>
                        <fmt:message key="text.email"/>
                        <c:out value="${user.getEmail()}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/foot.jsp"/>
</body>
</html>
