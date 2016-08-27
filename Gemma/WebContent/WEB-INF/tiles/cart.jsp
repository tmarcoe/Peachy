<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
$(document).ready(function(){
	alert("This site is currently in beta and not accepting orders." + 
		  "\nHowever, you are welcome to register and browse the products." + 
		  "\nKeep in mind that the inventory might change.");
});
</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/vieworder">
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
				<td>&nbsp;</td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td>Delete</td>
					<td>&nbsp;</td>
					<td>Edit</td>
				</c:if>
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
					<td><fmt:formatNumber type='currency' currencySymbol='${currencySymbol}'
							value='${pr * rate}' /></td>
					<td><fmt:formatNumber type='currency' currencySymbol='${currencySymbol}'
							value='${tx * rate}' /></td>
					<td>&nbsp;</td>
					<c:if test="${invoice.invoiceHeader.processed == null}">
						<td><a href="#" onclick="rowRemoved(${i.index});"
							class="removeAccount"><img alt="[Remove]"
								src="<c:url value='/static/images/web/delete.jpg' />"></a></td>
						<td>&nbsp;</td>
						<c:if test="${pr >= 0}">
							<td><a href="#" onclick="getDetail(${i.index});"
								class="inventorydetail"><img alt="[Show Detail]"
									src="<c:url value='/static/images/web/edit.jpg' />"></a></td>
						</c:if>
						<c:if test="${pr < 0}">
							<td>&nbsp;</td>
						</c:if>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot class="tablefooter">
			<tr>
				<td colspan="6">Subtotal =======></td>
				<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
						value="${total * rate}" /></td>
				<td>&nbsp;</td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td colspan="3">&nbsp;</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="6">Total Tax =======></td>
				<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
						value="${ttax * rate}" /></td>
				<td>&nbsp;</td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td colspan="3">&nbsp;</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="6">POD Charge ======></td>
				<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
						value="${invoice.invoiceHeader.addedCharges * rate}" /></td>
				<td>&nbsp;</td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td colspan="3">&nbsp;</td>
				</c:if>
			</tr>
			<tr>
				<td colspan="6">Shipping Charge ======></td>
				<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
						value="${invoice.invoiceHeader.shippingCost * rate}" /></td>
				<td>&nbsp;</td>			
			</tr>
			<tr>
				<td colspan="6">Total =======></td>
				<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
						value="${(total + ttax + invoice.invoiceHeader.addedCharges + invoice.invoiceHeader.shippingCost) * rate}" /></td>
				<td>&nbsp;</td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td colspan="3">&nbsp;</td>
				</c:if>
			</tr>
			<c:if test="${invoice.invoiceHeader.processed == null}">
				<tr>
					<td colspan="11">&nbsp;</td>
				</tr>
				<tr>
					<td><input type="submit" value="Check Out" /></td>
					<td><input type="button" Value="Cancel Order"
						onclick="cancel()" /></td>
					<td><input type="button" value="Continue Shopping"
						onclick="followLink('/pickcategory')" /></td>
					<td>Coupon: <input type="text" name="couponNum" value="CPN" /></td>
					<td colspan="7"><div class="error">${errorMsg}</div></td>
				</tr>
				<!-- <tr>
					<td><img alt="7-connect"
						src="/static/images/web/paymethods_logo.png"> <input
						type="radio" name="invoiceHeader.paymentType" value="7-connect" />
					</td>
					<td><img alt="Payment Card"
						src="/static/images/web/png-vmda-large.png"> <input
						type="radio" name="invoiceHeader.paymentType" value="braintree" />
					</td>
					<td colspan="9">&nbsp;</td>
				</tr> -->
			</c:if>
		</tfoot>
	</table>
</sf:form>
<script type="text/javascript">
	function rowRemoved(row) {

		var inputs = document.getElementById('listinvoice')
				.getElementsByTagName('input');
		var column = (row * 2);

		var invoiceNum = inputs[column].value;
		var skuNum = inputs[column + 1].value;

		if (confirm("Are you sure you want to remove SKU number '"
				+ inputs[column + 1].value + "' from the shopping cart?") == true) {
			window.location.href = "${pageContext.request.contextPath}/deleteinvoiceitem?invoiceNum="
					+ invoiceNum + "&skuNum=" + skuNum;
		}
	}
	function getDetail(row) {
		//var paymentType = document.querySelector('input[name="paymentType"]:checked').value;
		//alert("here = " + paymentType);
		var inputs = document.getElementById('listinvoice')
				.getElementsByTagName('input');
		var column = (row * 2);
		var invoiceNum = inputs[column].value;
		var skuNum = inputs[column + 1].value;
		window.location.href = "${pageContext.request.contextPath}/editcart?invoiceNum="
				+ invoiceNum + "&skuNum=" + skuNum;
	}

	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
	function cancel() {
		if (confirm("Are you sure you want to cancel this order?") == true) {
			window.location.href = "${pageContext.request.contextPath}/cancelsale";
		}
	}
	function pod() {
		if (confirm("For POD's a 10% handling fee will be added to your order.") == true) {
			window.location.href = "${pageContext.request.contextPath}/pod";
		}
	}
</script>