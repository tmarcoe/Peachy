<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<sf:form method="post" enctype="multipart/form-data"
	commandName="fileUpload" action="${pageContext.request.contextPath}/addinventory">
	<table>
		<tr>
			<td><sf:input type="file" path="file" value="Select Image" /></td>
			<td><div class="error"><sf:errors path="file"></sf:errors></div></td>
		</tr>
		<tr>
			<td><input type="submit" value="Upload File" /></td>
		</tr>
	</table>
</sf:form>
