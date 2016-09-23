<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form action="${pageContext.request.contextPath}/submitsurvey"
	method="post" commandName="survey">
	<table>
		<tr>
			<td>Did you find the item you wanted?</td>
			<td>What product were you looking for?</td>
		</tr>
		<tr>
			<td>Yes <sf:radiobutton path="question1" value="Y" /> No <sf:radiobutton
					path="question1" value="N" />
			</td>
			<td><sf:textarea path="question2" rows="2" cols="50" /></td>
		</tr>
		<tr>
			<td>On a scale of 1 to 10 (10 = very likely, 1 = not likely at
				all),
				<p>how likely would you recommend this website?
			</td>
			<td>What is the reason for this rating?</td>
		</tr>
		<tr>
			<td>1<sf:radiobutton path="question3" value="0" /> 
				2<sf:radiobutton path="question3" value="1" />
				3<sf:radiobutton path="question3" value="2" /> 
				4<sf:radiobutton path="question3" value="3" /> 
				5<sf:radiobutton path="question3" value="4" /> 
				6<sf:radiobutton path="question3" value="5" /> 
				7<sf:radiobutton path="question3" value="6" /> 
				8<sf:radiobutton path="question3" value="7" /> 
				9<sf:radiobutton path="question3" value="8" /> 
				10<sf:radiobutton path="question3" value="9" /></td>
			<td><sf:textarea path="question4" rows="2" cols="50" /></td>
		</tr>
		<tr>
			<td>On a scale of 1 to 10 (10 = very easy, 1 = very difficult),
				<p>how difficult was it to navigate the store?
			</td>
			<td>What is the reason for this rating?</td>
		</tr>
		<tr>
			<td>
				1<sf:radiobutton path="question5" value="0" /> 
				2<sf:radiobutton path="question5" value="1" /> 
				3<sf:radiobutton path="question5" value="2" /> 
				4<sf:radiobutton path="question5" value="3" /> 
				5<sf:radiobutton path="question5" value="4" /> 
				6<sf:radiobutton path="question5" value="5" /> 
				7<sf:radiobutton path="question5" value="6" /> 
				8<sf:radiobutton path="question5" value="7" /> 
				9<sf:radiobutton path="question5" value="8" /> 
				10<sf:radiobutton path="question5" value="9" />
			</td>
			<td><sf:textarea path="question6" rows="2" cols="50" /></td>
		</tr>
		<tr>
			<td>On a scale of 1 to 10 (10 = very good, 1 = very bad),
				<p>how did you like our prices?
			</td>
			<td>What is the reason for this rating?</td>
		</tr>
		<tr>
			<td>
				1<sf:radiobutton path="question7" value="0" /> 
				2<sf:radiobutton path="question7" value="1" /> 
				3<sf:radiobutton path="question7" value="2" /> 
				4<sf:radiobutton path="question7" value="3" /> 
				5<sf:radiobutton path="question7" value="4" /> 
				6<sf:radiobutton path="question7" value="5" /> 
				7<sf:radiobutton path="question7" value="6" /> 
				8<sf:radiobutton path="question7" value="7" /> 
				9<sf:radiobutton path="question7" value="8" /> 
				10<sf:radiobutton path="question7" value="9" />
			</td>
			<td><sf:textarea path="question8" rows="2" cols="50" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit Survey" /></td>
		</tr>
	</table>
	<sf:hidden path="userID" />
</sf:form>