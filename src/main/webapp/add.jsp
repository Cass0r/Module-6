<html>
<head><title>Add To-Do Item</title></head>
<body>
<h2>Add Item</h2>
<form action="todo" method="post">
    <input type="hidden" name="action" value="add" />
    ID: <input type="text" name="id" required /><br/><br/>
    Name: <input type="text" name="name" required /><br/><br/>
    Description: <input type="text" name="description" required /><br/><br/>
    Status:
    <select name="status">
        <option value="0">Not Completed</option>
        <option value="1">Completed</option>
    </select><br/><br/>
    <input type="submit" value="Add Item" />
</form>
<br/>
<a href="todo?action=list">Back to list</a>
</body>
</html>
