<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<h1>Please choose an output file.</h1>

<sf:form method="post" enctype="multipart/form-data" 
					   commandName="filePrint" 
					   action="${pageContext.request.contextPath}/processorders" >
	<table>
		<tr>
			<sf:input type="file" path="file" value="Select Output File"/>
			<td><div class="error"><sf:errors path="file" /></div></td>
		</tr>
		<tr>
			<td><input type="submit" value="Continue" /></td>
		</tr>
	</table>


</sf:form>