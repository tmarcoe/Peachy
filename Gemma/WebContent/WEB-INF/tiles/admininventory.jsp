<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld"%>

<div class="menu-items">
	<fieldset>
		<legend>Inventory Tasks</legend>

		<div class="container">
			<ul class="side-menu">
				<li><a href="admin">Admin</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="manageinventory">Manage Inventory</a></li>
					<li><a href="orderinventory">Order Inventory</a></li>
					<li><a href="singleitem">Restock single Item</a>
				</sec:authorize>
			</ul>
		</div>
	</fieldset>
</div>