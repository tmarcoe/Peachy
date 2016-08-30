<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/savecoupon"
	commandName="coupon">
	<table class="coupontable">
		<tr>
			<th>Coupon ID</th>
			<th>Coupon Name</th>
			<th>Rule Name</th>
			<th>Date Expired</th>
			<th>Maximum Usage</th>
		</tr>
		<tr>
			<td>CPN<sf:input path="couponID" type="text" /></td>
			<td><sf:input path="name" type="text" /></td>
			<td><sf:input path="ruleName" type="text" /></td>
			<td><sf:input path="expires" type="date" /></td>
			<td><sf:input path="useage" type="number"/></td>
		</tr>
		<tr>
			<td>Active&nbsp;<input type="hidden" value="on" name="_active" /> <sf:checkbox
					path="active" /></td>
			<td>Exclusive&nbsp;<input type="hidden" value="on" name="_active" /> <sf:checkbox
					path="exclusive" /></td>
		</tr>
	</table>
	<table>
		<tr>
			<th>Description</th>
		</tr>
		<tr>
			<td><sf:textarea path="description" rows="10" cols="50" /></td>
		</tr>
		<tr>
			<td><button type="submit" >Save</button></td>
			<td><button type="button" onclick="followLink('/listcoupons')">Cancel</button></td>
		</tr>
	</table>


</sf:form>
<script type="text/javascript">
function followLink(link) {
	window.location.href = "${pageContext.request.contextPath}" + link;
}

</script>