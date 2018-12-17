<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to Site</title>
</head>
<body>

<h1>Hello world</h1>
<ul>
    <li><a href="">Login</a></li>
    <li><a href="">Signup</a></li>
</ul>
<div class="formSignup">
    <form method="POST" action="add">
        <input type="hidden" name="id"/>
        <input type="text" name="name"/>
        <input type="text" name="phone"/>
        <input type="password" name="password"/>
        <input type="Submit" value="Register"/>
    </form>
</div>
</body>
</html>