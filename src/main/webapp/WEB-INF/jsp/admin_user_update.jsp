<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 26.05.2021
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
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
  <form action="${pageContext.request.contextPath}/testing/admin/user/update" align="center" method="post">
    <p>
      <label>
        <input type="text" required placeholder="<fmt:message key="text.id"/>" name="userId"/>
      </label>
    </p>
    <p>
      <label>
        <input type="text" pattern="^[A-Z][a-z]{1,20}$" placeholder="<fmt:message key="text.name"/>"
               name="userName"/>
      </label>
    </p>
    <p>
      <label>
        <input type="text" pattern="^[A-Z][a-z]{1,20}$" placeholder="<fmt:message key="text.surname"/>"
               name="userSurname"/>
      </label>
    </p>
    <p>
      <label>
        <input type="email" placeholder="<fmt:message key="text.email"/>" name="userEmail"/>
      </label>
    </p>
    <p>
      <label>
        <input type="password" placeholder="<fmt:message key="text.password"/>" name="userPassword"/>
      </label>
    </p>
    <p>
      <label>
        <input type="text" pattern="BLOCKED|UNBLOCKED" placeholder="<fmt:message key="text.status.change"/>" name="userStatus"/>
      </label>
    </p>
    <div>
      <input type="submit" class="btn" value="<fmt:message key="text.update"/>"/>
      <c:if test="${requestScope.updateError}">
      <div>
        <fmt:message key="text.update.error"/>
      </div>
      </c:if>
      <c:if test="${requestScope.userExist}">
      <div>
        <fmt:message key="text.user.exist"/>
      </div>
      </c:if>
  </form>
</div>
<jsp:include page="/WEB-INF/jsp/foot.jsp"/>
</body>
</html>
