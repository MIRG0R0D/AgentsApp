<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<table border="1">
    <thead>
    <tr>
        <th>code name</th>
        <th>location</th>
        <th>description</th>
        <th>start</th>
        <th>end</th>
    </tr>
    </thead>
    <c:forEach items="${missions}" var="mission">
        <tr>
            <td><c:out value="${mission.codeName}"/></td>
            <td><c:out value="${mission.location}"/></td>
            <td><c:out value="${mission.description}"/></td>
            <td><c:out value="${mission.start}"/></td>
            <td><c:out value="${mission.end}"/></td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/missions/delete?id=${mission.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Delete"></form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/missions/update?id=${mission.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Edit"></form>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add mission</h2>
<c:if test="${not empty chyba}">
    <div style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${chyba}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/missions/add" method="post">
    <table>
        <tr>
            <th>code name:</th>
            <td><input type="text" name="codeName" value="<c:out value='${mission.codeName}'/>"/></td>
        </tr>
        <tr>
            <th>location:</th>
            <td><input type="text" name="location" value="<c:out value='${mission.location}'/>"/></td>
        </tr>
        <tr>
            <th>description:</th>
            <td><input type="text" name="description" value="<c:out value='${mission.description}'/>"/></td>
        </tr>
        <tr>
            <th>start:</th>
            <td><input type="text" name="start" value="<c:out value='${mission.start}'/>"/></td>
        </tr>
        <tr>
            <th>end:</th>
            <td><input type="text" name="end" value="<c:out value='${mission.end}'/>"/></td>
        </tr>
    </table>
    <input type="Submit" value="Add"/>
</form>


</body>
</html>