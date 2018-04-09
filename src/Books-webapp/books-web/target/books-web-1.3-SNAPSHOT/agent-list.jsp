<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<table border="1">
    <thead>
    <tr>
        <th>name</th>
        <th>level</th>
        <th>born</th>
    </tr>
    </thead>
    <c:forEach items="${agents}" var="agent">
        <tr>
            <td><c:out value="${agent.name}"/></td>
            <td><c:out value="${agent.level}"/></td>
            <td><c:out value="${agent.born}"/></td>
            <td><form method="post" action="${pageContext.request.contextPath}/agents/delete?id=${agent.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Delete"></form></td>
            <td><form method="post" action="${pageContext.request.contextPath}/agents/update?id=${agent.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Edit"></form></td>
        </tr>
    </c:forEach>
</table>

<h2>Add agent</h2>
<c:if test="${not empty chyba}">
    <div style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${chyba}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/agents/add" method="post">
    <table>
        <tr>
            <th>name:</th>
            <td><input type="text" name="name" value="<c:out value='${agent.name}'/>"/></td>
        </tr>
        <tr>
            <th>born:</th>
            <td><input type="text" name="born" value="<c:out value='${agent.born}'/>"/></td>
        </tr>
        <tr>
            <th>level:</th>
            <td><input type="text" name="level" value="<c:out value='${agent.level}'/>"/></td>
        </tr>
    </table>
    <input type="Submit" value="Add" />
</form>


</body>
</html>