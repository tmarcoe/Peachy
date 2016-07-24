<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="pageheading">
	<h2>Daily Specials</h2>
</div>
<script>
$(document).ready(function(){
	alert("This site is currently in beta and not accepting orders." + 
		  "\nHowever, you are welcome to register and browse the products." + 
		  "\nKeep in mind that the inventory might change.");
});
</script>
<div class="disclaimer">
<h4>&nbsp;All pricess are in Philippine pesos&nbsp;</h4>
<h6>&nbsp;Approx. 45PHP=$1USD&nbsp;</h6>
</div>
<c:if test="${inventory.size() > 0}">
	<table class="dailyspecials">

		<caption>To see a full list of products click the 'shop'
			link at the top of the page.</caption>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<th>Product Name</th>
			<th>Now On Sale For...</th>
		</tr>

		<c:forEach var="inventory" items="${inventory}">
			<c:set var="price" value="${inventory.discountPrice}" />
			<tr class="inventoryrow">
				<td><a
					href="${pageContext.request.contextPath}/productdetails?skuNum=${inventory.skuNum}">
						<img alt="Image Not Available"
						src='<c:url value="${fileLoc}${inventory.image}"></c:url>'
						width="50">
				</a></td>
				<td class="name" width="600">${inventory.productName}</td>
				<td class="price"><fmt:formatNumber type="currency"
						currencySymbol="P" value="${price}" /></td>
			</tr>
			<tr>
				<td colspan="3"><hr /></td>
			</tr>
		</c:forEach>
		<tr>
			<td><button class="standout" type="button" onclick="followLink('/pickcategory')">More Products</button>
		</tr>

	</table>
</c:if>
<c:if test="${inventory.size() == 0}">
	<h2>Sorry, no daily specials today.</h2>
	<h3>Click 'Shop' at the top menu to see a full list of products.</h3>
</c:if>
<script type="text/javascript">
function followLink(link) {
	window.location.href = "${pageContext.request.contextPath}" + link;
}
</script>