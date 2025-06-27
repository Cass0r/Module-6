<%@ page import="java.util.List" %>
<%@ page import="com.example.module_6.Items" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
<h2>To-Do Items</h2>
<a href="todo?action=add">Add Item</a> | <a href="todo?action=delete">Delete Item</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
    </tr>
    <%
        List<Items> items = (List<Items>) request.getAttribute("items");
        for (Items item : items) {
    %>
    <tr>
        <td><%= item.getId() %></td>
        <td><%= item.getName() %></td>
        <td><%= item.getShortDescription() %></td>
        <td><%= item.getTaskStatus() == 1 ? "Completed" : "Not Completed" %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
