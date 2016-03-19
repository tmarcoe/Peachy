<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<form  method="get" action="${pageContext.request.contextPath}/replenish">
	<table>
		<tr>
			<td><h4>Please enter the product #</h4></td>
		</tr>
		<tr>
			<td><input name="sku" type="text" /></td>
		</tr>
		<tr>
			<td><div class="error">${error}</div></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit"></td>
		</tr>
	</table>
</form>
