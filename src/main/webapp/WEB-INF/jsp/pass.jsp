<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 25.05.2021
  Time: 21:44
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
        textarea{
            resize: none;
            outline: none;
        }
    </style>
</head>
<body>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/testing/student/subject/test/pass/result">
        <input type="hidden"  value="${test.getTestName()}" name="testName"/>
        <h1 class="flow-text">${test.getTestName()}</h1>
        <table>
            <thead>
            <tr>
                <th><fmt:message key="text.question"/></th>
                <th><fmt:message key="text.question.answer"/></th>
                <th></th>
                <br/>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${databaseList}" var="question">
                <tr>
                    <textarea style="display:none;" readonly="readonly"  name="question">${question.getQuestionText()}</textarea>
                    <td>${question.getQuestionText()}</td>
                    <td>
                        <p>
                            <label>
                                <input type="text" required placeholder="<fmt:message key="text.question.write"/>" name="answer"/>
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
