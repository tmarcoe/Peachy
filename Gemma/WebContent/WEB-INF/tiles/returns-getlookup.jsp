<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<sf:form method="post" action="${pageContext.request.contextPath}/returns-submit" commandName="returns" >
	<table>
		<thead>
			<tr>
				<th>Invoice #</th>
				<th>SKU #</th>
				<th>Amount to Return</th>
			</tr>
		</thead>
		<tr>
			<td><sf:input type="number" path="invoiceNum" /></td>
			<td><sf:input type="text" path="skuNum" /></td>
			<td><sf:input type="number" path="amtReturned" />
		</tr>
		<tr>
			<td><div class="error"><sf:errors path="invoiceNum" /></div></td>
			<td><div class="error"><sf:errors path="skuNum" /></div></td>
			<td><div class="error"><sf:errors path="amtReturned" /></div></td>
		</tr>
	</table>
	<table>
		<tr>
			<th>Reason</th>
		</tr>
		<tr>
			<td><sf:textarea path="reason" rows="10" cols="50"/></td>
		</tr>
		<tr>
			<td><div class="error"><sf:errors path="reason" /></div></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</sf:form>