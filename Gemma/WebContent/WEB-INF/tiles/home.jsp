<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Daily Specials</h2>

<table class="dailyspecials">
	<thead>
		<tr>
			<th>&nbsp;</th>
			<th>Product Name</th>
			<th>Now On Sale For...</th>

		</tr>
	</thead>
	<c:forEach var="inventory" items="${inventory}">
		<c:set var="price" value="${inventory.discountPrice}" />
		<tr class="inventoryrow">
			<td><a
				href="${pageContext.request.contextPath}/productdetails?skuNum=${inventory.skuNum}">
					<img alt="Image Not Available"
					src="<c:url value='/static/images/products/${inventory.image}' />"
					width="70" height="80">
			</a></td>
			<td class="name" width="600"><c:out
					value="${inventory.productName}"></c:out></td>
			<td class="price"><fmt:formatNumber type="currency" currencySymbol="P" value="${price}"/></td>
		</tr>
		<tr>
		<!--<td colspan="3">________________________________________________________________________________________________________</td> -->
		<td colspan="3"><hr /></td>
		</tr>
	</c:forEach>


</table>