<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/podsave"
	commandName="invHeader">
	<h2>Invoice # <fmt:formatNumber type="number" pattern="00000000" value="${invHeader.invoiceNum}"/></h2>
	<h4>Shipped on <fmt:formatDate type="date" value="${invHeader.dateShipped}"/> </h4>
	<table>
		<tr>
			<td>Sales -------------------------></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invHeader.total}" /></td>
		</tr>
		<tr>
			<td>Shipping Cost -----------------></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invHeader.shippingCost}" /></td>
		</tr>
		<tr>
			<td>Added Charges -----------------></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invHeader.addedCharges}" /></td>
		</tr>
		<tr>
			<td>Subtotal ----------------------></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invHeader.total + invHeader.shippingCost + invHeader.addedCharges}" /></td>
		</tr>
		<tr>
			<td>Tax ---------------------------></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invHeader.totalTax}" /></td>
		</tr>
		<tr>
			<td>Grand Total -------------------></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invHeader.total + invHeader.shippingCost + invHeader.addedCharges + invHeader.totalTax}" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="OK" /></td>
		</tr>
		<tr>
			<td><input type="hidden" name="invoiceNum" value="${invHeader.invoiceNum}" /></td>
			<td><input type="hidden" name="userID" value="${invHeader.userID}" /></td>
			<td><input type="hidden" name="total" value="${invHeader.total}" /></td>
			<td><input type="hidden" name="totalTax" value="${invHeader.totalTax}" /></td>
			<td><input type="hidden" name="shippingCost" value="${invHeader.shippingCost}" /></td>
			<td><input type="hidden" name="addedCharges" value="${invHeader.addedCharges}" /></td>
			<td><input type="hidden" name="pod" value="${invHeader.pod}" /></td>
			<td><input type="hidden" name="modified" value="${invHeader.modified}" /></td>
			<td><input type="hidden" name="processed" value="${invHeader.processed}" /></td>
			<td><input type="hidden" name="dateShipped" value="${invHeader.dateShipped}" /></td>
		</tr>
	</table>
</sf:form>