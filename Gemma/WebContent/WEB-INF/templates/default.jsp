<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<sql:setDataSource var="ds" dataSource="jdbc/donzalma_peachy" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery-2.1.4.min.js"></script>

<script src="/static/script/money.min.js" type="text/javascript">
	
</script>
<sec:authentication var="principal" property="principal" />
<tiles:insertAttribute name="includes"></tiles:insertAttribute>

</head>

<body>
	<c:choose>
		<c:when test="${principal  != 'anonymousUser'}">

			<sql:query var="rs" dataSource="${ds}">
				SELECT firstname, lastname, currency FROM UserProfile WHERE username='${principal.username}'
			</sql:query>

			<c:forEach var="row" items="${rs.rows}">
				<c:set var="firstName" value="${row.firstname}" />
				<c:set var="lastName" value="${row.lastname}" />
				<c:set var="currency" value="${row.currency}" />
			</c:forEach>
			<input id="currencyHolder" type="hidden" value="${currency}" />
			<table class="rightside">
				<tr>
					<td>
						<h6 class="label">Currency:</h6>
					</td>
					<td>
						<div class="userinfo">
							<select id="getCurrency">
								<option value="PHP">Philippines Peso</option>
								<option value="USD">United States Dollar</option>
								<option value="AUD">Australia Dollar</option>
								<option value="BGN">Bulgaria Lev</option>
								<option value="BRL">Brazil Real</option>
								<option value="CAD">Canada Dollar</option>
								<option value="CHF">Switzerland Franc</option>
								<option value="CNY">China Yuan Renminbi</option>
								<option value="CZK">Czech Republic Koruna</option>
								<option value="DKK">Denmark Krone</option>
								<option value="GBP">United Kingdom Pound</option>
								<option value="HKD">Hong Kong Dollar</option>
								<option value="HRK">Croatia Kuna</option>
								<option value="HUF">Hungary Forint</option>
								<option value="IDR">Indonesia Rupiah</option>
								<option value="ILS">Israel Shekel</option>
								<option value="INR">India Rupee</option>
								<option value="JPY">Japan Yen</option>
								<option value="KRW">South Korea Won</option>
								<option value="MXN">Mexico Peso</option>
								<option value="MYR">Malaysia Ringgit</option>
								<option value="NOK">Norway Krone</option>
								<option value="NZD">New Zealand Dollar</option>
								<option value="PLN">Poland Zloty</option>
								<option value="RON">Romania New Leu</option>
								<option value="RUB">Russia Ruble</option>
								<option value="SEK">Sweden Krona</option>
								<option value="SGD">Singapore Dollar</option>
								<option value="THB">Thailand Baht</option>
								<option value="TRY">Turkey Lira</option>
								<option value="ZAR">South Africa Rand</option>
							</select>
						</div>
					</td>
				</tr>
			</table>
			<table class="leftside">
				<tr>
					<td>Welcome back</td>
					<td><h6 class="label">${firstName}</h6></td>
					<td><h6 class="label">${lastName}</h6></td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<table class="rightside">
				<tr>

					<td>
						<h6 class="label">Currency:</h6>
					</td>
					<td>
						<h6 class="label">Philippines Peso &nbsp;&nbsp;</h6>
					</td>
				</tr>
			</table>
			<table class="leftside">
				<tr>
					<td><h6 class="label">${principal}</h6></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
		$("#getCurrency").val($('#currencyHolder').val());
		 document.getElementById("getCurrency").disabled=true;
	</script>
	<div class="heading">
		<div class="container">
			<tiles:insertAttribute name="heading_title"></tiles:insertAttribute>
		</div>
	</div>
	<div class="toolbar">
		<tiles:insertAttribute name="toolbar"></tiles:insertAttribute>
	</div>
	<div class="content">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>

	<tiles:insertAttribute name="footer" />

</body>
</html>