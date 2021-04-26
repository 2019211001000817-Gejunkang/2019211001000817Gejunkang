<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp"%>
This is MyJsp page <br>
<a href="index.jsp">go to ecjtu</a> <!--method is GET-->
<form method="post"><!--what is method when we use form?--><!--its GET,Why?  default is GET-->
    Name :<input type="text" name="name"><br/>
    Id :<input type="text" name="id"><br/>
    <input type="submit" value="Send data to server"/>
</form>
<%@include file="footer.jsp"%>