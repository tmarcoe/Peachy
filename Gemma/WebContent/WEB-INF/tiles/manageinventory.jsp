<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<sf:form modelAttribute="inventoryContainer1" method="get">
 	<table  class="inventorytable" id="listinventory">
    	<thead>
        	<tr>
             	<th>SKU Number</th>
             	<th>Product Name</th>
			</tr>
		</thead>

		<tbody >

			<c:forEach items="${inventoryContainer1.inventoryList}" var="inventoryList" varStatus="i" begin="0" > 
				<tr class="account">    
					<td><sf:input type="text" path="inventoryList[${i.index}].skuNum" readonly = "true"/></td>
					<td><sf:input type="text " path="inventoryList[${i.index}].productName" readonly = "true"/></td>
					<td><a href="#" onclick="rowRemoved(${i.index});" class="removeAccount"><img alt="[Remove]" 
					src="<c:url value='/static/images/web/delete.gif' />" /></a></td>
					<td>&nbsp;</td>
					<td><a href="#" onclick="inventoryDetail(${i.index});" class="inventorydetail"><img alt="[Show Details]" 
					src="<c:url value='/static/images/web/details.gif' />" /></a>
				</tr>
			</c:forEach>
		</tbody>
	</table>

    <a href="${pageContext.request.contextPath}/uploadfile">Add Product</a>&nbsp;&nbsp;
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
	
	function inventoryDetail(row) {

  		var inputs = document.getElementById('listinventory').getElementsByTagName('input');
		var column = (row * 2);
		var key = inputs[column].value;
		window.location.href = "${pageContext.request.contextPath}/inventorydetails?InventoryKey=" + key;	
	}
 
</script>
