<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/stockshelves"
	commandName="order">
	<table>
		<tr>
			<td>Product:</td>
			<td>${order.inventory.productName}</td>
			<td>&nbsp;</td>
			<td>In Stock:</td>
			<td>${order.inventory.amtInStock}</td>
		</tr>
		<tr>
			<td>Amount to Add:</td>
			<td><sf:input type="number" path="amount"/></td>
			<td>&nbsp;</td>
			<td>Order Price:</td>
			<td><sf:input type="number" path="price"/></td>
			<td>&nbsp;</td>
			<td>Order Tax</td>
			<td><sf:input type="number" path="tax"/></td>
		</tr>
		<tr><td><sf:input type="hidden" path="inventory.skuNum"/></td></tr>
		<tr><td><input type="submit" value="Restock" ></td></tr>
	</table>

</sf:form>