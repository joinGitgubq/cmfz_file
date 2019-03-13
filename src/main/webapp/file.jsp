<%@page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<% request.setAttribute("path",request.getContextPath()); %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
</head>
    <body>
    <h6 th:inlines="text">文件上传</h6>
    <form action="${path}/fileUpload" method="post" enctype="multipart/form-data">
        <p>选择文件: <input type="file" name="multipartFile"/></p>
        <p><input type="submit" value="提交"/>

            <a href="${path}/upfile.jsp">点击下载</a></p>

    </form>
    </body>
</html>
