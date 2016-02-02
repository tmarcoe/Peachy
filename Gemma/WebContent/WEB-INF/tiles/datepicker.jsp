<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/generalledger"
	commandName="datePicker" >
	<table>
		<thead>
			<tr>
				<th>Start Date</th>
				<th>End Date</th>
			</tr>
		</thead>
		<tr>
			<td><sf:input type="date" path="start" format="yyyy-MM-dd"/></td>
			<td><sf:input type="date" path="end"  format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</sf:form>