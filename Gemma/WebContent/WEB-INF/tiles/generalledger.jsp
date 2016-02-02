<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="viewledger">
	<thead>
		<tr>
			<th>Date</th>
			<th>Account #</th>
			<th>Description</th>
			<th>Debit</th>
			<th>Credit</th>
		</tr>
	</thead>

	<tbody>

		<c:forEach items="${ledgerList.pageList}" var="item">
			<c:choose>
				<c:when test="${item.debitAmt > 0}">
					<fmt:formatNumber type="currency" currencySymbol="P"
						value="${item.debitAmt}" var="debit" />
				</c:when>
				<c:otherwise>
					<c:set var="debit" value="" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${item.creditAmt > 0}">
					<fmt:formatNumber type="currency" currencySymbol="P"
						value="${item.creditAmt}" var="credit" />
				</c:when>
				<c:otherwise>
					<c:set var="credit" value="" />
				</c:otherwise>
			</c:choose>
			<tr class="ledgerrecord">
				<td><fmt:formatDate value="${item.entryDate}" /></td>
				<td>${item.accountNum}</td>
				<td>${item.description}</td>
				<td>${debit}</td>
				<td>${credit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${ledgerList.getPageCount() > 1}">
	<div class="paging">
		<c:if test="${ledgerList.isFirstPage()==false}">
			<a href="ledgerpaging?page=prev"><img alt="[Prev]"
				src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
		</c:if>
		<c:forEach begin="1" end="${ledgerList.getPageCount()}" var="i">

			<c:choose>
				<c:when test="${(i-1)!=ledgerList.getPage()}">
					<a href="ledgerpaging?page=${i-1}"><span class="paging"><c:out
								value="${i}" /></span></a>
				</c:when>
				<c:otherwise>
					<span class="paging"><c:out value="${i}" /></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--For displaying Next link --%>
		<c:if test="${ledgerList.isLastPage()==false}">
			<a href="ledgerpaging?page=next"><img alt="[Next]"
				src="<c:url value='/static/images/web/button_next.gif'/>"></a>
		</c:if>
	</div>
</c:if>