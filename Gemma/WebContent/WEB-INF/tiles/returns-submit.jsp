<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<sf:form method="post"
	action="${pageContext.request.contextPath}/returns-save"
	commandName="returns">
	<table class="returns">
		<thead>
			<tr>
				<th>Invoice #</th>
				<th>SKU #</th>
				<th>Refund</th>
				<th># of Items</th>
				<th>Date Purchased</th>
			</tr>
		</thead>
		<tr>
			<td><fmt:formatNumber type="number" pattern="000000"
					value="${returns.invoiceNum}" /></td>
			<td>${returns.skuNum}</td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${returns.purchaseTax + returns.purchasePrice}" /></td>
			<td>${returns.amtReturned}</td>
			<td><fmt:formatDate value="${returns.datePurchased}" /></td>
		</tr>
	</table>
	<sf:hidden path="invoiceNum" />
	<sf:hidden path="skuNum" />
	<sf:hidden path="purchasePrice" />
	<sf:hidden path="purchaseTax" />
	<sf:hidden path="amtReturned" />

	<sf:input type="hidden" path="datePurchased" />

	<table>
		<tr><th>&nbsp;</th></tr>
		<tr>
			<th>Reason for Return</th>
		</tr>
		<tr>
			<td><sf:textarea path="reason" rows="10" cols="50" readonly="true" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>
	</table>
</sf:form>