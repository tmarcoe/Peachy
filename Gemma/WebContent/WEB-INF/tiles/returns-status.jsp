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
	<c:forEach var="item" items="${returns.pageList}">
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
<c:if test="${returns.getPageCount() > 1}">
	<div class="paging">
		<c:if test="${returns.isFirstPage()==false}">
			<a href="returnsstatuspaging?page=prev"><img alt="[Prev]"
				src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
		</c:if>
		<c:forEach begin="1" end="${returns.getPageCount()}" var="i">

			<c:choose>
				<c:when test="${(i-1)!= returns.getPage()}">
					<a href="returnsstatuspaging?page=${i-1}"><span class="paging"><c:out
								value="${i}" /></span></a>
				</c:when>
				<c:otherwise>
					<span class="paging"><c:out value="${i}" /></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--For displaying Next link --%>
		<c:if test="${returns.isLastPage()==false}">
			<a href="returnsstatuspaging?page=next"><img alt="[Next]"
				src="<c:url value='/static/images/web/button_next.gif'/>"></a>
		</c:if>
	</div>
</c:if>
