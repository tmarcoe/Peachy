<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/createprofile"
	commandName="userProfile">
	<table class="signup">
		<thead>
			<tr>
				<th colspan="4">User Information</th>
			</tr>
		</thead>
		<tr>
			<td>First Name:</td>
			<td><sf:input type="text" class="control" path="firstname"
					name="firstname" /></td>
			<td>Last Name:</td>
			<td><sf:input type="text" class="control" path="lastname"
					name="lastname" /></td>
			<td><sf:radiobutton path="maleFemale" value="M" />Male<sf:radiobutton
					path="maleFemale" value="F" />Female</td>

		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<div class="error">
					<sf:errors path="firstname" />
				</div>
			</td>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="lastname" />
				</div></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><sf:input type="text" path="address1" class="control"
					name="address1" /></td>
			<td>Address 2:</td>
			<td><sf:input type="text" path="address2" class="control"
					name="address2" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="address1" />
				</div></td>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="address2" />
				</div></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><sf:input type="text" path="city" class="control"
					name="city" value="Surigao City" readonly="true" /></td>
			<td>State/Region:</td>
			<td><sf:input type="text" path="region" class="control"
					name="region" value="Surigao Del Norte" readonly="true" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="city" />
				</div></td>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="region" />
				</div></td>
		</tr>
		<tr>
			<td>Postal Code:</td>
			<td><sf:input type="text" path="postalCode" class="control"
					name="postalCode" /></td>
			<td>Country:</td>
			<td><sf:input type="text" path="country" class="control"
					name="country" value="Philippines" readonly="true" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="postalCode" />
				</div></td>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="country" />
				</div></td>
		</tr>
		<tr>
			<td>Home Phone:</td>
			<td><sf:input type="tel" path="homePhone" class="control"
					name="homePhone" /></td>
			<td>Cell Phone:</td>
			<td><sf:input type="tel" path="cellPhone" class="control"
					name="cellPhone" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="homePhone" />
				</div></td>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="cellPhone" />
				</div></td>
		</tr>
		<tr>
			<td>E-Mail:</td>
			<td><sf:input type="username" path="username" class="control"
					name="username" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="username" />
				</div></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><sf:input id="password" class="control" path="password"
					name="password" type="password" /></td>
			<td>Confirm:</td>
			<td><input id="confirmpass" class="control" name="confirmpass"
				type="password" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="password" />
				</div></td>
			<td>&nbsp;</td>
			<td><div id="matchpass"></div></td>
		</tr>
		<tr>
			<td>Monthly News Letter:</td>
			<td><input type="hidden" value="on" name="_active" /> <sf:checkbox
					class="control" path="monthlyMailing" /></td>
			<td>Daily Specials:</td>
			<td><input type="hidden" value="on" name="_active" /> <sf:checkbox
					path="dailySpecials" class="control" /></td>
		</tr>


		<tr>
			<td>Shipping Info:</td>
			<td><sf:textarea rows="5" cols="50" path="shippingInfo"
					class="control" name="shippingInfo" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="shippingInfo" />
				</div></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit" /></td>
		</tr>

	</table>
</sf:form>
