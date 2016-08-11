<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<c:forEach var="user" items="${objectList.pageList}" varStatus="i"
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
						src="<c:url value='/static/images/web/delete.jpg' />"></a></td>
				<td>&nbsp;</td>
				<td><a href="#" onclick="getDetail(${i.index});"
					class="inventorydetail"><img alt="[Show Detail]"
						src="<c:url value='/static/images/web/edit.jpg' />"></a></td>
			</tr>
		</c:forEach>
		<tfoot>
			<tr>
				<td colspan="10"><button type="button" onclick="followLink('/admin');">Back</button></td>
			</tr>
		</tfoot>
	</table>
</form:form>
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
	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
</script>
