<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<center>
	<h3>From All of us at Peachy's Coffee, Salamat</h3>
</center>

<fmt:formatNumber type='currency' currencySymbol='${currencySymbol}'
	value='${(invoiceHeader.total + invoiceHeader.totalTax + invoiceHeader.addedCharges + invoiceHeader.shippingCost) * rate}'
	var='total' />
<h1>This Site is being tested and we are not taking orders at this time.</h1>
<h6>Your Total order is ${total}. This includes tax.</h6>
<h6>We appreciate your business. Your order is on it's way.</h6>
