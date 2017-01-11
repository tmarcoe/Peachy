<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource var="ds" dataSource="jdbc/donzalma_peachy" />

	<table class="tableview" id="viewsurvey">
		<tr>
			<th>Date Submitted</th>
			<th>Submitted By</th>
			<th>Satisfaction</th>
			<th>Navigation</th>
			<th>Prices</th>
		</tr>
		<c:forEach var="item" items="${objectList.pageList}">
			<tr>
				<td><fmt:formatDate value="${item.filledOut}" /></td>
				<sql:query var="rs" dataSource="${ds}">SELECT firstname, lastname FROM UserProfile WHERE userID=${item.userID}</sql:query>
				<c:forEach var="row" items="${rs.rows}">
					<c:set var="firstName" value="${row.firstname}" />
					<c:set var="lastName" value="${row.lastname}" />
				</c:forEach>
				<td>${firstName} ${lastName}</td>
				<td>${item.question3 + 1}</td>
				<td>${item.question5 + 1}</td>
				<td>${item.question7 + 1}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><button type="button"
					onclick="followLink('/admin')">OK</button></td>
		</tr>
	</table>

<script type="text/javascript">
	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
</script>