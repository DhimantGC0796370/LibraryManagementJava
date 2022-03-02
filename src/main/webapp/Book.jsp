<%@ page language="java" import="java.util.*, java.lang.*, model.Books" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book</title>
</head>
<body>
	<h1> Books Page </h1>
	<form action="BookServlet" method="post">
   		<table style="with: 80%">
    		<tr>
    			<td>Book ID:</td>
     			<td><input type="text" name="bookId" /></td>
    		</tr>
    		<tr>
     			<td>Author:</td>
     			<td><input type="text" name="author" /></td>
    		</tr>
    		<tr>
     			<td>Title:</td>
     			<td><input type="text" name="title" /></td>
    		</tr>
    		<tr>
     			<td>Price:</td>
     			<td><input type="text" name="price" /></td>
    		</tr>
    		<tr>
     			<td>Available:</td>
     			<td><input type="text" name="available" /></td>
    		</tr>
    		<tr>
     			<td>Member Id:</td>
     			<td><input type="text" name="memberId" /></td>
    		</tr>
    		<tr>
     			<td>Due Date:</td>
     			<td><input type="date" name="dueDate" min="2022-03-01" max="2022-12-31"></td>
    		</tr>
    	</table>
    <input type="submit" name="issue" value="Issue Book">
    <input type="submit" name="update" value="Update Book">
    <input type="submit" name="delete" value="Delete Book">
  </form>
  <br>
  <br>
  <table border="1" cellpadding="2" cellspacing="2">
		<tr>
		     <th>Book Id</th>
		     <th>Author</th>
		     <th>Title</th>
		     <th>Price</th>
		     <th>Available</th>
	    </tr>
	    <% 
	    	ArrayList<Books> list = (ArrayList<Books>) request.getAttribute("book"); 
	    	Books book = new Books();
	     	for (int i=0; i < list.size(); i++) {
           		book = list.get(i);
        %>

            <tr>
                <td><%=book.getBook_id()%></td>
                <td><%=book.getAuthor()%></td>
                <td><%=book.getTitle()%></td>
                <td><%=book.getPrice()%></td>
                <td><%=book.isAvailable()%></td>
            </tr>
            <%
            };
            %>
	</table>
</body>
</html>