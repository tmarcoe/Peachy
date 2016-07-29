<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<div class="returnsAdmin">
	<c:choose>
		<c:when test="${objectList.pageList.size() > 0}">
			<form:form id="pgform" method="post" modelAttribute="objectList"
				action="${pageContext.request.contextPath}/returns-approve">
				<table class="viewreturns">
					<thead>
						<tr>
							<th>RMA #</th>
							<th>Invoice #</th>
							<th>SKU #</th>
							<th>Date Purchased</th>
							<th>Review</th>
						</tr>
					</thead>
					<c:forEach var="item" items="${objectList.pageList}">
						<tr>
							<td><fmt:formatNumber type="number" pattern="00000000"
									value="${item.rmaId}" /></td>
							<td><fmt:formatNumber type="number" pattern="00000000"
									value="${item.invoiceNum}" /></td>
							<td>${item.skuNum}</td>
							<td><fmt:formatDate value="${item.datePurchased}" /></td>
							<td><a href="${pageContext.request.contextPath}/returns-approve?rmaId=${item.rmaId}" ><img alt="[Review]" src="<c:url value='/static/images/web/edit.jpg' />"></a></td>
						</tr>
					</c:forEach>
				</table>
			</form:form>
		</c:when>
		<c:otherwise>
			<h1>No Returns to print</h1>
		</c:otherwise>
	</c:choose>
</div>