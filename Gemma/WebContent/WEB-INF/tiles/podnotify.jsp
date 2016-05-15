<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form method="post"	action="${pageContext.request.contextPath}/podconfirm">
	<table>
		<tr>
			<td>Invoice Number</td>
		</tr>
		<tr>
			<td><input type="text" id="invNum" name="invNum" /></td>
			<td><div class="error">${errMsg}</div>
		</tr>
		<tr>
			<td><input type="submit" value="OK" /></td>
		</tr>
	</table>
</form >