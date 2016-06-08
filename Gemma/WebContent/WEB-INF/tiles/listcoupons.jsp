<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<table class="viewcoupons">
	<tr>
		<th>Coupon Name</th>
		<th>Coupon Description</th>
		<th>Active?</th>
		<th>Exclusive?</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="item" items="${couponList.pageList}">
		<tr>
			<td>${item.name}</td>
			<td>${item.description}</td>
			<td>${item.active}</td>
			<td>${item.exclusive}</td>
			<td><button type="button"
					onclick="followLink('editcoupon?key=${item.couponID}')">
					<img alt="[View]"
						src="<c:url value='/static/images/web/edit.jpg' />">
				</button></td>
			<td><button type="button"
					onclick="rowRemoved('${item.couponID}')">
					<img alt="[Delete]"
						src="<c:url value='/static/images/web/delete.jpg' />">
				</button></td>
		</tr>
	</c:forEach>
	<tfoot class="viewcouponfoot">
	<tr>
		<td><button type="button" onclick="followLink('/createcoupon')">New Coupon</button></td>
		<td colspan="5"><button type="button" onclick="followLink('/admin')">Back</button></td>
		
	</tr>
	</tfoot>
</table>

<div class="paging">
	<c:if test="${couponList.getPageCount()> 1}">
		<c:if test="${couponList.isFirstPage()==false}">
			<a href="couponpaging?page=prev"><img alt="[Prev]"
				src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
		</c:if>
		<c:forEach begin="1" end="${couponList.getPageCount()}" var="i">

			<c:choose>
				<c:when test="${(i-1)!= couponList.getPage()}">
					<a href="couponpaging?page=${i-1}"><span class="paging"><c:out
								value="${i}" /></span></a>
				</c:when>
				<c:otherwise>
					<span class="paging"><c:out value="${i}" /></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--For displaying Next link --%>
		<c:if test="${couponList.isLastPage()==false}">
			<a href="couponpaging?page=next"><img alt="[Next]"
				src="<c:url value='/static/images/web/button_next.gif'/>"></a>
		</c:if>
	</c:if>
</div>

<script type="text/javascript">
	function rowRemoved(key) {

		if (confirm("Are you sure you want to remove '" + key
				+ "' from the coupon database?") == true) {
			window.location.href = "${pageContext.request.contextPath}/deletecoupon?key="
					+ key;
		}
	}
	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
</script>