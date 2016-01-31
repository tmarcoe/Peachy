<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!--<img class="logo" alt="FlowerLogo.png"
	src="<c:url value='/static/images/FlowerLogo.jpg' />" />
  -->

<div class="nav-bar">
	<div class="container">
		<ul class="nav">
			<li><a href="home">Home</a></li>
			<sec:authorize  access="hasRole('ROLE_ADMIN')" >
				<li><a href="manageinventory">Manage Inventory</a></li>
				<li><a href="manageaccount">Manage Accounts</a></li>
				<li><a href="users">User Profiles</a></li>
			</sec:authorize>
		</ul>
	</div>
</div>