<%@include file="_tagIncludes.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="edit" method="post" commandName="entity">
				<table width="500" style="border-collapse: collapse;" border="0" bordercolor="#006699" cellspacing="2" cellpadding="2">					
					<tr>
						<td width="100" align="right">Id</td>
						<td width="150">
						<form:input path="ID" readonly="true"/></td>
						<td align="left">
						<form:errors path="ID" cssStyle="color:red"></form:errors>  </td>
					</tr>
					<tr>
						<td width="100" align="right">Description</td>
						<td>
						<form:input path="Description"/></td>
						<td align="left">
						<form:errors path="Description" cssStyle="color:red"></form:errors> 
						</td>
					</tr>
					<tr>
						<td width="100" align="right">State Province</td>
						<td>
						<form:input path="StateProvince.Code"/></td>
						<td align="left">
						<form:errors path="StateProvince.Code" cssStyle="color:red"></form:errors> 
						</td>
					</tr>
				
					<tr valign="bottom">
						<td colspan="3" align="center">
						<input type="button"  value="Delete" onclick="javascript:deleteContact('deleteContact.do?id=${entity.ID}');">
						&nbsp;&nbsp;
						<input type="submit" name="" value="Save">						
						&nbsp;&nbsp;
						<input type="button"  value="Back" onclick="javascript:go('users');">		
						<!--  <a href="${fn:replace(pageContext.request.requestURL,pageContext.request.requestURI,pageContext.request.contextPath)}">List</a>				
						--></td>
					</tr>
					
				</table>				
		</form:form>

</body>
</html>