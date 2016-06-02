<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:choose>
	<c:when test="${historyList.pageList.size() > 0}">
		<table class="shoppingtable">
			<tr>
				<th>Invoice #</th>
				<th>Grand Total</th>
				<th>Purchased On</th>
				<th>Processed On</th>
			</tr>
			<c:forEach var="item" items="${historyList.pageList}">
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
		<c:if test="${historyList.getPageCount()>1}">
			<div class="paging">
				<c:if test="${historyList.isFirstPage()==false}">
					<a href="historypaging?page=prev"><img alt="[Prev]"
						src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
				</c:if>
				<c:forEach begin="1" end="${historyList.getPageCount()}" var="i">

					<c:choose>
						<c:when test="${(i-1)!=historyList.getPage()}">
							<a href="historypaging?page=${i-1}"><span class="paging"><c:out
										value="${i}" /></span></a>
						</c:when>
						<c:otherwise>
							<span class="paging"><c:out value="${i}" /></span>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%--For displaying Next link --%>
				<c:if test="${historyList.isLastPage()==false}">
					<a href="historypaging?page=next"><img alt="[Next]"
						src="<c:url value='/static/images/web/button_next.gif'/>"></a>
				</c:if>
			</div>
		</c:if>
	</c:when>
	<c:otherwise>
		<h1>No Purchase History</h1>
	</c:otherwise>
</c:choose>