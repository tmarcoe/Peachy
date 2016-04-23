<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<sf:form method="post"
	action="${pageContext.request.contextPath}/returns-update"
	commandName="returns">
	<table class="rmadetails">
		<caption>Request</caption>
		<tr>
			<th>RMA #</th>
			<th>Invoice #</th>
			<th>SKU #</th>
			<th>Amount to Return</th>
		</tr>
		<tr>
			<td><fmt:formatNumber type="number" pattern="00000000"
					value="${returns.rmaId}" /></td>
			<td><fmt:formatNumber type="number" pattern="00000000"
					value="${returns.invoiceNum}" /></td>
			<td>${returns.skuNum}</td>
			<td>${returns.amtReturned}</td>
		</tr>
		<tr>
			<th>Price</th>
			<th>Tax</th>
			<th>Return Amount</th>
			<th>Date Purchased</th>
		</tr>
		<tr>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${returns.purchasePrice}" /></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${returns.purchaseTax}" /></td>
			<td>${returns.amtReturned}</td>
			<td><fmt:formatDate value="${returns.datePurchased}" /></td>
		</tr>
		<tr>
			<th colspan="4">Reason</th>
		</tr>
		<tr>
			<td colspan="4">${returns.reason}</td>
		</tr>
	</table>
	<hr />
	<table class="rmadetails">
		<caption>Result</caption>
		<tr>
			<th>Descision</th>
		</tr>
		<tr>
			<td>
				<sf:radiobutton path="decision" value="A" />Approve
				<sf:radiobutton path="decision" value="D" />Decliine
			</td>
			<td>Return To Stock?</td>
			<td><input type="hidden" value="on" name="_active"/>
			<sf:checkbox path="returnToStock" class="control" /></td>
		</tr>
		<tr>
			<th>Comments</th>
		</tr>
		<tr>
		<td><sf:textarea path="reasonForDecision" rows="5" cols="50" /></td>
		</tr>
	</table>
	<sf:hidden path="rmaId" />
	<sf:hidden path="invoiceNum" />
	<sf:hidden path="skuNum" />
	<sf:hidden path="purchasePrice" />
	<sf:hidden path="purchaseTax" />
	<sf:hidden path="amtReturned" />
	<sf:hidden path="datePurchased" />
	<sf:hidden path="dateReturned" />
	<sf:hidden path="reason" />
	<sf:hidden path="username"/>

	<input type="submit" value="Submit" />
</sf:form>