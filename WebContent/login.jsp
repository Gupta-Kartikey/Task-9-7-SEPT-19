<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Book Entry</title>
</head>
<body>
<form action="Book_Entry" method="post">
<pre>
BookName:
<input type="text" name="bookName" required>
AuthorName:
<input type="text" name="authorName" required>
Price:
<input type="number" name="price" required>
Genre:
<input type="text" name="genre" required>
Format: <input type="radio" name="word">Word<input type="radio" name="excel">Excel


<input type="submit" value="submit">
</pre>

</form>
</body>
</html>