<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
	<c:when test="${objectList.pageList.size() > 0}">
		<table class="shoppingtable">
			<tr>
				<th>Invoice #</th>
				<th>Grand Total</th>
				<th>Purchased On</th>
				<th>Processed On</th>
			</tr>
			<c:forEach var="item" items="${objectList.pageList}">
				<c:set var="price"
					value="${item.total + item.totalTax + item.shippingCost + item.addedCharges}"></c:set>
				<fmt:formatDate value="${item.processed}" var="purchased" />
				<fmt:formatDate value="${item.dateShipped}" var="shipped" />

				<tr>
					<td><fmt:formatNumber type="number" pattern="00000000"
							value="${item.invoiceNum}" /></td>
					<td><fmt:formatNumber type="currency" currencySymbol="P"
							value="${price}" /></td>
					<td>${purchased}</td>
					<td>${shipped}</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h1>No Purchase History</h1>
	</c:otherwise>
</c:choose>