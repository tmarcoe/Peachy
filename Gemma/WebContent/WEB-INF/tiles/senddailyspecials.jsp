<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<center>
	<h3>Daily specials were sent to ${mailCount} users</h3>
</center>

<button type="button" onclick="followLink('/admin')">OK</button>

<script type="text/javascript">
	function followLink(link) {
		window.location.href = "${pageContext.request.contextPath}" + link;
	}
</script>