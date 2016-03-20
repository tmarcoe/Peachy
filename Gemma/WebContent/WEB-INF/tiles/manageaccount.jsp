<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<sf:form action="${pageContext.request.contextPath}/accountdetail" modelAttribute="chartOfAccountsContainer1" method="get" id="accountsListForm" name="manageAccounts">
 	<table  class="accounttable" id = "listaccounts" >
    	<thead>
        	<tr>
            	<th>Number</th>
             	<th>Name</th>
             	<th>&nbsp;</th>
             	<th>&nbsp;</th>
             	<th>Delete</th>
             	<th>&nbsp;</th>
             	<th>Edit</th>
			</tr>
		</thead>

		<tbody >

			<c:forEach items="${accounts}" var="item" varStatus="i" begin="0" > 
				<tr class="account" >    
					<td>${item.accountNum}</td>
					<td>${item.accountName}</td>
					<td><input type="hidden" value="${item.accountNum}" /></td>
					<td><input type="hidden" value="${item.accountName}" /></td>
					<td><a href="#" onclick="rowRemoved(${i.index});" class="removeAccount"><img alt="[Remove]" src="<c:url value='/static/images/web/button.gif' />"></a></td>
					<td>&nbsp;</td>
					<td><a href="#" onclick="getDetail(${i.index});" class="inventorydetail"><img alt="[Show Detail]" src="<c:url value='/static/images/web/button.gif' />"></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
            
	<a href="${pageContext.request.contextPath}/addaccount">Add Account</a>&nbsp;&nbsp;
	</sf:form>

<script type="text/javascript">
	function rowRemoved(row) {
  		var inputs = document.getElementById('listaccounts').getElementsByTagName('input');
		var column = (row * 2);
		var key = inputs[column].value;
		    if (confirm("Are you sure you want to remove Account #" + inputs[column].value + " from Chart of Accounts?") == true) {
		   		window.location.href = "${pageContext.request.contextPath}/deleteaccount?deleteKey=" + key;		    
		   	} 
	}
	function getDetail(row) {
		
  		var inputs = document.getElementById('listaccounts').getElementsByTagName('input');
		var column = (row * 2);
		var key = inputs[column].value;
		window.location.href = "${pageContext.request.contextPath}/accountdetail?detailKey=" + key;	
	}
 
 
</script>
