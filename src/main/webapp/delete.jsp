<html>
<head><title>Delete To-Do Item</title></head>
<body>
<h2>Delete Item</h2>
<form action="todo" method="post">
    <input type="hidden" name="action" value="delete" />
    Enter ID of item to delete: <input type="text" name="id" required />
    <input type="submit" value="Delete" />
</form>
<br/>
<a href="todo?action=list">Back to list</a>
</body>
</html>
