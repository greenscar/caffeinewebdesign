<html>

<body text="red">

<%@ page isErrorPage="true" %>

<!-- Use the implicit exception obect, which holds a reference -->
<!-- to the thrown exception. -->

Error: <%= exception.getMessage() %> has been reported. 

</body>
</html>
