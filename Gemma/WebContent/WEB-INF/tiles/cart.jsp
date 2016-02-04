<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form modelAttribute="invoiceList" method="get">
	<c:set var="total" scope="session" value="0" />
	<c:set var="pr" scope="session" value="0" />
	<h1>
		Invoice #
		<fmt:formatNumber type="number"
			value="${invoice.invoiceHeader.invoiceNum}" minIntegerDigits="6"
			groupingUsed="false" />
	</h1>
	<table class="invoicetable" id="listinvoice">

		<thead>
			<tr>
				<th>Item</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>Product Name</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="item" items="${invoice.invoiceList}" varStatus="i"
				begin="0">
				<c:set var="pr" value="${item.price * item.amount}" />
				<c:set var="total" value="${total + pr}" />
				<tr>
					<td><fmt:formatNumber type="number" minIntegerDigits="2"
							groupingUsed="false" value="${i.index + 1}" /></td>
					<td><input type="hidden" value="${item.invoiceNum}" /></td>
					<td><input type="hidden" value="${item.skuNum}"></td>
					<td><input type="text" value="${item.productName}"
						readonly="readonly" /></td>
					<td><input type="number" value="${item.amount}"
						readonly="readonly" /></td>
					<td><input type="text"
						value="<fmt:formatNumber type='currency' currencySymbol='P' value='${pr}' />"
						readonly="readonly" /></td>
					<c:if test="${invoice.invoiceHeader.processed == null}">
						<td><a href="#" onclick="rowRemoved(${i.index});"
							class="removeAccount"><img alt="[Remove]"
								src="<c:url value='/static/images/web/minus.png' />"></a></td>
						<td>&nbsp;</td>
						<td><a href="#" onclick="getDetail(${i.index});"
							class="inventorydetail"><img alt="[Show Detail]"
								src="<c:url value='/static/images/web/edit.png' />"></a></td>
					</c:if>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4"><b>Total Price-------> <fmt:formatNumber
							type="currency" currencySymbol="P" value="${total}" />
				</b></td>
				<c:if test="${invoice.invoiceHeader.processed == null}">
					<td><a href="${pageContext.request.contextPath}/processcart"
						class="button">Check Out</a></td>
				</c:if>
			</tr>
		</tbody>
	</table>

</sf:form>

<script type="text/javascript">
	function rowRemoved(row) {

  		var inputs = document.getElementById('listinvoice').getElementsByTagName('input');
		var column = (row * 5);

		var invoiceNum = inputs[column].value;
		var skuNum = inputs[column + 1].value;
	
		    if (confirm("Are you sure you want to remove '" + inputs[column + 2].value + "' from the shopping cart?") == true) {
		   		window.location.href = "${pageContext.request.contextPath}/deleteinvoiceitem?invoiceNum=" + invoiceNum + "&skuNum=" + skuNum;		    
		   	} 
	}
	function getDetail(row) {

		var inputs = document.getElementById('listinvoice')
				.getElementsByTagName('input');
		var column = (row * 5);
		var invoiceNum = inputs[column].value;
		var skuNum = inputs[column + 1].value;
		window.location.href = "${pageContext.request.contextPath}/editcart?invoiceNum="
				+ invoiceNum + "&skuNum=" + skuNum;
	}
	
</script>
