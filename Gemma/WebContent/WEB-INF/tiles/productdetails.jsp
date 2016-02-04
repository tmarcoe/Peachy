<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/orderproduct"
	commandName="invoiceItem">
	<table class="invoiceItemDetails">
		<thead>
			<tr class="invoiceItemheader">
				<th>&nbsp;</th>
				<th>Product Description</th>
				<th>Price</th>
				<th>Qty</th>
			</tr>
		</thead>
		<tr class="detailrow">
			<td><img alt="Image Not Available"
				src="<c:url value='/static/images/products/${invoiceItem.image}' />"
				width="120" height="140"></td>
			<td class="name" width="600"><c:out
					value="${invoiceItem.description}" /></td>
			<td class="price"><c:out value="${invoiceItem.price}" /></td>
			<td ><sf:input type="number" path="amount" step="1"
					min="1" value="1" maxlength="2" size="2" /></td>
		</tr>
		<tr>
			<td><input type="image" name="submit" width="50" 
				height="50" src="<c:url value='/static/images/web/cart.ico' />" /><br>Add to Cart</td>
			<td><sf:hidden path="productName" /></td>
			<td><sf:hidden path="skuNum" /></td>
			<td><sf:input type="hidden" path="amtInStock" /></td>
			<td><sf:input type="hidden" path="weight" /></td>
			<td><sf:input type="hidden" path="invoiceKey.invoiceNum" /></td>
			<td><sf:input type="hidden" path="invoiceKey.skuNum" /></td>
			<td><sf:input type="hidden" path="description" /></td>
			<td><sf:input type="hidden" path="image" /></td>
			<td><sf:input type="hidden" path="price" />
		</tr>
	</table>
</sf:form>