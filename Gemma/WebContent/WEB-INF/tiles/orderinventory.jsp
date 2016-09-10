<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
	<c:when test="${objectList.pageList.size() > 0}">
<form>
	<table class="ordertable">
		<thead>
			<tr>
				<th>Product</th>
				<th>Price</th>
				<th>Tax</th>
				<th>In Stock</th>
				<th>&nbsp</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${objectList.pageList}">
				<tr>
					<td>${item.productName}</td>
					<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}" value="${item.salePrice}" /></td>
					<td><fmt:formatNumber type="currency" currencySymbol="${currencySymbol}" value="${item.taxAmt}" /></td>
					<td>${item.amtInStock}</td>
					<td><a href="${pageContext.request.contextPath}/replenish?sku=${item.skuNum}" >[Order]</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</form>
</c:when>
	<c:otherwise>
		<h2>Inventory is at normal levels</h2>
	</c:otherwise>
</c:choose>