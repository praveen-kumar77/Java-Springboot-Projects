<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.springlearn.StudentDetails.model.EmployeeDetails" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Student List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Get the list of students from the request attribute
                List<EmployeeDetails> details = (List<EmployeeDetails>) request.getAttribute("emp");

                if (details != null) {
                    for (EmployeeDetails emp : details) {

            %>
            <tr>
                <td><%= emp.getId() %></td>
                <td><%= emp.getName() %></td>
                <td><%= emp.getAge() %></td>
                <td><%= emp.getSalary() %></td>
            </tr>
            <%
                    }

                } else {
            %>
            <tr>
                <td colspan="4">No students found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>