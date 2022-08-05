<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<%--
  Created by IntelliJ IDEA.
  User: shaba
  Date: 29.07.2022
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Информация о машинах</h2>
<security:authorize access="hasRole('SECRETARY')">
<input type="button" value="Посмотреть типы машин"
       onclick="window.location.href='secretary_info'"/>
</security:authorize>
<br><br>
<security:authorize access="hasRole('DIRECTOR')">
<input type="button" value="Посмотреть мощности машин"
       onclick="window.location.href='director_info'"/>
</security:authorize>
<br><br>
</body>
</html>
