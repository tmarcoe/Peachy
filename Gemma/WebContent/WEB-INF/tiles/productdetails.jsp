<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/orderproduct"
	commandName="invoiceItem">
	<table class="productdetails">
		<tr class="invoiceItemheader">
			<th>&nbsp;</th>
			<th>Product Description</th>
			<th>Price</th>
			<th>Qty</th>
		</tr>
		<tr>
			<td><img alt="Image Not Available"
				src="<c:url value='${fileLoc}${invoiceItem.image}' />" width="150"></td>
			<td>${invoiceItem.description}</td>
			<td><fmt:formatNumber type='currency' currencySymbol='${currencySymbol}'
					value='${invoiceItem.price * rate}' /></td>
			<td><sf:input type="number" path="amount" step="1" min="1"
					value="1" maxlength="3" size="2" /></td>
			<td><div class="error"><sf:errors path="amount" /></div>
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