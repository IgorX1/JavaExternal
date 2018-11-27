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
    <br/><br/>
    <input type="submit" value="Log in"/>
</form>
</body>
</html>