<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/productadded"
	commandName="inventory" >
	<table  class="inventorytable">
		<thead>
			<tr>
				<th><h1>Products</h1></th>
			</tr>
		</thead>
		<tr>
			<td>SKU Number:</td>
			<td><sf:input path="skuNum" name="skuNum" class="control" type="text" /></td>
			<td>Product Name:</td>
			<td><sf:input path="productName" name="productName" class="control" type="text" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="skuNum"></sf:errors></div></td>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="productName"></sf:errors></div></td>
		</tr>
		<tr>
			<td>Category:</td>
			<td><sf:input path="category" name="category" class="control" type="text" /></td>
			<td>Sub Category:</td>
			<td><sf:input path="subCategory" name="subCategory" class="control" type="text" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="category"></sf:errors></div></td>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="subCategory"></sf:errors></div></td>
		</tr>
		<tr>
			<td>Amount In Stock:</td>
			<td><sf:input path="amtInStock" name="amtInStock" class="control" type="number" /></td>
			<td>Amount Committed:</td>
			<td><sf:input path="amtCommitted" name="amtCommitted" class="control" type="text" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="amtInStock"></sf:errors></div></td>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="amtCommitted"></sf:errors></div></td>
		</tr>
		<tr>
			<td>Sale Price:</td>
			<td><sf:input path="salePrice" name="salePrice" class="control" type="number" step=".01"/></td>
			<td>Discount Price:</td>
			<td><sf:input path="discountPrice" name="discountPrice" class="control" type="number" step=".01"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="salePrice"></sf:errors></div></td>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="discountPrice"></sf:errors></div></td>
		</tr>
		<tr>
			<td>Tax Amount:</td>
			<td><sf:input path="salePrice" name="salePrice" class="control" type="number" step=".01"/></td>
			<td>Product On Sale:</td>
			<td><input type="hidden" value="on" name="_active"/>
			<sf:checkbox name="onSale" class="control" path="onSale" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td><td><div class="error"><sf:errors path="salePrice"></sf:errors></div></td>
		</tr>
		<tr>
		<td>Image File:</td>
		<td><sf:input type="text" name="image" path="image" readonly="true" /></td>
		</tr>
		
	</table>
	<h3>Product Description</h3>
	<sf:textarea path="description" rows="10" cols="100"></sf:textarea>
	<div class="error"><sf:errors path="description"></sf:errors></div>
	<input type="submit" value="Add Product" />
</sf:form>