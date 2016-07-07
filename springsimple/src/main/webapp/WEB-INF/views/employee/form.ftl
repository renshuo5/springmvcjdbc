<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="${rc.contextPath}/employee">
	<table>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" value="${entity.name}"/></td>
			<td>disId:</td>
			<td><input type="text" name="disId" value="${entity.disId}"/></td>
			<td>contact</td>
			<td><input type="text" name="contactName" value="${entity.contactName}"/></td>
			<td>mobilePhone</td>
			<td>
				<input type="text" name="mobilePhone" value="${entity.mobilePhone}"/>
			</td>
			
			<td>phone</td>
			<td>
				<input type="text" name="phone" value="${entity.phone}"/>
			</td>
			<td>email</td>
			<td>
				<input type="text" name="email" value="${entity.email}"/>
			</td>
			<td>address</td>
			<td>
				<input type="text" name="address" value="${entity.address}"/>
			</td>
			
			<td>
			<button type="submit">提交</button>
			<button type="button">取消</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>