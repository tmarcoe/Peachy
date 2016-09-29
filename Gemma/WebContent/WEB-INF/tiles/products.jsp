<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<h4>${filter}</h4>


<form:form id="pgform" method="post" modelAttribute="pparam"
	action="${pageLink}">
	<table class="productlist">

		<thead>
			<tr>
				<th>&nbsp;</th>
				<th>Product Name</th>
				<th class="currency" >Price</th>
				<th class="currency" >On Sale For...</th>
			</tr>
		</thead>
		<c:forEach var="inventory" items="${objectList.pageList}">
			<fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
				value="${inventory.salePrice * rate}" var="saleprice" />
			<fmt:formatNumber type="currency" currencySymbol="${currencySymbol}"
				value="${inventory.discountPrice * rate}" var="discountprice" />
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
				<td><a href="productdetails?skuNum=${inventory.skuNum}"> <img
						alt="Image Not Available"
						src="<c:url value='${fileLoc}${inventory.image}' />"
						width="30">
				</a></td>
				<td width="500">${inventory.productName}</td>
				<td class="currency" >${saleprice}</td>
				<td class="currency" >${discountprice}</td>
			</tr>
			<tr>
				<td colspan="4">
					<hr />
				</td>
			</tr>
		</c:forEach>

	</table>


</form:form>
