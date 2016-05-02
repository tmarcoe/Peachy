<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form modelAttribute="invoiceList" method="get">
	<c:set var="total" scope="session" value="0" />
	<c:set var="pr" scope="session" value="0" />
	<c:set var="ttax" scope="session" value="0" />
	<h3>
		Invoice #
		<fmt:formatNumber type="number"
			value="${invoice.invoiceHeader.invoiceNum}" minIntegerDigits="6"
			groupingUsed="false" />
	</h3>
	<table class="invoicetable" id="listinvoice">

		<thead>
			<tr>
				<th>Item</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>Product Name</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Tax</th>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<th>Delete</th>
					<th>&nbsp;</th>
					<th>Edit</th>
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
					<td><fmt:formatNumber type="number" minIntegerDigits="2"
							groupingUsed="false" value="${i.index + 1}" /></td>
					<td><input type="hidden" value="${item.invoiceNum}" /></td>
					<td><input type="hidden" value="${item.skuNum}"></td>

					<td>${item.productName}</td>
					<td>${item.amount}</td>
					<td><fmt:formatNumber type='currency' currencySymbol='P'
							value='${pr}' /></td>
					<td><fmt:formatNumber type='currency' currencySymbol='P'
							value='${tx}' /></td>
					<c:if test="${invoice.invoiceHeader.processed == null}">
						<td><a href="#" onclick="rowRemoved(${i.index});"
							class="removeAccount"><img alt="[Remove]"
								src="<c:url value='/static/images/web/delete.jpg' />"></a></td>
						<td>&nbsp;</td>
						<td><a href="#" onclick="getDetail(${i.index});"
							class="inventorydetail"><img alt="[Show Detail]"
								src="<c:url value='/static/images/web/edit.jpg' />"></a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot class="tablefooter">
			<tr>
				<td>Subtotal -------></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><fmt:formatNumber type="currency" currencySymbol="P"
						value="${total}" /></td>

				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</c:if>
			</tr>
			<tr>
				<td>Total Tax -------></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><fmt:formatNumber type="currency" currencySymbol="P"
						value="${ttax}" /></td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</c:if>
			</tr>
			<tr>
				<td>Total -------></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><fmt:formatNumber type="currency" currencySymbol="P"
						value="${total + ttax}" /></td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</c:if>
			</tr>
			<c:if test="${invoice.invoiceHeader.processed == null}">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><input type="button" value="Check Out" onclick="followLink('/processcart')" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="button" Value="Cancel Order" onclick="cancel()" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="button" value="Continue Shopping" onclick="followLink('/pickcategory')" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Print this page for your records  </td>
					<td><input type="button" onClick="window.print()"
						value="Print" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</c:if>
		</tfoot>
	</table>
</sf:form>

<script type="text/javascript">
	function rowRemoved(row) {

  		var inputs = document.getElementById('listinvoice').getElementsByTagName('input');
		var column = (row * 2);

		var invoiceNum = inputs[column].value;
		var skuNum = inputs[column + 1].value;
	
		    if (confirm("Are you sure you want to remove SKU number '" + inputs[column + 1].value + "' from the shopping cart?") == true) {
		   		window.location.href = "${pageContext.request.contextPath}/deleteinvoiceitem?invoiceNum=" + invoiceNum + "&skuNum=" + skuNum;		    
		   	} 
	}
	function getDetail(row) {

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
</script>
