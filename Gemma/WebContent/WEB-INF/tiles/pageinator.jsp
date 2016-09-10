<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="footer">

	<h1></h1>
	<c:if test="${objectList.getPageCount() > 1}">
		<div class="paging">
			<c:if test="${objectList.isFirstPage()==false}">
				<a href="${pageContext.request.contextPath}${pagelink}?page=prev"><img alt="[Prev]"
					src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
			</c:if>
			<c:forEach begin="1" end="${objectList.getPageCount()}" var="i">

				<c:choose>
					<c:when test="${(i-1)!= objectList.getPage()}">
						<a href="${pageContext.request.contextPath}${pagelink}?page=${i-1}"><span class="paging"><c:out
									value="${i}" /></span></a>
					</c:when>
					<c:otherwise>
						<span class="paging"><c:out value="${i}" /></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<%--For displaying Next link --%>
			<c:if test="${objectList.isLastPage()==false}">
				<a href="${pageContext.request.contextPath}${pagelink}?page=next"><img alt="[Next]"
					src="<c:url value='/static/images/web/button_next.gif'/>"></a>
			</c:if>
		</div>
	</c:if>

</div>