<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<sf:form method="post" enctype="multipart/form-data"
	commandName="fileUpload" action="${pageContext.request.contextPath}/addinventory">
	<table>
		<tr>
			<td><sf:input path="file" type="file" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="Upload" /></td>
		</tr>
	</table>
</sf:form>
