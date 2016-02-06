<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<form method="post" >
	<div class="category" >
	<table class="catlist" >
		<c:forEach var="cat" items="${catList}">
			<tr>
				<td><a href="setcategory?cat=${cat}" >${cat}</a></td>
			</tr>	
		</c:forEach>
			<tr>
				<td><a href="setcategory?cat=" >All Categories</a></td>
			</tr>
	</table>
	</div>
</form>