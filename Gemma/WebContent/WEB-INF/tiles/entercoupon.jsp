<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>
<h2>Please Enter any coupons</h2>
<h4>If you don't have any coupons, click on Enter</h4>
<sf:form method="post"
	action="${pageContext.request.contextPath}/redeemcoupon">
	<table>
		<tr>
			<td>Enter The Coupon</td>
			<td><input name="couponNum" value="${couponNum}" />
		</tr>
		<tr>
			<td><button type="submit" >Enter</button></td>
			<td><div class="error" >${errorMsg}</div></td>
		</tr>
	</table>
</sf:form>