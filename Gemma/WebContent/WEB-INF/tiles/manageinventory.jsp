<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<sf:form modelAttribute="inventory" method="get">
	<table class="inventorytable" id="listinventory">
		<thead>
			<tr>
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

			<c:forEach items="${inventory.pageList}" var="item" varStatus="i" begin="0">
				<tr class="account">
					<td>${item.productName}</td>
					<td>${item.onSale}</td>
					<td><input type="hidden" value="${item.skuNum}" /></td>
					<td><input type="hidden" value="${item.productName}" /></td>
					<td><a href="#" onclick="rowRemoved(${i.index});"
						class="removeAccount"><img alt="[Remove]"
							src="<c:url value='/static/images/web/button.gif' />" /></a></td>
					<td>&nbsp;</td>
					<td><a href="#" onclick="inventoryDetail(${i.index});"
						class="inventorydetail"><img alt="[Show Details]"
							src="<c:url value='/static/images/web/button.gif' />" /></a>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="${pageContext.request.contextPath}/uploadfile">Add Product</a>&nbsp;&nbsp;
	</sf:form>
<c:if test="${inventory.getPageCount() > 1}">
	<div class="paging">
		<c:if test="${inventory.isFirstPage()==false}">
			<a href="productpaging?page=prev"><img alt="[Prev]"
				src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
		</c:if>
		<c:forEach begin="1" end="${inventory.getPageCount()}" var="i">

			<c:choose>
				<c:when test="${(i-1)!= inventory.getPage()}">
					<a href="productpaging?page=${i-1}"><span class="paging"><c:out
								value="${i}" /></span></a>
				</c:when>
				<c:otherwise>
					<span class="paging"><c:out value="${i}" /></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--For displaying Next link --%>
		<c:if test="${inventory.isLastPage()==false}">
			<a href="productpaging?page=next"><img alt="[Next]"
				src="<c:url value='/static/images/web/button_next.gif'/>"></a>
		</c:if>
	</div>
</c:if>
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
