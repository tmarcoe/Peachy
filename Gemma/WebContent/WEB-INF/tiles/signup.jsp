<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/createprofile"
	commandName="userProfile">
	<div class="billing">
		<table>
			<thead>
				<tr>
					<th colspan="6">User Name</th>
				</tr>
			</thead>
			<tr>
				<td>First Name:</td>
				<td><sf:input type="text" class="control" path="firstname"
						name="firstname" /></td>
				<td>
					<div class="error">
						<sf:errors path="firstname"></sf:errors>
					</div>
				</td>
				<td>Last Name:</td>
				<td><sf:input type="text" class="control" path="lastname"
						name="lastname" /></td>
					<td><div class="error">
						<sf:errors path="lastname"></sf:errors>
					</div></td>
				<td>Gender</td>
				<td><sf:select path="maleFemale">
						<option value="">--Select One--</option>
						<option value="M">Male</option>
						<option value="F">Female</option>
				</sf:select></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<thead>
				<tr>
					<th colspan="6">Address</th>
				</tr>
			</thead>
			<tr>
				<td>Address 1:</td>
				<td><sf:input type="text" path="address1" class="control"
						name="address1" /></td>
				<td><div class="error">
						<sf:errors path="address1"></sf:errors>
					</div></td>
				<td>Address 2:</td>
				<td><sf:input type="text" path="address2" class="control"
						name="address2" /></td>
				<td><div class="error">
						<sf:errors path="address2"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td>City :</td>
				<td><sf:input type="text" path="city" class="control"
						name="city" /></td>
				<td><div class="error">
						<sf:errors path="city"></sf:errors>
					</div></td>
				<td>State/Region:</td>
				<td><sf:input type="text" path="region" class="control"
						name="region" /></td>
				<td><div class="error">
						<sf:errors path="region"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td>Postal Code:</td>
				<td><sf:input type="text" path="postalCode" class="control"
						name="postalCode" /></td>
				<td><div class="error">
						<sf:errors path="postalCode"></sf:errors>
					</div></td>
				<td>Country:</td>
				<td><sf:input type="text" path="country" class="control"
						name="country" /></td>
				<td><div class="error">
						<sf:errors path="country"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<thead>
				<tr>
					<th colspan="6">Contact Information</th>
				</tr>
			</thead>

			<tr>
				<td>Home Phone:</td>
				<td><sf:input type="tel" path="homePhone" class="control"
						name="homePhone" /></td>
				<td><div class="error">
						<sf:errors path="homePhone"></sf:errors>
					</div></td>
				<td>Cell Phone:</td>
				<td><sf:input type="tel" path="cellPhone" class="control"
						name="cellPhone" /></td>
				<td><div class="error">
						<sf:errors path="cellPhone"></sf:errors>
					</div></td>
				</tr>
				<tr>
				<td>E-Mail:</td>
				<td><sf:input type="username" path="username" class="control"
						name="username" /></td>
				<td><div class="error">
						<sf:errors path="username"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><sf:input id="password" class="control" path="password"
						name="password" type="password" /></td>
					<td><div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
				<td>Confirm Password:</td>
				<td><input id="confirmpass" class="control" name="confirmpass"
					type="password" /></td>
					<td><div id="matchpass"></div></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
			</tr>
			<tr>
				<td>Monthly News Letter:</td>
				<td><input type="hidden" value="on" name="_active" /> <sf:checkbox
						class="control" path="monthlyMailing" /></td>
			</tr>
			<tr>
				<td>Daily Specials:</td>
				<td><input type="hidden" value="on" name="_active" /> <sf:checkbox
						path="dailySpecials" class="control" /></td>
			</tr>


			<tr>
				<td>Shipping Info:</td>
				<td><sf:textarea rows="5" cols="50" path="shippingInfo"
						class="control" name="shippingInfo" /></td>
				<td><div class="error">
						<sf:errors path="shippingInfo"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>

		</table>
	</div>
</sf:form>
