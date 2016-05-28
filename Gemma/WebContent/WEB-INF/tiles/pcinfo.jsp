<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/demo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.lettering-0.6.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/response.js"></script>

<div class="wrapper">
	<div class="checkout container">
		<sf:form id="payment-form" method="post"
			action="${pageContext.request.contextPath}/processcart"
			commandName="payment">
			<table class="pcinfoTable">
				<tr>
					<td>First Name</td>
					<td>Last Name</td>
					<td>Address 1</td>
					<td>Address 2</td>
				</tr>
				<tr>
					<td><sf:input type="text" path="firstName" /></td>
					<td><sf:input type="text" path="lastName" /></td>
					<td><sf:input type="text" path="address1" /></td>
					<td><sf:input type="text" path="address2" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td>Region</td>
					<td>Postal Code</td>
					<td>Country</td>
				</tr>
				<tr>
					<td><sf:input type="text" path="city" /></td>
					<td><sf:input type="text" path="region" /></td>
					<td><sf:input type="text" path="postal" /></td>
					<td><sf:input type="text" path="country" size="3" maxlength="3" /></td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
			</table>

			<section>
				<div class="bt-drop-in-wrapper">
					<div id="bt-dropin"></div>
				</div>
			</section>
			<button type="submit" >Submit</button>
			<input id="cToken" type="hidden" value="${clientToken}" />
		</sf:form>
	</div>
</div>
<script src="https://js.braintreegateway.com/v2/braintree.js"></script>

<script type="text/javascript">
	/*<![CDATA[*/
	var checkout = new Demo({
		formID : 'payment-form'
	});
	var input = document.getElementById("cToken");
	var client_token = input.value;

	braintree.setup(client_token, "dropin", {
		container : "bt-dropin"
	});
	/*]]>*/
</script>
