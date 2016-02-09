<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<center><h3>From All of us at DonzalMart, Salamat</h3></center>

<fmt:formatNumber type='currency' currencySymbol='P' value='${invoiceHeader.total + invoiceHeader.totalTax}' var='total'/>
<h4>Your Order is available At:</h4>
<h4>02319 Nueva St. Brgy. Taft</h4>
<h4>Surigao City, Surigao Del Norte</h4>
<br>
<br>
<h6>Please have ${total} ready when you pick up your order.</h6>
