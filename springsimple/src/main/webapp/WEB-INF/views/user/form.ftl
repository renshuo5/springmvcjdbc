<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="${rc.contextPath}/user">
	<table>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="name" value="${entity.name}"/></td>
			<td>年龄</td>
			<td><input type="text" name="age" value="${entity.age}"/></td>
			<td>性别</td>
			<td>
				<select>
					<option value="">不限</option>
					<option value="0">男</option>
					<option value="1">女</option>
				</select>
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