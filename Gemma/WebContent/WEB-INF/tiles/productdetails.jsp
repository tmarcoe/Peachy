<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h3>File = ${fileLoc}${inventory.image}</h3>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/orderproduct"
	commandName="invoiceItem">
	<table class="productdetails">
		<thead>
			<tr class="invoiceItemheader">
				<th>&nbsp;</th>
				<th>Product Description</th>
				<th>Price</th>
				<th>Qty</th>
			</tr>
		</thead>
		<tr class="detailrow">
			<td><img alt="Image Not Available" src="<c:url value='${fileLoc}${invoiceItem.image}' />" width="70" ></td>
			<td class="price"><fmt:formatNumber type='currency'
					currencySymbol='P' value='${invoiceItem.price}' /></td>
			<td><sf:input type="number" path="amount" step="1" min="1"
					value="1" maxlength="3" size="2" /></td>
		</tr>
		<tr>
			<td><input type="image" name="submit" width="50" height="50"
				src="<c:url value='/static/images/web/Website-Shopping-Cart.jpg' />" /><br>Add
				to Cart</td>
			<td><sf:hidden path="productName" /></td>
			<td><sf:hidden path="skuNum" /></td>
			<td><sf:input type="hidden" path="amtInStock" /></td>
			<td><sf:input type="hidden" path="weight" /></td>
			<td><sf:input type="hidden" path="invoiceKey.invoiceNum" /></td>
			<td><sf:input type="hidden" path="invoiceKey.skuNum" /></td>
			<td><sf:input type="hidden" path="description" /></td>
			<td><sf:input type="hidden" path="image" /></td>
			<td><sf:input type="hidden" path="price" /></td>
			<td><sf:input type="hidden" path="tax" />
			<td>
		</tr>
	</table>
</sf:form>