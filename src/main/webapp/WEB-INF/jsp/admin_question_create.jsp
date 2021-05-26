<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 26.05.2021
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="text.title"/></title>
    <jsp:include page="/WEB-INF/jsp/head.jsp"/>
</head>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/testing/admin/subject/test/question/create" method="post">
        <input type="hidden" name="title" value="${title}"/>
        <input type="hidden" name="testName" value="${testName}"/>
        <p>
            <label>
                <input type="text" required placeholder="<fmt:message key="text.admin.question.write"/>" name="question"/>
            </label>
        </p>
        <p>
            <label>
                <input type="text" placeholder="<fmt:message key="text.admin.answer.write"/>"
                       name="answer"/>
            </label>
        </p>
        <div>
            <input type="submit" class="btn" value="<fmt:message key="text.create.question"/>"/>
            <c:if test="${requestScope.updateError}">
            <div>
                <fmt:message key="text.update.error"/>
            </div>
            </c:if>
    </form>
</div>
<form method="get"
      action="${pageContext.request.contextPath}/testing/admin/subject/test">
    <label>
        <button class="btn" name="title" value="${title}" type="submit"><fmt:message key="text.test.name"/> </button>
    </label>
</form>
<jsp:include page="/WEB-INF/jsp/foot.jsp"/>
</body>
</html>
