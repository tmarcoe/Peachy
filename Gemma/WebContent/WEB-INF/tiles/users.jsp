<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<h2>Users</h2>

<form:form id="pgform" method="post" modelAttribute="pparam"
	action="${pageLink}">
	<table class="userstable" id="listusers">

		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>userID</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
				<th>Delete</th>
				<th>&nbsp;</th>
				<th>Edit</th>
			</tr>
		</thead>
		<c:forEach var="user" items="${userList.pageList}" varStatus="i"
			begin="0">
			<tr>
				<td>${user.firstname}</td>
				<td>${user.lastname}</td>
				<td>${user.username}</td>
				<td>${user.userID}</td>
				<td><input type="hidden" value="${user.username}" /></td>
				<td><input type="hidden" value="${user.firstname}" /></td>
				<td><input type="hidden" value="${user.lastname}" /></td>
				<td><a href="#" onclick="rowRemoved(${i.index});"
					class="removeAccount"><img alt="[Remove]"
						src="<c:url value='/static/images/web/button.gif' />"></a></td>
				<td>&nbsp;</td>
				<td><a href="#" onclick="getDetail(${i.index});"
					class="inventorydetail"><img alt="[Show Detail]"
						src="<c:url value='/static/images/web/button.gif' />"></a></td>
			</tr>
		</c:forEach>

	</table>


</form:form>
<div class="paging">
	<c:if test="${userList.getPageCount()>1}">
		<c:if test="${userList.isFirstPage()==false}">
			<a href="paging?page=prev"><img alt="[Prev]"
				src="<c:url value='/static/images/web/button_prev.gif'/>"></a>
		</c:if>
		<c:forEach begin="1" end="${userList.getPageCount()}" var="i">

			<c:choose>
				<c:when test="${(i-1)!=userList.getPage()}">
					<a href="paging?page=${i-1}"><span class="paging"><c:out
								value="${i}" /></span></a>
				</c:when>
				<c:otherwise>
					<span class="paging"><c:out value="${i}" /></span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%--For displaying Next link --%>
		<c:if test="${userList.isLastPage()==false}">
			<a href="paging?page=next"><img alt="[Next]"
				src="<c:url value='/static/images/web/button_next.gif'/>"></a>
		</c:if>
	</c:if>
</div>
<script type="text/javascript">
	function rowRemoved(row) {

		var inputs = document.getElementById('listusers').getElementsByTagName(
				'input');
		var column = (row * 3);
		var key = inputs[column].value;
		if (confirm("Are you sure you want to remove "
				+ inputs[column + 1].value + " " + inputs[column + 2].value + " from User Profiles?") == true) {
			window.location.href = "${pageContext.request.contextPath}/deleteuser?deleteKey="
					+ key;
		}
	}

	function getDetail(row) {

		var inputs = document.getElementById('listusers')
				.getElementsByTagName('input');
		var column = (row * 3);
		var key = inputs[column].value;
		window.location.href = "${pageContext.request.contextPath}/userdetails?detailKey="
				+ key;
	}
</script>
