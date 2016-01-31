<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<h2>Products</h2>

<form:form id="pgform" method="post" modelAttribute="pparam"
	action="${pageLink}">
	<table class="inventorytable">

		<thead>
			<tr>
				<th>&nbsp;</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>On Sale For...</th>
			</tr>
		</thead>
		<c:forEach var="inventory" items="${inventoryList.pageList}">
			<fmt:formatNumber type="currency" currencySymbol="P" value="${inventory.salePrice}" var="saleprice"/>
			<fmt:formatNumber type="currency" currencySymbol="P" value="${inventory.discountPrice}" var="discountprice"/>
			<c:choose>
				<c:when test="${inventory.onSale == true}">
					<c:set var="saleprice" value="<s>${saleprice}</s>" />
					<c:set var="discountprice" value="<b>${discountprice}</b>" />
				</c:when>
				<c:otherwise>
					<c:set var="discountprice" value="" />
				</c:otherwise>
			</c:choose>

			<tr>
				<td><a href="productdetails?skuNum=${inventory.skuNum}"> <img alt="Image Not Available"
						src="${pageContext.request.contextPath}/static/images/products/${inventory.image}"
						height="70" width="70">
				</a></td>
				<td width="600">${inventory.productName}</td>
				<td>${saleprice}</td>
				<td>${discountprice}</td>
			</tr>
			<tr>
			<td colspan="4">
			<hr />
			</td>
			</tr>
		</c:forEach>

	</table>


</form:form>
<div class="paging">
	<c:if test="${inventoryList.isFirstPage()==false}">
		<a href="paging?page=prev"><img alt="[Prev]"
			src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
	</c:if>
	<c:forEach begin="1" end="${inventoryList.getPageCount()}" var="i">

		<c:choose>
			<c:when test="${(i-1)!=inventoryList.getPage()}">
				<a href="paging?page=${i-1}"><span class="paging"><c:out
							value="${i}" /></span></a>
			</c:when>
			<c:otherwise>
				<span class="paging"><c:out value="${i}" /></span>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<%--For displaying Next link --%>
	<c:if test="${inventoryList.isLastPage()==false}">
		<a href="paging?page=next"><img alt="[Next]"
			src="<c:url value='/static/images/web/button_next.gif'/>"></a>
	</c:if>
</div>
