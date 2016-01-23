<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!--<img class="logo" alt="FlowerLogo.png"
	src="<c:url value='/static/web/images/FlowerLogo.jpg' />" />
  -->

<div class="nav-bar">
	<div class="container">
		<ul class="nav">
			<li><a href="home">Home</a></li>
			<li><a href="products">Shop</a></li>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="login">Sign In</a></li>
				<li><a href="signup">Sign Up</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a class="logout"
					href="<c:url value='/j_spring_security_logout'/>">Sign Out</a></li>
				<li><a href="mydonzalmart">My DonzalMart</a></li>
				<li><a href="changepassword">Change password</a></li>
			</sec:authorize>
			<li><a href="contactus">Contact Us</a></li>
			<li><a href="aboutus">About Us</a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="admin">Admin</a></li>
			</sec:authorize>
		</ul>
	</div>
</div>