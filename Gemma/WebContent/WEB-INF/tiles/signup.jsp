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
			<td><div class="error">
					<sf:errors path="maleFemale" />
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
					name="city" /></td>
			<td>State/Region:</td>
			<td><sf:input type="text" path="region" class="control"
					name="region" /></td>
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
					name="country" size="3" maxlength="3"/></td>
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
			<td>Currency:</td>
			<td><sf:select path="currency">
								<sf:option value="">--Select--</sf:option>
								<sf:option value="PHP">Philippines Peso</sf:option>
								<sf:option value="USD">United States Dollar</sf:option>
								<sf:option value="AUD">Australia Dollar</sf:option>
								<sf:option value="BGN">Bulgaria Lev</sf:option>
								<sf:option value="BRL">Brazil Real</sf:option>
								<sf:option value="CAD">Canada Dollar</sf:option>
								<sf:option value="CHF">Switzerland Franc</sf:option>
								<sf:option value="CNY">China Yuan Renminbi</sf:option>
								<sf:option value="CZK">Czech Republic Koruna</sf:option>
								<sf:option value="DKK">Denmark Krone</sf:option>
								<sf:option value="GBP">United Kingdom Pound</sf:option>
								<sf:option value="HKD">Hong Kong Dollar</sf:option>
								<sf:option value="HRK">Croatia Kuna</sf:option>
								<sf:option value="HUF">Hungary Forint</sf:option>
								<sf:option value="IDR">Indonesia Rupiah</sf:option>
								<sf:option value="ILS">Israel Shekel</sf:option>
								<sf:option value="INR">India Rupee</sf:option>
								<sf:option value="JPY">Japan Yen</sf:option>
								<sf:option value="KRW">South Korea Won</sf:option>
								<sf:option value="MXN">Mexico Peso</sf:option>
								<sf:option value="MYR">Malaysia Ringgit</sf:option>
								<sf:option value="NOK">Norway Krone</sf:option>
								<sf:option value="NZD">New Zealand Dollar</sf:option>
								<sf:option value="PLN">Poland Zloty</sf:option>
								<sf:option value="RON">Romania New Leu</sf:option>
								<sf:option value="RUB">Russia Ruble</sf:option>
								<sf:option value="SEK">Sweden Krona</sf:option>
								<sf:option value="SGD">Singapore Dollar</sf:option>
								<sf:option value="THB">Thailand Baht</sf:option>
								<sf:option value="TRY">Turkey Lira</sf:option>
								<sf:option value="ZAR">South Africa Rand</sf:option>
			</sf:select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="username" />
				</div></td>
			<td>&nbsp;</td>
			<td><div class="error">
					<sf:errors path="currency" />
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
