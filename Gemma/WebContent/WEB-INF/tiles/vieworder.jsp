<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="total" scope="session" value="0" />
<c:set var="pr" scope="session" value="0" />
<c:set var="ttax" scope="session" value="0" />

<h3>
	Invoice #
	<fmt:formatNumber type="number"
		value="${invoice.invoiceHeader.invoiceNum}" minIntegerDigits="8"
		groupingUsed="false" />
</h3>
<table class="invoicetable" id="listinvoice">
	<thead class="invoicehead">
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>Item</td>
			<td>Product Name</td>
			<td>Quantity</td>
			<td>Price</td>
			<td>Tax</td>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="item" items="${invoice.invoiceList}" varStatus="i"
			begin="0">
			<c:set var="pr" value="${item.price * item.amount}" />
			<c:set var="tx" value="${item.tax * item.amount}" />
			<c:set var="total" value="${total + pr}" />
			<c:set var="ttax" value="${ttax + tx}" />
			<tr>
				<td><input type="hidden" value="${item.invoiceNum}" /></td>
				<td><input type="hidden" value="${item.skuNum}"></td>
				<td><fmt:formatNumber type="number" minIntegerDigits="2"
						groupingUsed="false" value="${i.index + 1}" /></td>

				<td>${item.productName}</td>
				<td>${item.amount}</td>
				<td><fmt:formatNumber type='currency' currencySymbol='P'
						value='${pr}' /></td>
				<td><fmt:formatNumber type='currency' currencySymbol='P'
						value='${tx}' /></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot class="tablefooter">
		<tr>
			<td colspan="6">Subtotal =======></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${total}" /></td>
		</tr>
		<tr>
			<td colspan="6">Total Tax =======></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${ttax}" /></td>
		</tr>
		<tr>
			<td colspan="6">POD Charge ======></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${invoice.invoiceHeader.addedCharges}" /></td>
		</tr>
		<tr>
			<td colspan="6">Total =======></td>
			<td><fmt:formatNumber type="currency" currencySymbol="P"
					value="${total + ttax + invoice.invoiceHeader.addedCharges}" /></td>
		</tr>
		<tr>
			<td><button type="button" onclick="followLink('/pcinfo')">Submit Order</button></td>
			<td><button type="button" onclick="cancel()">Cancel Order</button></td>
			<td colspan="6"><input type="button" onClick="window.print()"
						value="Print Order" /></td>
		</tr>
	</tfoot>
</table>

<script type="text/javascript" >
function followLink(link) {
	window.location.href = "${pageContext.request.contextPath}" + link;
}

function cancel() {
	if (confirm("Are you sure you want to cancel this order?") == true) {
		window.location.href = "${pageContext.request.contextPath}/cancelsale";
	}
}
</script>

