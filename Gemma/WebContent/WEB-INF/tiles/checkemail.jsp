<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<div class="pageheading">
	<h2>Client Email</h2>
</div>
<c:choose>
	<c:when test="${msgs.pageList.size() > 0}">
		<table class="emailtable">
			<thead class="emailheader">
				<tr>
					<th>From</th>
					<th>Subject</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${msgs.getPageList()}">
				<tr>
					<td>${item.getFrom()}</td>
					<td>${item.getSubject()}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="paging">
			<c:if test="${msgs.getPageCount()> 1}">
				<c:if test="${msgs.isFirstPage()==false}">
					<a href="emailpaging?page=prev"><img alt="[Prev]"
						src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
				</c:if>
				<c:forEach begin="1" end="${msgs.getPageCount()}" var="i">

					<c:choose>
						<c:when test="${(i-1)!= msgs.getPage()}">
							<a href="emailpaging?page=${i-1}"><span class="paging"><c:out
										value="${i}" /></span></a>
						</c:when>
						<c:otherwise>
							<span class="paging"><c:out value="${i}" /></span>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<%--For displaying Next link --%>
				<c:if test="${msgs.isLastPage()==false}">
					<a href="emailpaging?page=next"><img alt="[Next]"
						src="<c:url value='/static/images/web/button_next.gif'/>"></a>
				</c:if>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<h1>No Email</h1>
	</c:otherwise>
</c:choose>