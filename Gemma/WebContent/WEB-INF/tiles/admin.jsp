<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<form:form id="pgform" method="post"  modelAttribute="headerList" action="${pageContext.request.contextPath}/filepicker" >
	<h2>Orders Ready For Processing</h2>
	<table class="viewheader">
		<thead>
			<tr>
				<th>Invoice #</th>
				<th>Date</th>
				<th>User #</th>
				<th>Total</th>
			</tr>
		</thead>
			<c:forEach var="item" items="${headerList.pageList}" >
				<fmt:formatNumber type="currency" currencySymbol="P" value="${item.total}" var="total" />
				<fmt:formatDate value="${item.modified}" var="stdate"/>
				<tr>
					<td>${item.invoiceNum}</td>
					<td>${stdate}</td>
					<td>${userID}</td>
					<td>${total}</td>
					<td><a href="viewcart?invoiceNum=${item.invoiceNum}">[View]</a></td>
				</tr>
			
			</c:forEach>
	</table>
	<input type="submit" value="Process Orders" /></td>
</form:form>
<div class="paging">
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
</div>
