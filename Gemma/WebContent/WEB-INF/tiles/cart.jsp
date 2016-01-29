<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form modelAttribute="invoiceList" method="get">
	<c:set var="total" scope="session" value="0" />
	<c:set var="pr" scope="session" value="0" />
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
			<c:forEach var="item" items="${invoiceList}" varStatus="i" begin="0">
				<c:set var="pr" value="${item.price * item.amount}" />
				<c:set var="total" value="${total + pr}" />
				<tr>
					<td><input type="number" value="${i.index}" pattern="0#"
						readonly="readonly" width="2em" /></td>
					<td><input type="text" value="${item.productName}"
						readonly="readonly" /></td>
					<td><input type="number" value="${item.amount}"
						readonly="readonly" /></td>
					<td><input type="number" value="${pr}" readonly="readonly" /></td>
					<td><a href="#" onclick="rowRemoved(${i.index});"
						class="removeAccount"><img alt="[Remove]"
							src="<c:url value='/static/images/web/delete.gif' />"></a></td>
					<td>&nbsp;</td>
					<td><a href="#" onclick="getDetail(${i.index});"
						class="inventorydetail"><img alt="[Show Detail]"
							src="<c:url value='/static/images/web/details.gif' />"></a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" ><b>Total Price------->P${total}</b></td>
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
	function getDetail(row) {

		var inputs = document.getElementById('listusers')
				.getElementsByTagName('input');
		var column = (row * 4);
		var key = inputs[column + 2].value;
		window.location.href = "${pageContext.request.contextPath}/userdetails?detailKey="
				+ key;
	}
	
</script>
