<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
用户列表
<a href="${rc.contextPath}/distri/form">新增分销商</a><br>
<#list list as li>
编码:${li.id},姓名:${li.name},年龄:${li.contactName}<a href="${rc.contextPath}/distri/form?id=${li.id}">修改</a><br>

</#list>
</body>
</html>