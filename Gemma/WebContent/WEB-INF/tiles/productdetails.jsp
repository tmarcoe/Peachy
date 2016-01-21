<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/orderproduct"
	commandName="inventory">
	<c:choose>
		<c:when test="${inventory.onSale == true}">
			<c:set var="price" value="${inventory.discountPrice}" />
		</c:when>
		<c:otherwise>
			<c:set var="price" value="${inventory.salePrice}" />
		</c:otherwise>
	</c:choose>
	<table class="inventoryDetails">
		<thead>
			<tr class="inventoryheader">
				<th>&nbsp;</th>
				<th>Product Description</th>
				<th>Price</th>
			</tr>
		</thead>
		<tr class="detailrow">
			<td><img alt="Image Not Available"
				src="<c:url value='/static/images/${inventory.image}' />"
				width="120" height="140"></td>
			<td class="name" width="600"><c:out
					value="${inventory.description}"></c:out></td>
			<td class="price"><c:out value="${price}"></c:out></td>
		</tr>
	</table>
	<a href="#" class="snipcart-add-item"
		data-item-id="${inventory.skuNum}"
		data-item-name="${inventory.productName}" data-item-price="${price}"
		data-item-weight="20"
		data-item-url="https://67f90c5a.ngrok.io/productdetails?skuNum=${inventory.skuNum}"
		data-item-description="${inventory.description}"> <img
		alt="Add to Carte" src="<c:url value='/static/images/cart.ico' />"
		width="50" height="60"><br>Add to Cart
	</a>
</sf:form>