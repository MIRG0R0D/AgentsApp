<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Edit mission</h2>
<c:if test="${not empty chyba}">
    <div style="border: solid 1px red; background-color: yellow; padding: 10px">
        <c:out value="${chyba}"/>
    </div>
</c:if>
<form action="${pageContext.request.contextPath}/missions/editSave?id=${mission.id}" method="post">
    <table>
        <tr>
            <th>codename:</th>
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
    <input type="Submit" value="Save" />
</form>

</body>
</html>
