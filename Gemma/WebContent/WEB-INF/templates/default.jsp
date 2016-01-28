<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"
	type="text/javascript">
	
</script>

    
<tiles:insertAttribute name="includes"></tiles:insertAttribute>

</head>
<body>
	<div class="heading">
		<div class="container">
			<tiles:insertAttribute name="heading_title"></tiles:insertAttribute>
		</div>

	</div>
	<div class="toolbar">
		<tiles:insertAttribute name="toolbar"></tiles:insertAttribute>
	</div>
	<!-- 

	<div class="header">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
 -->
	<div class="content">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>

		<tiles:insertAttribute name="footer"></tiles:insertAttribute>


</body>
</html>