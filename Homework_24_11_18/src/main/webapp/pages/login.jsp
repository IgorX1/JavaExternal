<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h3>Authorize, please:</h3>
<form action="login" method="post">
    <input type="hidden" name="command" value="login" />
    <input type="text" autocomplete="off" name="login" id="login" required/>
    <label for="login">Login</label>
    <br/>
    <input type="password" name="password" id="password" required/>
    <label for="password">Password</label>
    <br/>
    <label style="display: inline; width: auto;">
        <input type="radio" name="lang" value="en" required checked>English
    </label>
    <label>
        <input type="radio" name="lang" value="ukr" required>Українська
    </label>    <br/>
    <input type="submit" value="Log in"/>
</form>
</body>
</html>