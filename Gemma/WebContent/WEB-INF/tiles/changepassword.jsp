<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/passwordchanged"
	commandName="userProfile">
	<div class="billing">
		<table>
			<caption>Enter the New Password for : ${userProfile.username}</caption>
			<tr>
				<td>Password:</td>
				<td><sf:input id="password" class="control" path="password"
						name="password" type="password" value="" /></td>
				<td><div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
				<td>Confirm Password:</td>
				<td><input id="confirmpass" class="control" name="confirmpass"
					type="password" /></td>
				<td><div id="matchpass"></div></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</div>
	<sf:hidden path="userID"/>
</sf:form>
