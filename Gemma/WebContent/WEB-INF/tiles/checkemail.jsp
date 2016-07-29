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
	<c:when test="${objectList.pageList.size() > 0}">
		<table class="emailtable">
			<thead class="emailheader">
				<tr>
					<th>From</th>
					<th>Subject</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${objectList.getPageList()}">
				<tr>
					<td>${item.getFrom()}</td>
					<td>${item.getSubject()}</td>
				</tr>
			</c:forEach>
			<tfoot class="tablefooter">
				<tr>
					<td colspan="2"><button type="button" onclick="followLink('/admin');">Back</button>
				</tr>
			</tfoot>
		</table>
	</c:when>
	<c:otherwise>
		<h1>No Email</h1>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
</script>