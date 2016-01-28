<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<sf:form modelAttribute="invoiceList" method="get">
<c:set var="total" scope="session" value="0"/>
	<table class="invoicetable" id="listinventory">

		<thead>
			<tr>
				<th>Item Number</th>
				<th>Product Name</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="item"
				items="${invoiceList}" varStatus="i" begin="0">
				<c:set var="total" value="${total + (item.price * item.amount)}"/>
				<tr class="invoice">
					<td>${item.itemNum}</td>
					<td>${item.productName}</td>
					<td>${item.amount}</td>
					<td>P${item.price * item.amount}</td>
					<td><a href="#" onclick="rowRemoved(${i.index});"
						class="removeAccount"><img alt="[Remove]"
							src="<c:url value='/static/images/web/delete.gif' />" /></a></td>
				</tr>
			</c:forEach>
			<tr>
				<td>Total = P${total}</td>
			</tr>
		</tbody>
	</table>

</sf:form>

<script type="text/javascript">
	function rowRemoved(row) {
		
  		var inputs = document.getElementById('listinventory').getElementsByTagName('input');
		var column = (row * 2);
		var key = inputs[column].value;
		    if (confirm("Are you sure you want to remove '" + inputs[column + 1].value + "' from inventroy?") == true) {
		   		window.location.href = "${pageContext.request.contextPath}/deleteinventory?deleteKey=" + key;		    
		   	} 
	}
	
</script>
