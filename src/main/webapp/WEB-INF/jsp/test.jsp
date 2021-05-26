<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 25.05.2021
  Time: 21:30
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
</head>
<body>
<div class="container">
    <form method="get" action="${pageContext.request.contextPath}/testing/student/subject/test/pass">
        <table>
            <thead>
            <tr>
                <th><fmt:message key="text.test.name"/></th>
                <th><fmt:message key="text.test.complexity"/></th>
                <th><fmt:message key="text.test.time"/></th>
                <th></th>
                <br/>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${databaseList}" var="test">
                <tr>
                    <td> ${test.getTestName()}</td>
                    <td> ${test.getComplexity()}</td>
                    <td> ${test.getTime()}</td>
                    <td>
                        <p>
                            <label>
                                <input type="radio" required="required" name="testName" value="${test.getTestName()}"/><span> </span>
                            </label>
                        </p>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <input class="btn" type="submit" value="<fmt:message key="text.choose"/>">
    </form>
</div>
</body>
</html>
