<%@page import="com.bstek.ureport.export.html.HtmlReport" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@page import="com.bstek.ureport.export.ExportManager" %>
<%@page import="com.bstek.ureport.Utils" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>HTML报表测试</title>
</head>
<body>
<!-- 通过一个HTML链接来导出目标报表模版的PDF格式报表 -->
<a href="<%=request.getContextPath() %>/ureport/excel?_u=file:printer_number.ureport.xml">导出EXCEL格式报表</a>
<div>
    <iframe src="/ureport/preview?_u=file:printer_number.ureport.xml" width="500" height="500" scrolling="no" frameborder="0">

    </iframe>
</div>

<div>
    <iframe src="/ureport/preview?_u=file:test.ureport.xml" width="1000" height="1000" scrolling="no" frameborder="0">

    </iframe>
</div>

</body>
</html>
