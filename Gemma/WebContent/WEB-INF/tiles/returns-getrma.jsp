<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="getRma">
	<thead>
		<tr>
			<th>RMA #</th>
			<th>Invoice #</th>
			<th>SKU #</th>
			<th>Purchased ON</th>
		</tr>
	</thead>
		<tr>
			<td><fmt:formatNumber type="number" pattern="00000000" value="${returns.rmaId}"/></td>
			<td><fmt:formatNumber type="number" pattern="00000000" value="${returns.invoiceNum}"/></td>
			<td>${returns.skuNum}</td>
			<td><fmt:formatDate value="${returns.datePurchased}"/></td>
		</tr>
		<tr>
			<td><input type="button" onClick="window.print()" value="Print" /></td>
		</tr>
</table>