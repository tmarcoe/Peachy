<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="returns-status" >
	<thead>
		<tr>
			<th>RMA #</th>
			<th>Date</th>
			<th>Status</th>
			<th>Reason</th>
		</tr>
	</thead>
	<c:forEach var="item" items="${objectList.pageList}">
		<c:if test="${item.decision == null}">
			<c:set var="status" value="In Process"/>
		</c:if>
		<c:if test="${item.decision == 'A'}">
			<c:set var="status" value="Approved" />
		</c:if>
		<c:if test="${item.decision == 'D'}">
			<c:set var="status" value="Declined" />
		</c:if>
		<tr>
			<td><fmt:formatNumber type="number" pattern="00000000" value="${item.rmaId}"/></td>
			<td><fmt:formatDate value="${item.dateReturned}"/></td>
			<td>${status}</td>
			<td>${item.reasonForDecision}</td>
		</tr>
	</c:forEach>
</table>
