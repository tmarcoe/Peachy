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

		<c:forEach items="${objectList.pageList}" var="item">
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
	<tfoot class="tablefooter">
		<tr><td><button type="button" onclick="followLink('/admin')">OK</button></td></tr>
	</tfoot>
</table>

<script type="text/javascript">
function followLink(link) {
	window.location.href = "${pageContext.request.contextPath}" + link;
}
</script>