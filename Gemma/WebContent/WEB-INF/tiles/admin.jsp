<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>
<div class="menu-items">
	<fieldset>
		<legend>Admin Tasks</legend>

		<div class="container">
			<ul class="side-menu">
				<li><a href="home">Home</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="checkemail">Check Email</a></li>
					<li><a href="admininventory">Inventory Tasks</a></li>
					<li><a href="manageaccount">Manage Accounts</a></li>
					<li><a href="users">User Profiles</a></li>
					<li><a href="datepicker">View General Ledger</a></li>
					<li><a href="podnotify">Receive Payment From POD</a>
					<li><a href="returns-list">Process Returns</a></li>
					<li><a href="listcoupons">Manage Coupons</a></li>
					<li><a href="https://my.justhost.com/cgi/cpanel?ldomain=donzalmart.com">Control Panel</a></li>
				</sec:authorize>
			</ul>
		</div>
	</fieldset>
</div>

<div class="printorders">
	<c:choose>
		<c:when test="${headerList.pageList.size() > 0}">
			<form:form id="pgform" method="post" modelAttribute="headerList"
				action="${pageContext.request.contextPath}/processorders">
				<h2>Orders Ready For Processing</h2>
				<table class="viewheader">
					<thead>
						<tr>
							<th>Invoice #</th>
							<th>Date</th>
							<th>User #</th>
							<th>Total</th>
							<th>View Cart</th>
						</tr>
					</thead>
					<c:forEach var="item" items="${headerList.pageList}">
						<fmt:formatNumber type="number" pattern="00000000" value="${item.invoiceNum}" var="invNum"/>
						<fmt:formatNumber type="currency" currencySymbol="P"
							value="${item.total + item.totalTax + item.shippingCost + item.addedCharges}" var="total" />
						<fmt:formatDate value="${item.modified}" var="stdate" />
						<tr>
							<td>${invNum}</td>
							<td>${stdate}</td>
							<td>${item.userID}</td>
							<td>${total}</td>
							<td><a href="viewcart?invoiceNum=${item.invoiceNum}"><img
									alt="[View]"
									src="<c:url value='/static/images/web/edit.jpg' />"></a></td>
						</tr>

					</c:forEach>
				</table>
				<input type="submit" value="Process Orders" />
			</form:form>
			<div class="paging">
				<c:if test="${headerList.getPageCount()> 1}">
					<c:if test="${headerList.isFirstPage()==false}">
						<a href="headerpaging?page=prev"><img alt="[Prev]"
							src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
					</c:if>
					<c:forEach begin="1" end="${headerList.getPageCount()}" var="i">

						<c:choose>
							<c:when test="${(i-1)!= headerList.getPage()}">
								<a href="headerpaging?page=${i-1}"><span class="paging"><c:out
											value="${i}" /></span></a>
							</c:when>
							<c:otherwise>
								<span class="paging"><c:out value="${i}" /></span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%--For displaying Next link --%>
					<c:if test="${headerList.isLastPage()==false}">
						<a href="headerpaging?page=next"><img alt="[Next]"
							src="<c:url value='/static/images/web/button_next.gif'/>"></a>
					</c:if>
				</c:if>
			</div>
		</c:when>
		<c:otherwise>
			<h1>No orders to print</h1>
		</c:otherwise>
	</c:choose>
</div>
