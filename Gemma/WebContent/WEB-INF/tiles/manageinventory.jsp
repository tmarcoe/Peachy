<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<sf:form modelAttribute="objectList" method="get">

	<table class="tableview" id="listinventory">
		<thead>
			<tr>
				<th>SKU Number</th>
				<th>Product Name</th>
				<th>On Sale?</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>Delete</th>
				<th>&nbsp;</th>
				<th>Edit</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${objectList.pageList}" var="item" varStatus="i"
				begin="0">
				<tr class="account">
					<td>${item.skuNum}</td>
					<td>${item.productName}</td>
					<td>${item.onSale}</td>
					<td><input type="hidden" value="${item.skuNum}" /></td>
					<td><input type="hidden" value="${item.productName}" /></td>
					<td><a href="#" onclick="rowRemoved(${i.index});"
						class="removeAccount"><img alt="[Remove]"
							src="<c:url value='/static/images/web/delete.jpg' />" /></a></td>
					<td>&nbsp;</td>
					<td><a href="#" onclick="productDetail(${i.index});"
						class="productdetail"><img alt="[Show Details]"
							src="<c:url value='/static/images/web/edit.jpg' />" /></a>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">&nbsp;</td>
			</tr>

			<tr>
				<td><button type="button" onclick="followLink('/uploadfile')">Add Product</button>
				<td><button type="button" onclick="followLink('/admin')">Back</button></td>
			</tr>
		</tfoot>
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
	
	function productDetail(row) {	
  		var inputs = document.getElementById('listinventory').getElementsByTagName('input');
		var column = (row * 2);
		var key = inputs[column].value;		
		window.location.href = "${pageContext.request.contextPath}/inventorydetails?InventoryKey=" + key;	
	}
	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
 
</script>
