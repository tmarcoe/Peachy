<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sf:form method="post" commandName="item"
	action="${pageContext.request.contextPath}/saveitem">
	<table class="invoicedetail">
		<caption>
			<b>Invoice #
			<fmt:formatNumber type="number" value="${item.invoiceKey.invoiceNum}"
				minIntegerDigits="6" groupingUsed="false" /></b>
		</caption>
		<tr>
			<td>${item.productName}</td>
			<td>${item.description}</td>
			<td><fmt:formatNumber type="currency" currencySymbol="P" value="${item.price}"/> each</td>
			<td><sf:input type="number" path="amount" min="1" maxlength="3" size="1"/></td>
			<td><div class="error"><sf:errors path="amount"/></div></td>
		</tr>

		<tr>
			<td><sf:input type="hidden" path="invoiceKey.invoiceNum" /></td>
			<td><sf:input type="hidden" path="invoiceKey.skuNum" /></td>
			<td><sf:input type="hidden" path="productName" /></td>
			<td><sf:input type="hidden" path="description" /></td>
			<td><sf:input type="hidden" path="image" /></td>
			<td><sf:input type="hidden" path="price" /></td>
			<td><sf:input type="hidden" path="amtInStock" /></td>
			<td><sf:input type="hidden" path="weight" /></td>
		</tr>

		<tr>
			<td><input type="submit" value="Save Item"/>
		</tr>
	</table>
</sf:form>