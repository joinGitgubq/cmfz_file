<%@page language="java" import="java.util.*" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<% request.setAttribute("path",request.getContextPath()); %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <script type="text/javascript" src="${path}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url:"${path}/papers/selectAll",
                type:"post",
                success:function(r){
                    var tb=$("#tb");
                    for (var i = 0; i <r.length ; i++) {
                        var t=$("<tr><td>"+r[i].name+"</td><td>"+r[i].path+"</td><td><a href=\"${path}/download?path="+r[i].path+"\">下载</a></td></tr>");
                        tb.append(t);
                    }
                }
            });
        });
    </script>
</head>
    <body>
    <h6 th:inlines="text">文件下载</h6>

    <table border="1px" bgcolor="#ffe4c4">
        <p>选择文件: <input type="file" name="multipartFile"/></p>
        <tbody id="tb"></tbody>
    </table>
    </body>

</html>
